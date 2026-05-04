FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /workspace
COPY pom.xml ./
COPY car-rental-service ./car-rental-service
RUN mvn -pl car-rental-service -am clean package -DskipTests

FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /workspace/car-rental-service/target/car-rental-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/app.jar"]
