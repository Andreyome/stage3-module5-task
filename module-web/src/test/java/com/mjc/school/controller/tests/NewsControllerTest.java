package com.mjc.school.controller.tests;

import com.mjc.school.service.dto.TagDtoResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.controller.tests.CommentControllerTest.commentExample;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NewsControllerTest {
    public static String newsExample = "{ \"authorName\": \"Author example\", \"content\": \"Content example\", \"tagNames\": [ \"Tag name example\",\"Tag example name 2\" ], \"title\": \"Title example\"}";
    @BeforeAll
    public static void initiate(){
        RestAssured.baseURI = "http://localhost:8082";
        RestAssured.port = 8082;
    }
    @Test
    public void createNewsTest(){
        Response response= RestAssured.given()
                .contentType("application/json")
                .body(newsExample)
                .when()
                .request("POST","/news")
                .then()
                .statusCode(201)
                .body("title",equalTo("Title example")).extract().response();
        deleteTmp(response);
    }
    @Test
    public void findNewsByIdTest(){
        Response response=createTmpNews();
        given().request("GET","/news/"+response.jsonPath().getLong("id"))
                .then()
                .statusCode(200)
                .body("title",equalTo("Title example"));
        deleteTmp(response);
    }
    @Test
    public void testMethodsGetTagCommentAuthorByNewsId(){
        System.out.println("Test for methods to get Tag/Comment/Author By NewsId Started.");
        Response response=createTmpNews();
        Long newsId = response.jsonPath().getLong("id");
        String bodyComment = commentExample+newsId+"}";
        List<TagDtoResponse> tags = response.jsonPath().getList("tagDtoResponseList",TagDtoResponse.class);
        List<String> tagNames = new ArrayList<>();
        for (TagDtoResponse tag : tags) {
            tagNames.add(tag.name());
        }
        given()
                .request("GET","/news/"+response.jsonPath().getLong("id")+"/author")
                .then()
                .statusCode(200)
                .body("name", equalTo("Author example"));
        Response response1 = RestAssured.given()
                .request("GET","/news/"+response.jsonPath().getLong("id")+"/tag")
                .then()
                .statusCode(200)
                .body( "name",equalTo(tagNames)).extract().response();
        Integer commentID = RestAssured.given()
                .contentType("application/json")
                .body(bodyComment)
                .when()
                .request("POST","/comment")
                .then()
                .statusCode(201)
                .body("content", equalTo("Comment example"))
                .body("newsId",equalTo(response.jsonPath().getInt("id")))
                .extract().path("id");
        List<String> commentNames = new ArrayList<>();
        commentNames.add("Comment example");
        given().request("GET","/news/"+newsId+"/comment")
                        .then().statusCode(200)
                        .body("content",equalTo(commentNames));
        deleteTmp(response);
        System.out.println("Test for methods to get Tag/Comment/Author By NewsId finished successfully.");
    }
    @Test
    public void testSearchNewsByParams(){
        System.out.println("Test for methods to get News by params Started.");
        Response response=createTmpNews();
        List<String> newsExampleChecker = new ArrayList<>();
        newsExampleChecker.add("Title example");
        List<Integer> newsIdList =new ArrayList<>();
        newsIdList.add(response.jsonPath().getInt("id"));
        List<Integer> tagIds = new ArrayList<>((response.jsonPath().getList("tagDtoResponseList.id")));
        given().contentType("application/json")
                .queryParam("Title", "Title example")
                .when().request("GET", "/news/byParams/")
                .then().body("title", equalTo(newsExampleChecker)).statusCode(200)
                .body("id",equalTo(newsIdList));
        newsExampleChecker.set(0,"Author example");
        given().contentType("application/json")
                .queryParam("Author", "Author example")
                .when().request("GET", "/news/byParams")
                .then().body("authorDto.name", equalTo(newsExampleChecker)).statusCode(200)
                .body("id",equalTo(newsIdList));;
        newsExampleChecker.set(0,"Content example");
        given().contentType("application/json")
                .queryParam("Content","Content example")
                .when().request("GET", "/news/byParams")
                .then().body("content", equalTo(newsExampleChecker)).statusCode(200)
                .body("id",equalTo(newsIdList));
        StringBuilder body = new StringBuilder();
        tagIds.forEach(a-> body.append("Tag_Ids="+a+"&"));
        List<Integer> tagIdsForTagsParam =new ArrayList<>();
        tagIds.forEach(a->tagIdsForTagsParam.add(response.jsonPath().getInt("id")));
        given().contentType("application/json")
                .when().request("GET", "/news/byParams?"+body)
                .then().body("id",equalTo(tagIdsForTagsParam));
        deleteTmp(response);
        System.out.println("Test for methods to get News by params finished successfully");
    }
    public static Response createTmpNews(){
         Response response= RestAssured.given()
                .contentType("application/json")
                .body(newsExample)
                .when()
                .request("POST","/news")
                .then()
                .statusCode(201)
                .body("title",equalTo("Title example")).extract().response();
         System.out.println(response.asPrettyString());
        return response;
    }
    public static void deleteTmp(Response response){
        given().delete("/news/"+response.jsonPath().getLong("id")).then().statusCode(204);
        given().delete("/author/"+response.jsonPath().getLong("authorDto.id")).then().statusCode(204);
        List<Integer> tagIds = new ArrayList<>((response.jsonPath().getList("tagDtoResponseList.id")));
        tagIds.forEach(a->given().request("DELETE","/tag/"+a).then().statusCode(204));
        System.out.println("Tmp info deleted successfully");
    }
}
