package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.AuthorServInterface;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
@RestController
@RequestMapping(value = "/author", produces = {"application/JSON"})
@Api (produces = "application/JSON", value = "CRUD operations with Authors")
@Validated
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse,Long> {
    private final AuthorServInterface authorService;

    @Autowired
    public AuthorController(AuthorServInterface authorService) {
        this.authorService = authorService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value= "Get all authors",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved all authors"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")

    })
    public List<AuthorDtoResponse> readAll(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                           @RequestParam(value = "limit", required = false,defaultValue = "5") Integer limit,
                                           @RequestParam(value = "sortBy",required = false,defaultValue = "name:desc") String sortBy) {
        return authorService.readAll( page,  limit, sortBy);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value= "Get author by Id",response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved author"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Internal resource not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public AuthorDtoResponse readById(@PathVariable Long id) {
        return authorService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value= "Create author",response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully created author"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Internal resource not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public AuthorDtoResponse create(@Valid @RequestBody AuthorDtoRequest createRequest) {
        return authorService.create( createRequest);
    }

    @Override
    @PutMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value= "Update author",response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully updated author"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Internal resource not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public AuthorDtoResponse update(@PathVariable Long id, @RequestBody AuthorDtoRequest updateRequest) {
        return authorService.update(id, updateRequest);
    }

    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value= "Delete authors")
    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "Successfully deleted author"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Internal resource not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    public static void addHateoas(AuthorDtoResponse authorDtoResponse, Long id){
        authorDtoResponse.add(linkTo(methodOn(AuthorController.class).readById(id)).withSelfRel());

    }
}
