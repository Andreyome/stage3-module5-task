package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Hateoas.HateoasHelper;
import com.mjc.school.service.TagServInterface;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@RestController
@RequestMapping(value = "/tag",  produces = {"application/JSON"})
@Api(produces = "application/JSON", value = "CRUD operations with Tags")
public class TagsController implements BaseController<TagDtoRequest, TagDtoResponse,Long> {
    private TagServInterface tagsService;
    @Autowired
    public  TagsController(TagServInterface tagsService){
        this.tagsService=tagsService;
    }
    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all tags", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved all tags"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public List<TagDtoResponse> readAll(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", required = false,defaultValue = "5") Integer limit,
                                        @RequestParam(value = "sortBy",required = false,defaultValue = "name:desc") String sortBy) {
        return tagsService.readAll( page, limit, sortBy);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get tags by Id", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved tag by Id"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public EntityModel<TagDtoResponse> readById(@PathVariable Long id) {
        EntityModel<TagDtoResponse> result = EntityModel.of(tagsService.readById(id));
        HateoasHelper.addTagLinks(result);
        return result;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create tag", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully created tag"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public EntityModel<TagDtoResponse>  create(@RequestBody TagDtoRequest createRequest) {
        EntityModel<TagDtoResponse> result = EntityModel.of(tagsService.create(createRequest));
        HateoasHelper.addTagLinks(result);
        return result;
    }

    @Override
    @PutMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update tag", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully updated tag"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public EntityModel<TagDtoResponse>  update(@PathVariable Long id,@RequestBody TagDtoRequest updateRequest) {
        EntityModel<TagDtoResponse> result = EntityModel.of(tagsService.update(id,updateRequest));
        HateoasHelper.addTagLinks(result);
        return result;
    }

    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete tag by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "Successfully deleted tag by Id"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Internal resource not found")
    })
    public void deleteById(@PathVariable Long id) {
        tagsService.deleteById(id);
    }

}
