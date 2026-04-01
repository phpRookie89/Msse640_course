Project 2 – Integration Testing with Postman
Integration Testing Using a Triangle Classification API

Student Name: Shawn Wilkinson
Course: MSSE 640
Assignment: Project 2 – Integration Testing with Postman
Date: March 2026

Introduction

Modern distributed systems rely on Application Programming Interfaces (APIs) to enable communication between software components. Integration testing ensures these interfaces function correctly by verifying request handling, responses, and error scenarios.

In this project, a Triangle Classification API was developed using Python Flask and tested using Postman. The API accepts triangle side lengths as input and returns classification results in JSON format. Postman collections and environment variables were used to organize requests and validate expected system behavior.

This project demonstrates how HTTP supports integration testing workflows in modern distributed applications.

Part 1 – Research on APIs and Integration Testing
Basic Functionality of HTTP

HTTP (HyperText Transfer Protocol) enables communication between clients and servers.

Clients

Clients send requests to servers.

Examples include:

Web browsers
Mobile applications
Postman
curl command-line tool

In this lab, Postman acted as the client.

Servers

Servers process incoming requests and return responses.

Example:

The Flask Triangle API acted as the server in this project.

Requests

HTTP requests contain:

HTTP method
URL
Headers
Optional body

Example request:

POST /triangles
Responses

Servers return responses containing:

Status codes
Headers
JSON data

Example response:

{
"a": 3,
"b": 4,
"c": 5,
"triangle_type": "Scalene"
}
Headers vs Body

Headers contain metadata:

Content-Type: application/json

Body contains transmitted data:

{
"a": 3,
"b": 4,
"c": 5
}
Status Codes

Status codes describe request outcomes.

Code Meaning
200 Success
201 Created
400 Bad request
404 Not found
500 Server error

Example:

400 Bad Request

Occurs when triangle side values are missing.

HTTP Verbs

HTTP verbs describe actions performed on resources.

Verb Purpose
GET Retrieve data
POST Create data
PUT Update data
DELETE Remove data

Example endpoints used:

GET /triangles
POST /triangles
PUT /triangles/{id}
DELETE /triangles/{id}
Why HTTP is Stateless

HTTP is stateless because each request is processed independently.

The server does not automatically remember previous interactions.

State can be maintained using:

cookies
sessions
tokens

Stateless architecture improves scalability and reliability.

Role of APIs in Modern Applications

APIs allow software components to communicate with each other.

Example:

The Triangle API receives triangle side values from a client application and returns classification results.

This demonstrates how APIs enable interaction between frontend and backend systems.

Open APIs

Open APIs are publicly accessible APIs available to developers.

Benefits include:

faster development
interoperability
third-party integrations

Example:

Google Maps API allows applications to display maps and calculate navigation routes.

Source:

https://developers.google.com/maps/documentation

Cross-Origin Resource Sharing (CORS)

CORS controls whether web applications can access resources from another domain.

Example:

localhost:3000 → frontend
localhost:5000 → backend

Without CORS enabled:

browser blocks requests

With CORS enabled:

requests are allowed

CORS improves application security.

API Security

APIs are secured using authentication and encryption mechanisms.

Common methods include:

API Keys

Example:

x-api-key: abc123
OAuth 2.0

Used for delegated authentication such as logging in with Google.

JWT Tokens

Example:

Authorization: Bearer token
HTTPS Encryption

Encrypts communication between client and server.

The Triangle API used in this project runs locally and does not require authentication.

Five Public APIs

Examples include:

API Purpose
JSONPlaceholder REST testing
OpenWeather API Weather data
NASA Open API Space data
REST Countries API Country information
Google Maps API Navigation services

Sources:

https://jsonplaceholder.typicode.com

https://api.nasa.gov

https://restcountries.com

Part 2 – Integration Testing Using Postman
Triangle API Architecture

The Triangle API uses a layered structure:

triangle_model.py
triangle_data.py
app.py

This separates:

business logic
data storage
controller endpoints

This mirrors enterprise software architecture practices.

Postman Collection

Collection created:

Triangle API Collection

📸 Insert screenshot here

Environment Variable

Environment variable created:

{{url}} = http://127.0.0.1:5000

This allows requests to be reused easily.

📸 Insert screenshot here

Example Requests Tested
GET Request – List Stored Triangles
GET {{url}}/triangles

Example response:

{
"count": 1,
"items": [
{
"id": 1,
"a": 3,
"b": 4,
"c": 5,
"triangle_type": "Scalene"
}
]
}

📸 Insert screenshot here

POST Request – Valid Triangle
POST {{url}}/triangles

Body:

{
"a": 3,
"b": 4,
"c": 5
}

Response:

{
"message": "Triangle created",
"item": {
"id": 1,
"a": 3,
"b": 4,
"c": 5,
"triangle_type": "Scalene"
}
}

📸 Insert screenshot here

POST Request – Equilateral Triangle
{
"a": 5,
"b": 5,
"c": 5
}

Response:

triangle_type: Equilateral

📸 Insert screenshot here

Error Scenario Example

Missing parameter example:

{
"a": 5,
"b": 5
}

Response:

{
"error": "Missing triangle sides",
"status": 400
}

📸 Insert screenshot here

Persistence Behavior

The Triangle API stores triangle records in memory using a simulated data layer.

After executing:

POST /triangles

records remain available when calling:

GET /triangles

This demonstrates persistence during runtime execution of the application.

Additional Requests Created

Additional integration tests performed:

GET /health
GET /triangles
POST /triangles
GET /triangles/{id}
PUT /triangles/{id}
DELETE /triangles/{id}
GET /triangles/summary

These requests validated API functionality and error handling behavior.

Extra Credit – curl Requests

Example request:

curl http://127.0.0.1:5000/triangles

Example POST request:

curl -X POST http://127.0.0.1:5000/triangles \
-H "Content-Type: application/json" \
-d '{"a":3,"b":4,"c":5}'

Advantages of curl:

works in terminal environments
useful for automation scripts
lightweight compared to GUI tools

Advantages of Postman:

visual interface
collections
environment variables
structured request testing
Conclusion and Recommendations

This lab demonstrated how integration testing verifies communication between clients and APIs using HTTP requests. Postman collections and environment variables improved request organization and testing efficiency.

Testing confirmed that the Triangle API correctly classified triangle types, handled invalid input scenarios, and stored triangle records during runtime. Integration testing tools such as Postman are essential for validating distributed system reliability.
