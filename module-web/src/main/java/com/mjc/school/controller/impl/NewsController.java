package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.RestNewsController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.NewsServInterface;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping(value = "/news", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class NewsController implements RestNewsController {
    private final NewsServInterface newsService;

    @Autowired
    public NewsController(NewsServInterface newsService) {
        this.newsService = newsService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> readAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit,
                                         @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy) {
        return newsService.readAll(page, limit, sortBy);
    }

    @Override
    @GetMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse readById(@PathVariable Long id) {
        return newsService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDtoResponse create(@RequestBody NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    @PutMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse update(@PathVariable Long id, @RequestBody NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    @DeleteMapping(value = "/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> readNewsByParams(@RequestParam(required = false, defaultValue = "null") Optional<List<Long>> tagsIds,
                                                  @RequestParam(required = false, defaultValue = "null") Optional<List<String>> tagsNames,
                                                  @RequestParam(required = false, defaultValue = "null") Optional<String> authorName,
                                                  @RequestParam(required = false, defaultValue = "null") Optional<String> title,
                                                  @RequestParam(required = false, defaultValue = "null") Optional<String> content) {
        return newsService.readNewsByParams(tagsIds, tagsNames, authorName, title, content);
    }
}
