# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the target directory into the container
COPY target/*.jar app.jar

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]