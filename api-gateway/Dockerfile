FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /workspace
COPY pom.xml ./
COPY api-gateway ./api-gateway
COPY discovery-service ./discovery-service
COPY flight-service ./flight-service
COPY hotel-service ./hotel-service
COPY car-rental-service ./car-rental-service
RUN mvn -pl api-gateway -am clean package -DskipTests

FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /workspace/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
