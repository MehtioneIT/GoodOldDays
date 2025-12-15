package com.influncer.education.Teacher.repo;

import com.influncer.education.Teacher.entity.Teacher;
import com.influncer.education.Teacher.entity.TeacherProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyDataTeacherRepo extends JpaRepository<Teacher,Integer> {

}
