package com.mjc.school.service.implementation;

import com.mjc.school.repository.AuthorRepInterface;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.AuthorServInterface;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service("authorService")
public class AuthorService implements AuthorServInterface {
    private final AuthorRepInterface authorRepository;
    private final AuthorMapper mapper;
    @Autowired
    public AuthorService(AuthorRepInterface authorRepository,AuthorMapper mapper){
        this.authorRepository = authorRepository;
        this.mapper=mapper;
    }

    @Override
    public List<AuthorDtoResponse> readAll(Integer page, Integer limit, String sortBy) {
        return mapper.authorModelListToDto(authorRepository.readAll(page,limit,sortBy));
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        Optional<AuthorModel> authorModel = authorRepository.readById(id);
        if (authorModel.isPresent()) return mapper.authorModelToDto(authorModel.get());
        else throw new RuntimeException("Author with provided id does not exist");
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return mapper.authorModelToDto(authorRepository.create(mapper.authorDtoToModel(createRequest)));
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        return mapper.authorModelToDto(authorRepository.update(mapper.authorDtoToModel(updateRequest)));
    }

    @Override
    public boolean deleteById(Long id) {
        return authorRepository.deleteById(id);
    }

    @Override
    public AuthorDtoResponse getAuthorByNewsId(Long id) {
        return mapper.authorModelToDto(authorRepository.getAuthorByNewsId(id).orElse(null));
    }
}
