package com.ee.car_test.dao;

import com.ee.car_test.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentDao extends JpaRepository<Student,Integer> {
    List<Student> findByStatus(String status);
    Student findById(int id);
}
