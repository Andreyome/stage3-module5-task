package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public class AuthorDtoResponse {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    public AuthorDtoResponse(Long id, String name,LocalDateTime createDate,LocalDateTime lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.createDate=createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return "Author id: " + id +"\n" + "Author name: " + name+ "\n" + "Author create date: " + createDate + "Author last update time: " + lastUpdateDate;
    }
}
