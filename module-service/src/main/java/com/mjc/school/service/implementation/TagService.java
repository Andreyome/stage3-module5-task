package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TagService implements BaseService<TagDtoRequest, TagDtoResponse,Long> {
    private BaseRepository<TagModel, Long> tagRepository;
    @Autowired
    public TagService(BaseRepository<TagModel, Long> tagRepository){this.tagRepository=tagRepository;}
    @Override
    public List<TagDtoResponse> readAll() {
        return tagRepository.readAll().stream().map(TagMapper.INSTANCE::tagToDto).collect(Collectors.toList());
    }

    @Override
    public TagDtoResponse readById(Long id) {
        Optional<TagModel> tagModelOptional = tagRepository.readById(id);
        if (tagModelOptional.isPresent()) {
            return TagMapper.INSTANCE.tagToDto(tagModelOptional.get());
        } else throw new RuntimeException("No tag with such id found");
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return TagMapper.INSTANCE.tagToDto(tagRepository.create(TagMapper.INSTANCE.tagDtoToModel(createRequest)));
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return TagMapper.INSTANCE.tagToDto(tagRepository.update(TagMapper.INSTANCE.tagDtoToModel(updateRequest)));
    }

    @Override
    public boolean deleteById(Long id) {
        return tagRepository.deleteById(id);
    }
}
