package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class NewsDtoResponse {
    private Long id;
    private String content;
    private String title;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;
    private List<TagDtoResponse> tagDtoResponseList;

    public NewsDtoResponse(Long id, String title, String content, LocalDateTime createDate, LocalDateTime lastUpdateDate, Long authorId,List<TagDtoResponse> tagDtoResponseList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorId = authorId;
        this.tagDtoResponseList =tagDtoResponseList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, authorId);
    }

    @Override
    public String toString() {
        return "News id: " + id + "\n" + "News title: " + title + "\n" + "News content: " + content + "\n" + "News create date: " + createDate + "News last update time: " + lastUpdateDate + "\n" + "News author: " + authorId;
    }

    public List<TagDtoResponse> getTagDtoResponseList() {
        return tagDtoResponseList;
    }

    public void setTagDtoResponseList(List<TagDtoResponse> tagDtoResponseList) {
        this.tagDtoResponseList = tagDtoResponseList;
    }
}
