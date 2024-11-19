package com.mjc.school.controller.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class AuthorControllerTest {
    @BeforeAll
    public static void initiate() {
        RestAssured.baseURI = "http://localhost:8082";
        RestAssured.port = 8082;
    }

    @Test
    @Transactional
    @Rollback
    public void givenValidRequest_whenCreateAuthor_thenReturn201() {
        Integer id = given()
                .contentType("application/json")
                .body("{\"name\" : \"Robertson\"}")
                .when()
                .request("POST", "/author")
                .then()
                .statusCode(201)
                .body("name", equalTo("Robertson")).extract().path("id");
        given().request("Delete", "author/" + id.longValue());
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateTwoAuthorsWithSameNameResultIn404() {
        Integer id = given()
                .contentType("application/json")
                .body("{\"name\" : \"Robertson\"}")
                .when()
                .request("POST", "/author")
                .then()
                .statusCode(201)
                .body("name", equalTo("Robertson")).extract().path("id");
        given()
                .contentType("application/json")
                .body("{\"name\" : \"Robertson\"}")
                .when()
                .request("POST", "/author")
                .then()
                .statusCode(404)
                .body("code", equalTo("400001"));
        given().request("Delete", "author/" + id.longValue());
    }

    @Test
    @Transactional
    @Rollback
    public void givenNoNValidRequest_whenCreateAuthor_thenReturn404() {
        given()
                .contentType("application/json")
                .body("{\"name\":\"No\"}")
                .when()
                .request("POST", "/author")
                .then()
                .statusCode(404)
                .body("code", equalTo("400001"))
                .body("errorMessage", equalTo("Author name should be between 3 and 15 characters. "));
    }

    @Test
    @Transactional
    @Rollback
    public void testReadByIdMethod() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\"name\" : \"Mark Aurelius\"}")
                .request("POST", "/author")
                .then().statusCode(201).extract().response();
        given()
                .request("GET", "/author/" + response.jsonPath().getLong("id"))
                .then()
                .statusCode(200)
                .body("name", equalTo("Mark Aurelius"));
        given().request("DELETE", "/author/" + response.jsonPath().getLong("id"));
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteAuthor() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\"name\" : \"Mark Aurelius\"}")
                .request("POST", "/author")
                .then().statusCode(201).extract().response();
        given().request("DELETE", "/author/" + response.jsonPath().getLong("id")).then().statusCode(204);
        given()
                .request("GET", "/author/" + response.jsonPath().getLong("id"))
                .then()
                .statusCode(404);
    }
}
