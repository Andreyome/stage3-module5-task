package com.mjc.school.controller.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommentControllerTest {
    public static final String commentExample = "{\"content\":\"Comment example\" , \"newsId\": ";

    @BeforeAll
    public static void initiate() {
        RestAssured.baseURI = "http://localhost:8082";
        RestAssured.port = 8082;
    }

    @Test
    public void testCreateComment() {
        Response response = NewsControllerTest.createTmpNews();
        Long newsId = response.jsonPath().getLong("id");
        List<String> commentNames = new ArrayList<>();
        commentNames.add(commentExample + newsId + "}");
        List<Long> commentIds = new ArrayList<>();
        commentNames.forEach(a -> commentIds.add(given()
                .contentType("application/json")
                .body(String.format(a))
                .when().request("POST", "/comment").then().statusCode(201)
                .body("content", equalTo(a.split("[:,\"]+")[2])).extract().jsonPath().getLong("id")));
        NewsControllerTest.deleteTmp(response);
    }

    @Test
    public void testDeleteComment() {
        Response response = NewsControllerTest.createTmpNews();
        Long newsId = response.jsonPath().getLong("id");
        List<String> commentNames = new ArrayList<>();
        commentNames.add(commentExample + newsId + "}");
        List<Long> commentIds = new ArrayList<>();
        commentNames.forEach(a -> commentIds.add(given()
                .contentType("application/json")
                .body(String.format(a))
                .when().request("POST", "/comment").then().statusCode(201)
                .body("content", equalTo(a.split("[:,\"]+")[2])).extract().jsonPath().getLong("id")));
        commentIds.forEach(a -> given().delete("/comment/" + a).then().statusCode(204));
        NewsControllerTest.deleteTmp(response);
    }

    @Test
    public void testUpdateComment() {
        Response response = NewsControllerTest.createTmpNews();
        Long newsId = response.jsonPath().getLong("id");
        List<String> commentNames = new ArrayList<>();
        commentNames.add(commentExample + newsId + "}");
        String updatedComment = "{\"content\":\"Comment example\" , \"newsId\": " + newsId + "}";
        List<Long> commentIds = new ArrayList<>();
        commentNames.forEach(a -> commentIds.add(given()
                .contentType("application/json")
                .body(String.format(a))
                .when().request("POST", "/comment").then().statusCode(201)
                .body("content", equalTo(a.split("[:,\"]+")[2])).extract().jsonPath().getLong("id")));
        given().contentType("application/json").body(updatedComment).patch("/comment/" + commentIds.get(0)).then().statusCode(200);
        NewsControllerTest.deleteTmp(response);
    }

}
