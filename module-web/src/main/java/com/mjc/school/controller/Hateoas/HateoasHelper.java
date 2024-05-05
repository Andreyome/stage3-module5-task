package com.mjc.school.controller.Hateoas;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class HateoasHelper {
    private HateoasHelper(){}

    public static void addAuthorLinks(EntityModel<AuthorDtoResponse> dtoModel){
        AuthorDtoResponse tmp = dtoModel.getContent();
        if (tmp == null) return;
        dtoModel.add(linkTo(methodOn(AuthorController.class).readById(tmp.id())).withSelfRel());
        dtoModel.add(linkTo(methodOn(NewsController.class).readNewsByParams(null,null,tmp.name(),null,null)).withRel("news"));
    }
    public static void addCommentLinks(EntityModel<CommentDtoResponse> dtoModel){
        CommentDtoResponse tmp = dtoModel.getContent();
        if (tmp == null) return;
    }
    public static void addNewsLinks(EntityModel<NewsDtoResponse> dtoModel){
        NewsDtoResponse tmp = dtoModel.getContent();
        if (tmp == null) return;
        dtoModel.add(linkTo(methodOn(NewsController.class).readById(tmp.id())).withSelfRel());
        dtoModel.add(linkTo(methodOn(AuthorController.class).readById(tmp.id())).withRel("author"));
        dtoModel.add(linkTo(methodOn(NewsController.class).getTagsByNewsId(tmp.id())).withRel("tags"));
        dtoModel.add(linkTo(methodOn(NewsController.class).getCommentsByNewsId(tmp.id())).withRel("comments"));

    }
    public static void addTagLinks(EntityModel<TagDtoResponse> dtoModel){
        TagDtoResponse tmp = dtoModel.getContent();
        if (tmp == null) return;
        dtoModel.add(linkTo(methodOn(NewsController.class).readNewsByParams(List.of(tmp.id()),null,tmp.name(),null,null)).withRel("news"));

    }

}
