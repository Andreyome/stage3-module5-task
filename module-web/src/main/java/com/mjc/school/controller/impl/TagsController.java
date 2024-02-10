package com.mjc.school.controller.impl;

import com.mjc.school.controller.RestTagsController;
import com.mjc.school.service.TagServInterface;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@RestController
@RequestMapping(value = "/tag", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class TagsController implements RestTagsController {
    private TagServInterface tagsService;
    @Autowired
    public  TagsController(TagServInterface tagsService){
        this.tagsService=tagsService;
    }
    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> readAll(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", required = false,defaultValue = "5") Integer limit,
                                        @RequestParam(value = "sortBy",required = false,defaultValue = "name") String sortBy) {
        return tagsService.readAll( page, limit, sortBy);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse readById(@PathVariable Long id) {
        return tagsService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDtoResponse create(@RequestBody TagDtoRequest createRequest) {
        return tagsService.create(createRequest);
    }

    @Override
    @PutMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse update(@PathVariable Long id,@RequestBody TagDtoRequest updateRequest) {
        return tagsService.update(updateRequest);
    }

    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        tagsService.deleteById(id);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> getTagsByNewsId(@PathVariable Long id) {
        return tagsService.readByNewsId(id);
    }
}
