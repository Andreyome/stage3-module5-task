package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "comments")
public class CommentModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", nullable = false)
    @Size(min = 5, max = 255, message = "Content is incorrect!")
    private String content;
    @Column(name = "createDate", nullable = false)
    @CreatedDate
    private LocalDateTime createDate;
    @Column(name = "lastUpdateDate", nullable = false)
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;
    @ManyToOne
    @JoinColumn(name = "news_id")
    private NewsModel newsModel;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public NewsModel getNewsModel() {
        return newsModel;
    }

    public void setNewsModel(NewsModel newsModel) {
        this.newsModel = newsModel;
    }
}
