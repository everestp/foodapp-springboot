# Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/food-springboot-0.0.1-SNAPSHOT.jar food-springboot.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "food-springboot.jar"]
