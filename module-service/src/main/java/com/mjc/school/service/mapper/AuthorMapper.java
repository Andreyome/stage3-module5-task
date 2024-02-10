package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    List <AuthorDtoResponse> authorModelListToDto(List<AuthorModel> list);
    AuthorDtoResponse authorModelToDto(AuthorModel model);
    @Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)})
    AuthorModel authorDtoToModel(AuthorDtoRequest request);

}
