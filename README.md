# My pizzeria

This is a simple REST Service built with Spring Boot that allows to manage the customer's favorite toppings.

## Features

- Add new customers along with theirs favorite toppings
- Get a list of favorite toppings for requested customer
- Get a list of toppings currently submitted and the number of unique customers that have requested that topping
- Get a list of the authenticated customer favorite toppings
- Authenticate for accessing the personal toppings page

## Installation/Running

#### To run this application:

1. Clone/download this repository.
2. Use `./gradlew bootRun` command to run the application.
3. Open `http://localhost:8080/toppings` in your browser/http client.

#### Alternative way (using jar file):

1. Clone/download this repository.
2. Use `./gradlew bootJar` command to build an executable .jar file.
3. Follow the path `/build/libs` and grab the file `my-pizzeria-<version>.jar` into your directory.
4. Run .jar file `java -jar my-pizzeria-<version>.jar <parameters or environment variables>`.
5. Open `http://localhost:8080/toppings` in your browser/http client.

### Environment variables

- `H2_USERNAME`: Username for H2 database. By default `sa`
- `H2_PASSWORD`: Password for H2 database. By default `password`
- `JWT_PASSWORD`: Password for JWT sign. By default `cGFzc3dvcmRwYXNzd29yZHBhc3N3b3JkcGFzc3dvcmRwYXNzd29yZHBhc3N3b3Jk`

## Usage

### Accessing the H2 database from the Web UI

Endpoint: `/h2-console`

JDBC URL: `jdbc:h2:file:./db/pizzeria`

User Name: From `H2_USERNAME` env variable. By default `sa`

Password: From `H2_PASSWORD` env variable. By default `password`

### Authentication for accessing the personal toppings page

Endpoint: `/auth/signin`

Request Method: `POST`

Request Body:

```json
{
  "username": "johndoe@example.com"
}
```

Response Body:

```json
{
  "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGV4YW1wbGUuY29tIiwiaWF0IjoxNjg1OTYzMDIwLCJleHAiOjE2ODU5NjMzMjB9.N3yt4V8V1TcBklAYLdnMwgdvAVzN6SFpXkxXVzc5Jnw"
}
```

### Adding new customers along with theirs favorite toppings

Endpoint: `/toppings`

Request Method: `POST`

Request Body:

```json
{
  "email": "johndoe@example.com",
  "toppings": [
    "topping 1",
    "topping 2",
    "topping 3"
  ]
}
```

### Getting a list of favorite toppings for requested customer

Endpoint: `/toppings/{customer_email}`

Request Method: `GET`

Response Body:

```json
[
  "topping 1",
  "topping 2",
  "topping 3"
]
```

### Getting a list of toppings currently submitted and the number of unique customers that have requested that topping

Endpoint: `/toppings`

Request Method: `GET`

Response Body:

```json
{
  "topping 1": 2,
  "topping 2": 4,
  "topping 3": 1 
}
```

### Getting a list of the authenticated customer favorite toppings

Endpoint: `/toppings/personal`

Request Method: `GET`

Authorization Header: `Bearer <token>`

Response Body:

```json
[
  "topping 1",
  "topping 2",
  "topping 3"
]
```