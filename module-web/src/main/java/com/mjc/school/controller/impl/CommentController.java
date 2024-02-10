package com.mjc.school.controller.impl;

import com.mjc.school.controller.RestCommentController;
import com.mjc.school.service.CommentServInterface;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@RestController
@RequestMapping(value = "/comment", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class CommentController implements RestCommentController {
    private final CommentServInterface commentService;
    @Autowired
    public CommentController(CommentServInterface commentService){
        this.commentService=commentService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> readAll(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                            @RequestParam(value = "limit", required = false,defaultValue = "5") Integer limit,
                                            @RequestParam(value = "sortBy",required = false,defaultValue = "name") String sortBy) {
        return commentService.readAll(page,limit,sortBy);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse readById(@RequestParam Long id) {
        return commentService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDtoResponse create(CommentDtoRequest createRequest) {
        return commentService.create(createRequest);
    }

    @Override
    @PutMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse update(@PathVariable Long id, @RequestBody CommentDtoRequest updateRequest) {
        return commentService.update(updateRequest);
    }

    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> getCommentsByNewsId(@PathVariable Long id) {
        return commentService.readByNewsId(id);
    }
}
