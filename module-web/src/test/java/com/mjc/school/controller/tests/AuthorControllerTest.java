package com.mjc.school.controller.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class AuthorControllerTest {
    @BeforeAll
    public static void initiate(){
        RestAssured.baseURI = "http://localhost:8082";
        RestAssured.port = 8082;
    }
    @Test
    public void givenValidRequest_whenCreateAuthor_thenReturn201() {
        Integer id = given()
                .contentType("application/json")
                .body("{\"name\" : \"Robertson\"}")
                .when()
                .request("POST", "/author")
                .then()
                .statusCode(201)
                .body("name",equalTo("Robertson")).extract().path("id");
        given().request("Delete","author/"+id.longValue());
    }
    @Test
    public void givenNoNValidRequest_whenCreateAuthor_thenReturn404(){
        given()
                .contentType("application/json")
                .body("{\"name\":\"No\"}")
                .when()
                .request("POST","/author")
                .then()
                .statusCode(404)
                .body("code", equalTo("400001"))
                .body("errorMessage",equalTo("Author name should be between 3 and 15 characters. "));
    }
    @Test
    public void testReadByIdMethod(){
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\"name\" : \"Mark Aurelius\"}")
                .request("POST","/author");
        given()
                .request("GET", "/author/"+response.jsonPath().getLong("id"))
                .then()
                .statusCode(200)
                .body("name", equalTo("Mark Aurelius"));
        given().request("DELETE", "/author/"+response.jsonPath().getLong("id"));

    }
    @Test
    public void createNewsTest(){
        Response response= RestAssured.given()
                .contentType("application/json")
                .body("{ \"authorName\": \"Author example\", \"content\": \"Content\", \"tagNames\": [ \"Tag name example\" ], \"title\": \"Title example\"}")
                .when()
                .request("POST","/news")
                .then()
                .statusCode(201)
                .body("title",equalTo("Title example")).extract().response();
        given().request("Delete","news/"+response.jsonPath().getLong("id"));
    }

}
