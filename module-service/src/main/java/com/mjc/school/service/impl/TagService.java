package com.mjc.school.service.impl;

import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.TagServInterface;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.mapper.TagMapper;
import com.mjc.school.service.validate.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class TagService implements TagServInterface {
    private final TagRepository tagRepository;
    private final TagMapper mapper;
    private final Validator validator;

    @Autowired
    public TagService(TagRepository tagRepository, TagMapper mapper, Validator validator) {
        this.tagRepository = tagRepository;
        this.mapper = mapper;
        this.validator=validator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDtoResponse> readAll(Integer page, Integer limit, String sortBy) {
        return mapper.tagListToDto(tagRepository.readAll(page, limit, sortBy));
    }

    @Override
    @Transactional(readOnly = true)
    public TagDtoResponse readById(Long id) {
        Optional<TagModel> tagModelOptional = tagRepository.readById(id);
        if (tagModelOptional.isPresent()) {
            return mapper.tagToDto(tagModelOptional.get());
        } else throw new RuntimeException("No tag with such id found");
    }

    @Override
    @Transactional
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return mapper.tagToDto(tagRepository.create(mapper.tagDtoToModel(validator.validateTag(createRequest))));
    }

    @Override
    @Transactional
    public TagDtoResponse update(Long id, TagDtoRequest updateRequest) {
        return mapper.tagToDto(tagRepository.update(id, mapper.tagDtoToModel(validator.validateTag(updateRequest))));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return tagRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDtoResponse> readByNewsId(Long id) {
        return mapper.tagListToDto(tagRepository.readByNewsId(id));
    }
}
