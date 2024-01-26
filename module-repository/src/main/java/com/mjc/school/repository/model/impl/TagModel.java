package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Component
@Table(name = "Tags")
public class TagModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name",nullable = false)
    @Size(min = 3,max = 15,message = "Name is incorrect!")
    private String name;
    @ManyToMany(mappedBy = "tagModelList", fetch = FetchType.LAZY)
    private List<NewsModel> newsModelList;
    public void TagModel(){}
    public void TagModel(String name,List<NewsModel> newsModelList){
        this.name=name;
        this.newsModelList=newsModelList;
    }
    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id=id;
    }
    public List<NewsModel> getNewsModelList(){return newsModelList;}
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

}
