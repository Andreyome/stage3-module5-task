package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class NewsDtoRequest {
    private Long id;
    private String content;
    private String title;
    private Long authorId;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    public NewsDtoRequest(Long id, String title, String content, Long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
    }

    public NewsDtoRequest() {
    }

//    public NewsDtoRequest(String title, String content, Long authorId) {
//        this.title = title;
//        this.content = content;
//        this.authorId = authorId;
//        this.createDate = LocalDateTime.now();
//        this.lastUpdateDate = LocalDateTime.now();
//    }


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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsDtoRequest that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(title, that.title) && Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, title, authorId);
    }

    @Override
    public String toString() {
        return "News id=" + id +
                "News content='" + content + '\'' +
                "News title='" + title + '\'' +
                "News authorId=" + authorId;
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
}
