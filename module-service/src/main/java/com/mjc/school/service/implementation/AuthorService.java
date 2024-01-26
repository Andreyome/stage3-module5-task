package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseRepository<AuthorModel, Long> authorRepository;
    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorRepository.readAll().stream().map(AuthorMapper.INSTANCE::authorModelToDto).toList();
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        Optional<AuthorModel> authorModel = authorRepository.readById(id);
        if (authorModel.isPresent()) return AuthorMapper.INSTANCE.authorModelToDto(authorModel.get());
        else throw new RuntimeException("Author with provided id does not exist");
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return AuthorMapper.INSTANCE.authorModelToDto(authorRepository.create(AuthorMapper.INSTANCE.authorDtoToModel(createRequest)));
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        return AuthorMapper.INSTANCE.authorModelToDto(authorRepository.update(AuthorMapper.INSTANCE.authorDtoToModel(updateRequest)));
    }

    @Override
    public boolean deleteById(Long id) {
        return authorRepository.deleteById(id);
    }
}
