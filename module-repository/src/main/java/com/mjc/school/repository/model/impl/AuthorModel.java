package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Entity
@Table(name = "Author")
public class AuthorModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name",nullable = false)
    @Size(min = 3,max = 15,message = "Name is incorrect!")
    private String name;
    @Column(name = "createDate",nullable = false)
    @CreatedDate
    private LocalDateTime createDate;
    @Column(name = "lastUpdateDate",nullable = false)
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;
    @OneToMany(mappedBy = "authorModel",cascade = CascadeType.REMOVE)
    private List<NewsModel> newsModelList;
    public AuthorModel() {
    }
    public AuthorModel(String name,LocalDateTime createDate,LocalDateTime lastUpdateDate) {
        this.name = name;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
}
