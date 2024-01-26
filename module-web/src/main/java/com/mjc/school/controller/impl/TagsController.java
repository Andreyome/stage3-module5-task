package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
@Component
@Controller
public class TagsController implements BaseController<TagDtoRequest, TagDtoResponse,Long> {
    private BaseService<TagDtoRequest,TagDtoResponse,Long> tagsService;
    @Autowired
    public void TagsController(BaseService<TagDtoRequest,TagDtoResponse,Long> tagsService){
        this.tagsService=tagsService;
    }
    @Override
    @CommandHandler(value ="readAllTags")
    public List<TagDtoResponse> readAll() {
        return tagsService.readAll();
    }

    @Override
    @CommandHandler(value ="readTagsById")
    public TagDtoResponse readById(@CommandParam("tagsId")Long id) {
        return tagsService.readById(id);
    }

    @Override
    @CommandHandler(value ="createTags")
    public TagDtoResponse create(@CommandBody TagDtoRequest createRequest) {
        return tagsService.create(createRequest);
    }

    @Override
    @CommandHandler(value ="updateTags")
    public TagDtoResponse update(@CommandBody TagDtoRequest updateRequest) {
        return tagsService.update(updateRequest);
    }

    @Override
    @CommandHandler(value ="deleteTagsById")
    public boolean deleteById(@CommandParam("tagsId")Long id) {
        return tagsService.deleteById(id);
    }
}
