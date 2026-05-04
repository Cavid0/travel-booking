FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/hotel-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]
