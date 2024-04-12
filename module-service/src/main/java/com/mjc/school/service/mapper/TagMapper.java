package com.mjc.school.service.mapper;


import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    List<TagDtoResponse> tagListToDto(List<TagModel> tagModelList);
    TagDtoResponse tagToDto(TagModel tagModel);

    @Mapping(target = "newsModelList", ignore = true)
    @Mapping(target = "id",ignore = true)
    TagModel tagDtoToModel(TagDtoRequest newsDtoRequest);
}
