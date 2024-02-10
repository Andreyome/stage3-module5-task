package com.mjc.school.service.mapper;


import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    List<TagDtoResponse> modelListToDto(List<TagModel> tagModelList);
    TagDtoResponse tagToDto(TagModel tagModel);

    @Mapping(target = "newsModelList", ignore = true)
    TagModel tagDtoToModel(TagDtoRequest newsDtoRequest);
}
