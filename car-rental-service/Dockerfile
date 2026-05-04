FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/car-rental-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/app.jar"]
