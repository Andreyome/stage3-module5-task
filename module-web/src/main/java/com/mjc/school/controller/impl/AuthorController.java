package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.RestAuthorController;
import com.mjc.school.service.AuthorServInterface;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping(value = "/author", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class AuthorController implements RestAuthorController {
    private final AuthorServInterface authorService;

    @Autowired
    public AuthorController(AuthorServInterface authorService) {
        this.authorService = authorService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDtoResponse> readAll(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                           @RequestParam(value = "limit", required = false,defaultValue = "5") Integer limit,
                                           @RequestParam(value = "sortBy",required = false,defaultValue = "name") String sortBy) {
        return authorService.readAll( page,  limit, sortBy);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse readById(@PathVariable Long id) {
        return authorService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDtoResponse create(@RequestBody AuthorDtoRequest createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    @PutMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse update(@PathVariable Long id, @RequestBody AuthorDtoRequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse getAuthorByNewsId(@PathVariable Long id) {
        return authorService.getAuthorByNewsId(id);
    }
}
