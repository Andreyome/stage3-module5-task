package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService) {
        this.newsService = newsService;
    }

    @Override
    @CommandHandler(value = "readAllNews")
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    @Override
    @CommandHandler(value = "readNewsById")
    public NewsDtoResponse readById(@CommandParam("newsId") Long id) {
        return newsService.readById(id);
    }

    @Override
    @CommandHandler(value = "createNews")
    public NewsDtoResponse create(@CommandBody NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    @CommandHandler(value = "updateNews")
    public NewsDtoResponse update(@CommandBody NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    @CommandHandler(value = "deleteNews")
    public boolean deleteById(@CommandParam("newsId") Long id) {
        return newsService.deleteById(id);
    }
}
