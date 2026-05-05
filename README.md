# Taking Care Of Business - Travel Booking CI/CD

Spring Boot microservice travel booking system with Eureka discovery, API Gateway, Flight, Hotel and Car Rental services. Includes Dockerfiles for every microservice, `docker-compose.yml`, Jenkins and Travis CI pipelines, JUnit tests, JaCoCo coverage, Codecov upload support, Heroku container deployment configuration and Render Blueprint deployment (`render.yaml`).

## Structure
`discovery-service/`, `api-gateway/`, `flight-service/`, `hotel-service/`, `car-rental-service/`, `docker-compose.yml`, `render.yaml`, `Jenkinsfile`, `.travis.yml`, root `pom.xml`.

## Run locally
```bash
mvn clean package
docker compose up --build
```

## Test and coverage
```bash
mvn test
mvn verify
```
JaCoCo reports: `<service>/target/site/jacoco/index.html`.

## One public website link

After deploying the Render Blueprint, open the API Gateway as the real website:

```text
https://travel-booking-gateway.onrender.com
```

This single link contains the UI and calls all backend microservices through the gateway.

## Endpoints
- Discovery: http://localhost:8761
- Flight: http://localhost:8081/api/flights and `/api/flights/search?origin=NYC&destination=LAX`
- Hotel: http://localhost:8082/api/hotels and `/api/hotels/search?location=LosAngeles`
- Cars: http://localhost:8083/api/cars and `/api/cars/search?location=LosAngeles`
- Gateway website: http://localhost:8080
- Gateway APIs: `/api/flights/**`, `/api/hotels/**`, `/api/cars/**` via http://localhost:8080

## CI/CD
Jenkinsfile stages: checkout, Maven build, tests, JaCoCo coverage, Docker build, smoke test, Heroku deploy. Travis alternative is in `.travis.yml`. Configure `HEROKU_API_KEY`, `HEROKU_APP_NAME`, and optional `CODECOV_TOKEN` in CI.

## Deployment Guide

Cloud deploy üçün addım-addım təlimat ayrıca faylda verilib:

```text
DEPLOYMENT.md
```

## Render deploy

Render-də **New > Blueprint** seçib GitHub repo-nu qoş. `render.yaml` faylı 5 servisi yaradır:

- `travel-booking-discovery`
- `travel-booking-gateway`
- `travel-booking-flight`
- `travel-booking-hotel`
- `travel-booking-car-rental`

Gateway URL: `https://travel-booking-gateway.onrender.com`
