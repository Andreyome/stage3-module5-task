package com.mjc.school.repository.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tags")
public class TagModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",nullable = false)
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
