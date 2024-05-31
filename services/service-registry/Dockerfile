
# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package


# Verify that the JAR file was created
RUN ls -l /home/app/target

# Stage 2: Run the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 8688
ENTRYPOINT ["java", "-jar", "app.jar"]
