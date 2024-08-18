# Base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the build file
COPY /home/sussa/Desktop/WorkSpace/marketduck/*.jar /app/marketduck.jar

# Expose the port the application runs on
EXPOSE 8987

# Define entrypoint
ENTRYPOINT ["java", "-jar", "/app/marketduck.jar", "--spring.profiles.active=dev"]