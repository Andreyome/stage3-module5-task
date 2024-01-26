package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = TagMapper.class)
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
    @Mappings({
            @Mapping(target = "authorId", ignore = true),
    @Mapping(target = "tagDtoResponseList",source = "tagModelList")})

    NewsDtoResponse newsToDto(NewsModel newsModel);

    @Mappings({
            @Mapping(target = "authorModel", ignore = true),
            @Mapping(target = "tagModelList", ignore = true),
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)})
    NewsModel newsDtoToModel(NewsDtoRequest newsDtoRequest);
}
