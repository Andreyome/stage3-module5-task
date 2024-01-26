package com.mjc.school.service.mapper;


import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDtoResponse tagToDto(TagModel tagModel);

    @Mappings({
            @Mapping(target = "newsModelList", ignore = true)})
    TagModel tagDtoToModel(TagDtoRequest newsDtoRequest);
}
