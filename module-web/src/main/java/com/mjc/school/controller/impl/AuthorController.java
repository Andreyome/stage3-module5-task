package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Hateoas.HateoasHelper;
import com.mjc.school.service.AuthorServInterface;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/author", produces = {"application/JSON"})
@Api(produces = "application/JSON", value = "CRUD operations with Authors")
@Validated
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final AuthorServInterface authorService;

    @Autowired
    public AuthorController(AuthorServInterface authorService) {
        this.authorService = authorService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all authors", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all authors"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")

    })
    public List<AuthorDtoResponse> readAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit,
                                           @RequestParam(value = "sortBy", required = false, defaultValue = "name:desc") String sortBy) {
        return authorService.readAll(page, limit, sortBy);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get author by Id", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved author"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Internal resource not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public EntityModel<AuthorDtoResponse> readById(@PathVariable Long id) {
        EntityModel<AuthorDtoResponse> result = EntityModel.of(authorService.readById(id));
        HateoasHelper.addAuthorLinks(result);
        return result;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create author", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created author"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Internal resource not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public EntityModel<AuthorDtoResponse> create(@RequestBody AuthorDtoRequest createRequest) {
        EntityModel<AuthorDtoResponse> result = EntityModel.of(authorService.create(createRequest));
        HateoasHelper.addAuthorLinks(result);
        return result;
    }

    @Override
    @PatchMapping ("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update author", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated author"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Internal resource not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public EntityModel<AuthorDtoResponse> update(@PathVariable Long id, @RequestBody AuthorDtoRequest updateRequest) {
        EntityModel<AuthorDtoResponse> result = EntityModel.of(authorService.update(id, updateRequest));
        HateoasHelper.addAuthorLinks(result);
        return result;
    }

    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete authors")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted author"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Internal resource not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}
