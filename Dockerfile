# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set environment variables for MySQL and JWT
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/hotel
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=Nizam@143
#ENV JWT_SECRET=357638792F423F4428472B4B6250655368566D597133743677397A2443264629
#ENV JWT_EXPIRATION=1800000

# Set the working directory inside the container
WORKDIR /app

# Copy the build files (assuming you use the Spring Boot jar file)
#COPY --build/libs/Security-0.0.1-SNAPSHOT.jar app.jar
COPY --from=build /app/build/libs/Security-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your Spring Boot app runs (custom port 8081 in this case)
EXPOSE 8081

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
