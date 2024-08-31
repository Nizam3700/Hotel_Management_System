# Stage 1: Build the application using Gradle
FROM gradle:7.2.0-jdk17 AS build
WORKDIR /app

# Copy Gradle project files
COPY . .

RUN chmod +x ./gradlew

# Build the project
RUN ./gradlew clean build -x test


# Stage 2: Create a minimal image for running the Spring Boot application
FROM openjdk:17.0.1-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built jar file from the previous stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port your application will run on
EXPOSE 8081

# Set environment variables for MySQL
#ENV MYSQL_DBNAME=hotel
#ENV MYSQL_USERNAME=root
#ENV MYSQL_PASSWORD=Nizam@143
#ENV MYSQL_URL=jdbc:mysql://192.168.29.168
#ENV MYSQL_PORT=3306

ENV MYSQl_DBNAME=defaultdb
ENV MYSQL_USERNAME=avnadmin
ENV MYSQL_PASSWORD=AVNS_TgDsSNoiJAL60YDVnVd
ENV MYSQL_URL=jdbc:mysql://mysql-1ae1a222-nizamuddin8637-1ba4.h.aivencloud.com
ENV MYSQL_PORT=22346

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]