package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@Entity
@Component
@Table(name = "News")
public class NewsModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title",nullable = false)
    @Size(min = 5,max = 30,message = "Title is incorrect!")
    private String title;
    @Column(name = "content",nullable = false)
    @Size(min = 5,max = 255,message = "Content is incorrect!")
    private String content;
    @Column(name = "createDate",nullable = false)
    @CreatedDate
    private LocalDateTime createDate;
    @Column(name = "lastUpdateDate",nullable = false)
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel authorModel;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "news_tags",joinColumns =@JoinColumn(name = "news_id" ), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagModel> tagModelList;
    public NewsModel(){}

    public NewsModel(String title, String content, LocalDateTime createDate, LocalDateTime lastUpdateDate, AuthorModel authorModel,List<TagModel> tagModelList) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorModel=authorModel;
        this.tagModelList=tagModelList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NewsModel newsModel)) {
            return false;
        }
        return (Objects.equals(this.id, newsModel.id)) && (this.title.equals(newsModel.title)) && (this.content.equals(newsModel.content)) && (this.lastUpdateDate.equals(newsModel.lastUpdateDate)) && (this.createDate.equals(newsModel.createDate));
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }
    public List<TagModel> getTagModelList(){
        return tagModelList;
    }
}
