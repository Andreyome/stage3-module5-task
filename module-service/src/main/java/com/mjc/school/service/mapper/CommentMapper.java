package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.CommentModel;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    List<CommentDtoResponse> commentListToDto(List<CommentModel> input);
    @Mapping(target = "createDate", expression = "java(input.getCreateDate().format(java.time.format.DateTimeFormatter.ISO_DATE_TIME))")
    @Mapping(target = "lastUpdateDate", expression = "java(input.getLastUpdateDate().format(java.time.format.DateTimeFormatter.ISO_DATE_TIME))")
    @Mapping(target = "newsId",expression = "java(input.getNewsModel().getId())")
    CommentDtoResponse commentToDto(CommentModel input);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "newsModel", ignore = true)
    @Mapping(target = "id", ignore = true)
    CommentModel commentDtoToModel(CommentDtoRequest input);
}
