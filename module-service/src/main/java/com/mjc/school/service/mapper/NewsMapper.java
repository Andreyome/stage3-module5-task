package com.mjc.school.service.mapper;

import com.mjc.school.repository.AuthorRepInterface;
import com.mjc.school.repository.CommentsRepInterface;
import com.mjc.school.repository.TagRepInterface;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel ="spring", uses = {TagMapper.class, CommentMapper.class, AuthorMapper.class})
public abstract class NewsMapper {
    @Autowired
    protected AuthorRepInterface authorRepInterface;
    @Autowired
    protected CommentsRepInterface commentsRepInterface;
    @Autowired
    protected TagRepInterface tagRepInterface;

    public abstract List <NewsDtoResponse> modelListToDtoList(List<NewsModel> modelList);
@Mapping(source = "authorModel",target = "authorDto")
@Mapping(source = "tagModelList",target = "tagDtoResponseList")
@Mapping(source = "commentsModelList",target = "commentDtoResponseList")
    public abstract NewsDtoResponse newsToDto(NewsModel newsModel);


    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "commentsModelList",expression = "java(newsDtoRequest.commentIds().stream().map(id ->commentsRepInterface.readById(id).get()).toList())")
    @Mapping(target = "tagModelList", expression = "java(newsDtoRequest.tagIds().stream().map(id->tagRepInterface.readById(id).get()).toList())")
    @Mapping(target = "authorModel", expression = "java(authorRepInterface.readById(newsDtoRequest.authorId()).get())")
    public abstract NewsModel newsDtoToModel(NewsDtoRequest newsDtoRequest);
}
