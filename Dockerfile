# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target directory
COPY target/*.jar /app/app.jar

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
