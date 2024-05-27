package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Hateoas.HateoasHelper;
import com.mjc.school.service.*;
import com.mjc.school.service.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public EntityModel<NewsDtoResponse> readById(@PathVariable Long id) {
        EntityModel<NewsDtoResponse> result = EntityModel.of(newsService.readById(id));
        HateoasHelper.addNewsLinks(result);
        return result;
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
    public EntityModel<NewsDtoResponse> create(@RequestBody NewsDtoRequest createRequest) {
        EntityModel<NewsDtoResponse> result = EntityModel.of(newsService.create(createRequest));
        HateoasHelper.addNewsLinks(result);
        return result;
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
    public EntityModel<NewsDtoResponse> update(@PathVariable Long id, @RequestBody NewsDtoRequest updateRequest) {
        EntityModel<NewsDtoResponse> result = EntityModel.of(newsService.update(id,updateRequest));
        HateoasHelper.addNewsLinks(result);
        return result;
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
    public List<EntityModel<TagDtoResponse>> getTagsByNewsId(@PathVariable Long id) {
        List<TagDtoResponse> tagDto = tagService.readByNewsId(id);
        List<EntityModel<TagDtoResponse>> result =new ArrayList<>();
        tagDto.forEach(tagDtoResponse ->result.add(EntityModel.of(tagDtoResponse)));
        result.forEach(HateoasHelper::addTagLinks);
        return result;
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
    public List<EntityModel<CommentDtoResponse>> getCommentsByNewsId(@PathVariable Long id) {
        List<EntityModel<CommentDtoResponse>> result =new ArrayList<>();
        commentService.readByNewsId(id).forEach(tagDtoResponse ->result.add(EntityModel.of(tagDtoResponse)));
        result.forEach(HateoasHelper::addCommentLinks);
        return result;
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
    public EntityModel<AuthorDtoResponse> getAuthorByNewsId(@PathVariable Long id) {
        EntityModel<AuthorDtoResponse> result = EntityModel.of(authorService.getAuthorByNewsId(id));
        HateoasHelper.addAuthorLinks(result);
        return result;
    }


}
