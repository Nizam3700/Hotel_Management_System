# Overview
A RESTful API service built using Spring Boot provides endpoints for mapping users,hottels and bookings while using MySQL to persist the data.

## Deployed in Render 
Check it Out : https://hotel-management-system-3.onrender.com/swagger-ui/index.html

# Features
* Authentication and Authorization : Implemented using Bearer token with three roles: CUSTOMER, MANAGER and ADMIN.
* User management:
  + User registration with email, password (encrypted using BCrypt), first name, last name, and role.
  + User login using email and password and a bearer token will get generated.
* Hotel management:
  + Store and manage Hotel details such as name,location,description and available rooms.
  + Create, update and delete hotels.
* Booking management:
  + User will book a hotel with his user details and hotelId.

# Getting Started:
## Prerequisites:
* Java 17 or higher
* MySql
* Postman for API Testing

## Configuration:
* DATABASE : AIVEN Cloud MySql Integration in Project 

## Running the application:
Run the application using Gradle:
./gradlew bootrun

# API Endpoints

You can find the API endpoints and test them using the provided:  https://coder9-3654.postman.co/workspace/CodeHacker%2FNizamuddin~ea59a5b6-06b9-48c2-af89-b00c27c6c5d0/request/34329189-5eab081f-b8f7-4dcc-aa4c-6a6aab12f288?action=share&creator=34329189&ctx=documentation
