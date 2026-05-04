pipeline {
  agent any
  environment { DOCKER_BUILDKIT='1'; HEROKU_APP_NAME=credentials('HEROKU_APP_NAME'); HEROKU_API_KEY=credentials('HEROKU_API_KEY') }
  stages {
    stage('Checkout'){ steps { checkout scm } }
    stage('Build'){ steps { sh 'mvn -B -DskipTests clean package' } }
    stage('Run Tests'){ steps { sh 'mvn -B test' } post { always { junit '**/target/surefire-reports/*.xml' } } }
    stage('Code Coverage'){ steps { sh 'mvn -B verify' } post { always { jacoco execPattern: '**/target/jacoco.exec', classPattern: '**/target/classes', sourcePattern: '**/src/main/java'; archiveArtifacts artifacts: '**/target/site/jacoco/**', allowEmptyArchive: true } } }
    stage('Docker Build'){ steps { sh 'docker compose build || docker-compose build' } }
    stage('Docker Smoke Test'){ steps { sh 'docker compose up -d || docker-compose up -d'; sh 'sleep 30'; sh 'curl -f http://localhost:8081/api/flights'; sh 'curl -f http://localhost:8082/api/hotels'; sh 'curl -f http://localhost:8083/api/cars' } post { always { sh 'docker compose down || docker-compose down' } } }
    stage('Deploy to Heroku'){ when { branch 'main' } steps { sh '''heroku container:login
for service in discovery-service api-gateway flight-service hotel-service car-rental-service; do
 docker tag travel-booking/$service:latest registry.heroku.com/${HEROKU_APP_NAME}-$service/web
 docker push registry.heroku.com/${HEROKU_APP_NAME}-$service/web
 heroku container:release web --app ${HEROKU_APP_NAME}-$service
done''' } }
  }
}
