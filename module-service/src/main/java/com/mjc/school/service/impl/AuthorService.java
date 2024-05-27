package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.AuthorRepositoryImpl;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.AuthorServInterface;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validate.Validator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exception.ServiceExceptionCode.AUTHOR_ID_DOES_NOT_EXIST;

@Service
@Transactional
public class AuthorService implements AuthorServInterface {
    private final AuthorRepositoryImpl authorRepositoryImpl;
    private final AuthorMapper mapper = Mappers.getMapper(AuthorMapper.class);
    private final Validator validator;

    @Autowired
    protected AuthorService(AuthorRepositoryImpl authorRepositoryImpl,Validator validator) {
        this.authorRepositoryImpl = authorRepositoryImpl;
        this.validator=validator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDtoResponse> readAll(Integer page, Integer limit, String sortBy) {
        return mapper.authorModelListToDto(authorRepositoryImpl.readAll(page, limit, sortBy));
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDtoResponse readById(Long id) {
        Optional<AuthorModel> authorModel = authorRepositoryImpl.readById(id);
        if (authorModel.isPresent()) return mapper.authorModelToDto(authorModel.get());
        else throw new NotFoundException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getErrorMessage(),id));
    }

    @Override
    @Transactional
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        try {
            return mapper.authorModelToDto(authorRepositoryImpl.create(mapper.authorDtoToModel(validator.validateAuthor(createRequest))));
        }
        catch (DataIntegrityViolationException e)
        {
            throw  new ValidationException("Author name is already taken");
        }
    }

    @Override
    @Transactional
    public AuthorDtoResponse update(Long id,AuthorDtoRequest updateRequest) {
        if(!authorRepositoryImpl.existById(id)){
            throw new NotFoundException("No author with provided id found");
        }
        try {
            return mapper.authorModelToDto(authorRepositoryImpl.update(id, mapper.authorDtoToModel(validator.validateAuthor(updateRequest))));
        }
        catch (DataIntegrityViolationException e)
        {
            throw  new ValidationException("Author name is already taken");
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return authorRepositoryImpl.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDtoResponse getAuthorByNewsId(Long id) {
        return mapper.authorModelToDto(authorRepositoryImpl.getAuthorByNewsId(id).orElse(null));
    }
}
