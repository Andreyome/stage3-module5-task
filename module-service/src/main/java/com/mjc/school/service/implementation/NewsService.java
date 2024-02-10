package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsRepInterface;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.NewsServInterface;
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
public class NewsService implements NewsServInterface {
    private final NewsRepInterface newsRepository;
    private final NewsMapper mapper;


    @Autowired
    public NewsService(NewsRepInterface newsRepository,NewsMapper mapper) {
        this.newsRepository = newsRepository;
        this.mapper=mapper;
    }


    @Override
    public List<NewsDtoResponse> readAll(Integer page, Integer limit, String sortBy) {
        return newsRepository.readAll( page, limit, sortBy).stream().map(mapper::newsToDto).collect(Collectors.toList());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> newsModelOptional = newsRepository.readById(id);
        if (newsModelOptional.isPresent()) {
            return mapper.newsToDto(newsModelOptional.get());
        } else throw new RuntimeException("No news with such id found");
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        NewsModel newsModel = mapper.newsDtoToModel(createRequest);
        newsModel.setCreateDate(LocalDateTime.now());
        newsModel.setLastUpdateDate(LocalDateTime.now());
        newsRepository.create(newsModel);
        return mapper.newsToDto(newsModel);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        NewsModel updatedNews = mapper.newsDtoToModel(updateRequest);
        updatedNews.setLastUpdateDate(LocalDateTime.now());
        updatedNews.setCreateDate(newsRepository.readById(updatedNews.getId()).get().getCreateDate());
        return mapper.newsToDto(newsRepository.update(updatedNews));
    }



    @Override
    public boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }

    @Override
    public List<NewsDtoResponse> readNewsByParams(Optional<List<Long>> tagsIds, Optional<List<String>> tagsNames, Optional<String> authorName, Optional<String> title, Optional<String> content) {
        return mapper.modelListToDtoList(newsRepository.readNewsByParams(tagsIds,tagsNames,authorName,title,content));
    }
}
