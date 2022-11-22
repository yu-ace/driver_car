package com.ee.car_test.dao;

import com.ee.car_test.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentDao extends JpaRepository<Student,Integer> {
    Page<Student> findByStatus(String status, Pageable pageable);
    Student findById(int id);
}
