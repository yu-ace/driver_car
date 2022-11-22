package com.ee.car_test.service;

import com.ee.car_test.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    void newStudent(String name);
    void addGrade(int id,int n,int grade);
    Page<Student> getStudentList(Pageable pageable);
    Page<Student> getStudentListByStatus(String status,Pageable pageable);
}
