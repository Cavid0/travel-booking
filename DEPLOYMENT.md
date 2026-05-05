# Deployment Guide - Travel Booking Microservices

Bu sənəd `travel-booking` microservice layihəsini cloud-a deploy etmək üçün lazım olan bütün addımları izah edir. Layihədə 5 servis var:

- `discovery-service` - Eureka Server
- `api-gateway` - Spring Cloud Gateway
- `flight-service`
- `hotel-service`
- `car-rental-service`

## 1. Deploy üçün lazım olan alətlər

Kompüterdə və ya CI/CD serverdə bunlar olmalıdır:

```bash
java -version      # Java 11 və ya yuxarı
mvn -version       # Maven
docker --version   # Docker
docker compose version
git --version
heroku --version   # Heroku deploy seçilərsə
```

> Qeyd: Docker işləmirsə `docker compose up --build` xəta verəcək. Mac-da Docker Desktop açıq olmalıdır.

## 2. Local build və test

Əvvəlcə layihənin root qovluğuna keç:

```bash
cd travel-booking
```

Build:

```bash
mvn clean package
```

Testlər:

```bash
mvn test
```

Code coverage / JaCoCo report:

```bash
mvn verify
```

Report faylları:

```text
discovery-service/target/site/jacoco/index.html
api-gateway/target/site/jacoco/index.html
flight-service/target/site/jacoco/index.html
hotel-service/target/site/jacoco/index.html
car-rental-service/target/site/jacoco/index.html
```

## 3. Local Docker Compose ilə işlətmək

Əvvəl jar-ları hazırla:

```bash
mvn clean package
```

Sonra Docker Compose:

```bash
docker compose up --build
```

Yoxlama endpoint-ləri:

```bash
curl http://localhost:8761/actuator/health
curl http://localhost:8081/api/flights
curl http://localhost:8082/api/hotels
curl http://localhost:8083/api/cars
```

Gateway üzərindən:

```bash
curl http://localhost:8080/api/flights
curl http://localhost:8080/api/hotels
curl http://localhost:8080/api/cars
curl "http://localhost:8080/api/flights/search?origin=NYC&destination=LAX"
```

Dayandırmaq üçün:

```bash
docker compose down
```

## 4. Heroku ilə deploy addımları

Bu layihədə hər microservice ayrıca Heroku app kimi deploy edilir. Bunun səbəbi hər servisin ayrıca port, image və runtime prosesə sahib olmasıdır.

### 4.1 Heroku login

```bash
heroku login
heroku container:login
```

### 4.2 Heroku app-ləri yarat

`mytravel` yerinə öz base adını yaz:

```bash
heroku create mytravel-discovery-service
heroku create mytravel-api-gateway
heroku create mytravel-flight-service
heroku create mytravel-hotel-service
heroku create mytravel-car-rental-service
```

### 4.3 JAR faylları build et

```bash
mvn clean package
```

### 4.4 Docker image-ləri build et

```bash
docker compose build
```

### 4.5 Image-ləri Heroku Container Registry üçün tag et

```bash
docker tag travel-booking/discovery-service:latest registry.heroku.com/mytravel-discovery-service/web
docker tag travel-booking/api-gateway:latest registry.heroku.com/mytravel-api-gateway/web
docker tag travel-booking/flight-service:latest registry.heroku.com/mytravel-flight-service/web
docker tag travel-booking/hotel-service:latest registry.heroku.com/mytravel-hotel-service/web
docker tag travel-booking/car-rental-service:latest registry.heroku.com/mytravel-car-rental-service/web
```

### 4.6 Image-ləri push et

```bash
docker push registry.heroku.com/mytravel-discovery-service/web
docker push registry.heroku.com/mytravel-api-gateway/web
docker push registry.heroku.com/mytravel-flight-service/web
docker push registry.heroku.com/mytravel-hotel-service/web
docker push registry.heroku.com/mytravel-car-rental-service/web
```

### 4.7 Eureka URL environment variable-larını set et

Discovery service deploy olunandan sonra digər servislər ona qoşulmalıdır:

```bash
heroku config:set EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=https://mytravel-discovery-service.herokuapp.com/eureka/ --app mytravel-api-gateway
heroku config:set EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=https://mytravel-discovery-service.herokuapp.com/eureka/ --app mytravel-flight-service
heroku config:set EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=https://mytravel-discovery-service.herokuapp.com/eureka/ --app mytravel-hotel-service
heroku config:set EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=https://mytravel-discovery-service.herokuapp.com/eureka/ --app mytravel-car-rental-service
```

### 4.8 Release et

```bash
heroku container:release web --app mytravel-discovery-service
heroku container:release web --app mytravel-api-gateway
heroku container:release web --app mytravel-flight-service
heroku container:release web --app mytravel-hotel-service
heroku container:release web --app mytravel-car-rental-service
```

### 4.9 Deploy sonrası yoxlama

```bash
heroku open --app mytravel-discovery-service
curl https://mytravel-flight-service.herokuapp.com/api/flights
curl https://mytravel-hotel-service.herokuapp.com/api/hotels
curl https://mytravel-car-rental-service.herokuapp.com/api/cars
```

## 5. Jenkins ilə avtomatik deploy

`Jenkinsfile` hazırdır. Jenkins-də pipeline yaratmaq üçün:

1. Layihəni GitHub-a push et.
2. Jenkins-də **New Item > Pipeline** seç.
3. **Pipeline script from SCM** seç.
4. GitHub repo URL-ni yaz.
5. Branch: `main`
6. Script path: `Jenkinsfile`

### Jenkins credential-ları

Jenkins-də **Manage Jenkins > Credentials** bölməsində bunları əlavə et:

| Credential ID | Tip | Dəyər |
|---|---|---|
| `HEROKU_API_KEY` | Secret text | Heroku API key |
| `HEROKU_APP_NAME` | Secret text | Base app adı, məsələn `mytravel` |

Heroku API key almaq üçün:

```bash
heroku auth:token
```

Pipeline bunları edir:

1. Checkout
2. Maven build
3. Unit test
4. JaCoCo coverage report
5. Docker build
6. Smoke test
7. `main` branch-də Heroku deploy

## 6. Travis CI ilə avtomatik deploy

Alternativ olaraq `.travis.yml` də hazırdır.

Travis-də environment variable-lar əlavə et:

| Variable | Dəyər |
|---|---|
| `HEROKU_API_KEY` | Heroku API key |
| `HEROKU_APP_NAME` | Base app adı, məsələn `mytravel` |
| `CODECOV_TOKEN` | Optional, Codecov token |

Travis workflow:

1. `mvn clean package`
2. `mvn test`
3. `mvn verify`
4. Docker image build
5. Codecov upload
6. `main` branch-də Heroku release

## 7. GitHub-a push etmək

Əgər repo hələ yaradılmayıbsa:

```bash
git init
git add .
git commit -m "Add travel booking microservices with CI/CD"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/travel-booking.git
git push -u origin main
```

## 8. Tez-tez çıxan problemlər

### Docker daemon işləmir

Xəta:

```text
failed to connect to the docker API
```

Həll:

- Docker Desktop-u aç.
- Sonra yenidən çalışdır:

```bash
docker compose up --build
```

### Heroku app adı səhvdir

Xəta:

```text
App not found
```

Həll: `mytravel` yerinə yaratdığın real app adlarını yaz.

### Eureka connection refused

Local test zamanı görünə bilər. Docker Compose ilə bütün servislər birlikdə qalxanda Eureka bağlantısı işləyəcək. Cloud-da isə `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE` düzgün set edilməlidir.

## 9. Final deploy checklist

- [ ] `mvn clean package` uğurludur
- [ ] `mvn test` uğurludur
- [ ] `mvn verify` JaCoCo report yaradır
- [ ] Docker Desktop işləyir
- [ ] `docker compose build` uğurludur
- [ ] Heroku app-lər yaradılıb
- [ ] `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE` set olunub
- [ ] Docker image-lər Heroku registry-ə push olunub
- [ ] `heroku container:release` işlədilib
- [ ] Public endpoint-lər `curl` ilə yoxlanılıb

## 10. Render ilə deploy addımları

Render üçün `render.yaml` Blueprint faylı əlavə edilib. Bu fayl 5 servisi Docker ilə deploy edir:

- `travel-booking-discovery`
- `travel-booking-flight`
- `travel-booking-hotel`
- `travel-booking-car-rental`
- `travel-booking-gateway`

### 10.1 GitHub-a push et

Render Blueprint repo-dan oxunur. Əvvəl dəyişiklikləri GitHub-a push et:

```bash
cd travel-booking
git add .
git commit -m "Add Render deployment blueprint"
git push
```

> Vacib: `travel-booking-discovery` servisində `EUREKA_CLIENT_ENABLED=false` yazma. Eureka Server-in öz auto-configuration bean-ləri üçün client tərəfi tam söndürülməməlidir. Discovery service-də yalnız `register-with-eureka: false` və `fetch-registry: false` qalmalıdır.

### 10.2 Render Blueprint yarat

1. https://dashboard.render.com aç.
2. **New +** düyməsinə kliklə.
3. **Blueprint** seç.
4. GitHub repository-ni qoş.
5. Render `render.yaml` faylını avtomatik tapacaq.
6. **Apply** et və deploy prosesinin bitməsini gözlə.

### 10.3 Render endpoint-ləri yoxla

Əsas real sayt linki:

```bash
open https://travel-booking-gateway.onrender.com
```

Bu linkin içində Flight, Hotel və Car Rental axtarışları işləyir. API-ləri ayrıca yoxlamaq üçün:

```bash
curl https://travel-booking-discovery.onrender.com/actuator/health
curl https://travel-booking-flight.onrender.com/api/flights
curl https://travel-booking-hotel.onrender.com/api/hotels
curl https://travel-booking-car-rental.onrender.com/api/cars
curl "https://travel-booking-gateway.onrender.com/api/flights/search?origin=NYC&destination=LAX"
curl "https://travel-booking-gateway.onrender.com/api/hotels/search?location=LosAngeles"
curl "https://travel-booking-gateway.onrender.com/api/cars/search?location=LosAngeles"
```

> Qeyd: Render free plan-da servis yuxuya gedə bilər. İlk request 30-60 saniyə gec cavab verə bilər.

### 10.4 Əgər service URL adları fərqli yaranarsa

Əgər Render URL-ləri yuxarıdakı formatda yaratmasa, Render dashboard-da `travel-booking-gateway` servisinin environment variable-larını düzəlt:

| Key | Dəyər |
|---|---|
| `FLIGHT_SERVICE_URL` | Flight servisinin real Render URL-i |
| `HOTEL_SERVICE_URL` | Hotel servisinin real Render URL-i |
| `CAR_RENTAL_SERVICE_URL` | Car rental servisinin real Render URL-i |

Sonra API Gateway servisini **Manual Deploy > Clear build cache & deploy** ilə yenidən deploy et.

### 10.5 Discovery service Render xətası

Əgər `travel-booking-discovery` log-da bu xəta çıxarsa:

```text
No qualifying bean of type 'com.netflix.appinfo.ApplicationInfoManager'
```

Render dashboard-da `travel-booking-discovery` servisinin **Environment** bölməsinə gir və bunu sil:

```text
EUREKA_CLIENT_ENABLED=false
```

Sonra **Manual Deploy > Clear build cache & deploy** et.
