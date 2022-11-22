package com.ee.car_test.dao;

import com.ee.car_test.model.ExamRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExamRecordsDao extends JpaRepository<ExamRecords,Integer> {
    List<ExamRecords> findByStudentId(int id);
}
