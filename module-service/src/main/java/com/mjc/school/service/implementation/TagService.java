package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.TagRepInterface;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.TagServInterface;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TagService implements TagServInterface {
    private final TagRepInterface tagRepository;
    private final TagMapper mapper;
    @Autowired
    public TagService(TagRepInterface tagRepository,TagMapper mapper){this.tagRepository=tagRepository;this.mapper=mapper;}
    @Override
    public List<TagDtoResponse> readAll(Integer page, Integer limit, String sortBy) {
        return mapper.modelListToDto(tagRepository.readAll(page,limit,sortBy));
    }

    @Override
    public TagDtoResponse readById(Long id) {
        Optional<TagModel> tagModelOptional = tagRepository.readById(id);
        if (tagModelOptional.isPresent()) {
            return mapper.tagToDto(tagModelOptional.get());
        } else throw new RuntimeException("No tag with such id found");
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return mapper.tagToDto(tagRepository.create(mapper.tagDtoToModel(createRequest)));
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return mapper.tagToDto(tagRepository.update(mapper.tagDtoToModel(updateRequest)));
    }

    @Override
    public boolean deleteById(Long id) {
        return tagRepository.deleteById(id);
    }

    @Override
    public List<TagDtoResponse> readByNewsId(Long id) {
        return mapper.modelListToDto(tagRepository.readByNewsId(id));
    }
}
