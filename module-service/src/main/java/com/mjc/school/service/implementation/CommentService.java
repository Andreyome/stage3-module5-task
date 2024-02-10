package com.mjc.school.service.implementation;

import com.mjc.school.repository.CommentsRepInterface;
import com.mjc.school.repository.implementation.CommentRepository;
import com.mjc.school.repository.model.impl.CommentModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.CommentServInterface;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements CommentServInterface {
    private final CommentMapper mapper;
    private final CommentsRepInterface repository;



    @Autowired
    public CommentService(CommentMapper mapper,CommentsRepInterface repository){
        this.mapper=mapper;
        this.repository=repository;
    }
    @Override
    public List<CommentDtoResponse> readAll(Integer page, Integer limit, String sortBy) {
        return mapper.commentListToDto(repository.readAll(page,limit, sortBy));
    }

    @Override
    public CommentDtoResponse readById(Long id) {
        if(repository.readById(id).isPresent()) {
            return mapper.commentToDto(repository.readById(id).get());
        }
        else {
            throw new EntityNotFoundException("Comment with provided Id not Found!");
        }
    }

    @Override
    public CommentDtoResponse create(CommentDtoRequest createRequest) {
        return mapper.commentToDto(repository.create(mapper.commentDtoToModel(createRequest)));
    }

    @Override
    public CommentDtoResponse update(CommentDtoRequest updateRequest) {
        if(repository.existById(updateRequest.id())){
            return mapper.commentToDto(repository.create(mapper.commentDtoToModel(updateRequest)));
        }
        else{
            throw new EntityNotFoundException("Comment with provided Id not Found!");
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if(repository.existById(id)){
            return repository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("Comment with provided Id not Found!");
        }
    }

    @Override
    public List<CommentDtoResponse> readByNewsId(Long id) {
        return mapper.commentListToDto(repository.readByNewsId(id));
    }
}
