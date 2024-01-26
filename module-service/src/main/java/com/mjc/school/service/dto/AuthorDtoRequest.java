package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public class AuthorDtoRequest {
    private Long id;
    private String name;


    public AuthorDtoRequest(Long id, String name) {
        this.id = id;
        this.name = name;
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

}
