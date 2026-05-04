FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /workspace
COPY pom.xml ./
COPY discovery-service ./discovery-service
COPY api-gateway ./api-gateway
COPY flight-service ./flight-service
COPY hotel-service ./hotel-service
COPY car-rental-service ./car-rental-service
RUN mvn -pl flight-service -am clean package -DskipTests

FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /workspace/flight-service/target/flight-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
