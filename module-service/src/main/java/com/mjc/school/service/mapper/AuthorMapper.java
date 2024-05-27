package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    List <AuthorDtoResponse> authorModelListToDto(List<AuthorModel> list);
    @Mapping(target = "createDate", expression = "java(model.getCreateDate().format(java.time.format.DateTimeFormatter.ISO_DATE_TIME))")
    @Mapping(target = "lastUpdateDate", expression = "java(model.getLastUpdateDate().format(java.time.format.DateTimeFormatter.ISO_DATE_TIME))")
    AuthorDtoResponse authorModelToDto(AuthorModel model);
    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)})
    AuthorModel authorDtoToModel(AuthorDtoRequest request);

}
