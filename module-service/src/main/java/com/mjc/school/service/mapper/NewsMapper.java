package com.mjc.school.service.mapper;

import com.mjc.school.repository.CommentsRepository;
import com.mjc.school.repository.impl.AuthorRepositoryImpl;
import com.mjc.school.repository.impl.TagRepositoryImpl;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = {TagMapper.class, CommentMapper.class, AuthorMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class NewsMapper {
    @Autowired
    protected AuthorRepositoryImpl authorRepositoryImpl;
    @Autowired
    protected CommentsRepository commentsRepositoryImpl;
    @Autowired
    protected TagRepositoryImpl tagRepositoryImpl;
    protected DateTimeFormatter dateTimeFormatter;

    public abstract List<NewsDtoResponse> modelListToDtoList(List<NewsModel> modelList);

    @Mapping(source = "authorModel", target = "authorDto")
    @Mapping(source = "tagModelList", target = "tagDtoResponseList")
    @Mapping(source = "commentsModelList", target = "commentDtoResponseList")
    @Mapping(target = "dtoResponseList", ignore = true)
    @Mapping(target = "mentDtoResponseList", ignore = true)
    @Mapping(target = "createDate", expression = "java(newsModel.getCreateDate().format(dateTimeFormatter.ISO_DATE_TIME))")
    @Mapping(target = "lastUpdateDate", expression = "java(newsModel.getLastUpdateDate().format(dateTimeFormatter.ISO_DATE_TIME))")
    public abstract NewsDtoResponse newsToDto(NewsModel newsModel);


    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "commentsModelList", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tagModelList", expression = "java(newsDtoRequest.tagNames().stream().map(name->tagRepositoryImpl.readTagByName(name).get()).toList())")
    @Mapping(target = "authorModel", expression = "java(authorRepositoryImpl.readByName(newsDtoRequest.authorName()).get())")
    public abstract NewsModel newsDtoToModel(NewsDtoRequest newsDtoRequest);
}
