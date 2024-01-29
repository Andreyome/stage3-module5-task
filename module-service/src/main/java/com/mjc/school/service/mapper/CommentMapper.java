package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.CommentModel;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    List<CommentDtoResponse> commentListToDto (List<CommentModel> input);

    CommentDtoResponse commentToDto(CommentModel input);
@Mapping(target = "createDate", ignore = true)
@Mapping(target = "lastUpdateDate",ignore = true)
@Mapping(target = "newsModel",ignore = true)
    CommentModel commentDtoToModel(CommentDtoRequest input);
}
