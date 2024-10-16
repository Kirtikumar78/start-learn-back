package com.elearn.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Data

public class Course {
    @Id
    private String id;
    private String title;
    private String shortDesc;
    @Column(length = 2000)
    private String LongDesc;
    private double price;
    private boolean live=false;
    private double discount;
    private Date createdDate;
    private String banner;

    @OneToMany(mappedBy = "course")
    private List<Video>videos =new ArrayList<>();

    @ManyToMany
    private List<Category> categoryList =new ArrayList<>();



}
