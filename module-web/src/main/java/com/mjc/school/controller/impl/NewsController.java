package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.*;
import com.mjc.school.service.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping(value = "/news", produces = {"application/JSON"})
@Api(produces = "application/JSON", value = "CRUD operations with News")
public class NewsController implements BaseController<NewsDtoRequest,NewsDtoResponse,Long> {
    private final NewsServInterface newsService;
    private final TagServInterface tagService;
    private final CommentServInterface commentService;
    private final AuthorServInterface authorService;

    @Autowired
    public NewsController(NewsServInterface newsService,
                          TagServInterface tagService,
                          CommentServInterface commentService,
                          AuthorServInterface authorService) {
        this.newsService = newsService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.authorService = authorService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all news", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all news"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public List<NewsDtoResponse> readAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit,
                                         @RequestParam(value = "sortBy", required = false, defaultValue = "createDate:desc") String sortBy) {
        return newsService.readAll(page, limit, sortBy);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get news by Id", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved news by Id"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public NewsDtoResponse readById(@PathVariable Long id) {
        return newsService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create news", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created news"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public NewsDtoResponse create(@RequestBody NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    @PutMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update news", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated news"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public NewsDtoResponse update(@PathVariable Long id, @RequestBody NewsDtoRequest updateRequest) {
        return newsService.update(id,updateRequest);
    }

    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete news")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted news by Id"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public void deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
    }

    @GetMapping(value = "/byParams")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get news by params", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved news by provided Params"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public List<NewsDtoResponse> readNewsByParams(
            @RequestParam(name = "Tag_Ids", required = false) List<Long> tagsIds,
            @RequestParam(name = "Tag_Names", required = false) List<String> tagsNames,
            @RequestParam(name = "Author", required = false) String authorName,
            @RequestParam(name = "Title", required = false) String title,
            @RequestParam(name = "Content", required = false) String content) {
        return newsService.readNewsByParams(tagsIds, tagsNames, authorName, title, content);
    }

    @GetMapping(value = "/{id:\\d+}/tag")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get tags by news Id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved tag by news Id"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public List<TagDtoResponse> getTagsByNewsId(@PathVariable Long id) {
        return tagService.readByNewsId(id);
    }

    @GetMapping(value = "/{id:\\d+}/comment")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get comments by news Id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved comment by news Id"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")

    })
    public List<CommentDtoResponse> getCommentsByNewsId(@PathVariable Long id) {
        return commentService.readByNewsId(id);
    }
    @GetMapping(value = "/{id:\\d+}/author")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value= "Get author by news Id",response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved author by news Id"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Internal resource not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public AuthorDtoResponse getAuthorByNewsId(@PathVariable Long id) {
        return authorService.getAuthorByNewsId(id);
    }
}
