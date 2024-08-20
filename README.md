# Spring Boot REST API Demo

Very simple Book API in Spring Boot.

Based on https://www.youtube.com/@devtiro/videos

Run `./start.sh` to build and run

Endpoints:

```
POST https://localhost:8080/books
{
    "isbn": "isbn-1234",
    "title": "Watchmen",
    "author": "Alan Moore"
}

GET https://localhost:8080/books

GET https://localhost:8080/books/isbn-1234

PUT https://localhost:8080/books/isbn-1234
{
    "isbn": "isbn-1234",
    "title": "Watchmen",
    "author": "Alan Moore (knows the score!)"
}

DELETE https://localhost:8080/books/isbn-1234
```