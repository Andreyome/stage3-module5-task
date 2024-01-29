package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository) {
        this.newsRepository = newsRepository;
    }


    @Override
    public List<NewsDtoResponse> readAll() {
        return newsRepository.readAll().stream().map(NewsMapper.INSTANCE::newsToDto).collect(Collectors.toList());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> newsModelOptional = newsRepository.readById(id);
        if (newsModelOptional.isPresent()) {
            return NewsMapper.INSTANCE.newsToDto(newsModelOptional.get());
        } else throw new RuntimeException("No news with such id found");
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        NewsModel newsModel = NewsMapper.INSTANCE.newsDtoToModel(createRequest);
        newsModel.setCreateDate(LocalDateTime.now());
        newsModel.setLastUpdateDate(LocalDateTime.now());
        newsRepository.create(newsModel);
        return NewsMapper.INSTANCE.newsToDto(newsModel);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        NewsModel updatedNews = NewsMapper.INSTANCE.newsDtoToModel(updateRequest);
        updatedNews.setLastUpdateDate(LocalDateTime.now());
        updatedNews.setCreateDate(newsRepository.readById(updatedNews.getId()).get().getCreateDate());
        return NewsMapper.INSTANCE.newsToDto(newsRepository.update(updatedNews));
    }

    @Override
    public boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }

}
