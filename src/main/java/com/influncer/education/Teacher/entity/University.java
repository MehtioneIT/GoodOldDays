package com.influncer.education.Teacher.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity(name="University")
@Table(name="university")
public class University {
    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "university",
            targetEntity = Teacher.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)

    @JsonIgnore
    private List<Teacher> teachers;


    public List<Teacher> getTeachers() {
        return teachers;
    }

    public University setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public University setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public University setName(String name) {
        this.name = name;
        return this;
    }



}
