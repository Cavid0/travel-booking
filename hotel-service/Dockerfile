FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /workspace
COPY pom.xml ./
COPY hotel-service ./hotel-service
RUN mvn -pl hotel-service -am clean package -DskipTests

FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /workspace/hotel-service/target/hotel-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]
