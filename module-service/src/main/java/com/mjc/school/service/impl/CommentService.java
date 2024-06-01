package com.mjc.school.service.impl;

import com.mjc.school.repository.CommentsRepository;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.CommentModel;
import com.mjc.school.service.CommentServInterface;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.CommentMapper;
import com.mjc.school.service.validate.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.mjc.school.service.exception.ServiceExceptionCode.COMMENT_ID_DOES_NOT_EXIST;

@Service
public class CommentService implements CommentServInterface {
    private final CommentMapper mapper;
    private final CommentsRepository commentRepository;
    private final Validator validator;
    private final NewsRepository newsRepository;


    @Autowired
    public CommentService(CommentMapper mapper, CommentsRepository commentRepository, Validator validator, NewsRepository newsRepository) {
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.validator = validator;
        this.newsRepository = newsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDtoResponse> readAll(Integer page, Integer limit, String sortBy) {
        return mapper.commentListToDto(commentRepository.readAll(page, limit, sortBy));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDtoResponse readById(Long id) {
        if (commentRepository.readById(id).isPresent()) {
            return mapper.commentToDto(commentRepository.readById(id).get());
        } else {
            throw new NotFoundException(String.format(COMMENT_ID_DOES_NOT_EXIST.getErrorMessage(), id));
        }
    }

    @Override
    @Transactional
    public CommentDtoResponse create(CommentDtoRequest createRequest) {
        validator.validateComment(createRequest);
        try {
            CommentModel commentModel = mapper.commentDtoToModel(createRequest);
            commentModel.setNewsModel(newsRepository.readById(createRequest.newsId()).get());
            return mapper.commentToDto(commentRepository.create(commentModel));
        } catch (RuntimeException e) {
            throw new NotFoundException("No news with provided id.");
        }
    }

    @Override
    @Transactional
    public CommentDtoResponse update(Long id, CommentDtoRequest updateRequest) {
        if (commentRepository.existById(id)) {
            return mapper.commentToDto(commentRepository.update(id, mapper.commentDtoToModel(validator.validateComment(updateRequest))));
        } else {
            throw new EntityNotFoundException("Comment with provided Id not Found!");
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (commentRepository.existById(id)) {
            return commentRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Comment with provided Id not Found!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDtoResponse> readByNewsId(Long id) {
        return mapper.commentListToDto(commentRepository.readByNewsId(id));
    }
}
