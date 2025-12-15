package com.influncer.education.Teacher.dto;

import com.influncer.education.Teacher.entity.University;

public class TeacherDTO {
    private Integer id;
    private int age;
    private String name;
    private String surname;

    private UniversityDTO university;

    public UniversityDTO getUniversity() {
        return university;
    }

    public TeacherDTO setUniversity(UniversityDTO university) {
        this.university = university;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public TeacherDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public int getAge() {
        return age;
    }

    public TeacherDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeacherDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public TeacherDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public TeacherDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public TeacherDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    private String email;
    public String password;

}
