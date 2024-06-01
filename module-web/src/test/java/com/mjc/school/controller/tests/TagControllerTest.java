package com.mjc.school.controller.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TagControllerTest {
    @BeforeAll
    public static void initiate() {
        RestAssured.baseURI = "http://localhost:8082";
        RestAssured.port = 8082;
    }

    String tagExample = "{\"name\":\"Breaking news\"}";

    @Test
    public void testCreateTag() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(tagExample)
                .when()
                .request("POST", "/tag")
                .then()
                .statusCode(201)
                .body("name", equalTo(tagExample.split("[:,\"]+")[2])).extract().response();
        System.out.println("Test Tag created" + "\n" + response.asPrettyString());
        Long id = response.jsonPath().getLong("id");
        given().request("Delete", "tag/" + id).then().statusCode(204);
        System.out.println("Tmp info deleted. Test finished");
    }

    @Test
    public void testUpdateTag() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(tagExample)
                .when()
                .request("POST", "/tag")
                .then()
                .statusCode(201)
                .body("name", equalTo(tagExample.split("[:,\"]+")[2])).extract().response();
        System.out.println("Test Tag created" + "\n" + response.asPrettyString());
        Long id = response.jsonPath().getLong("id");
        given().contentType("application/json").body("{\"name\" : \"Old news\"}").when().put("/tag/" + id).then().statusCode(200).body("name", equalTo("Old news"));
        given().request("Delete", "tag/" + id).then().statusCode(204);
        System.out.println("Tmp info deleted. Test finished");
    }

    @Test
    public void testDeleteTag() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(tagExample)
                .when()
                .request("POST", "/tag")
                .then()
                .statusCode(201)
                .body("name", equalTo(tagExample.split("[:,\"]+")[2])).extract().response();
        System.out.println("Test Tag created" + "\n" + response.asPrettyString());
        Long id = response.jsonPath().getLong("id");
        given().request("Delete", "tag/" + id).then().statusCode(204);
        System.out.println("Tmp info deleted. Test finished");
    }
}
