package com.ee.car_test.service;

import com.ee.car_test.model.ExamRecords;
import com.ee.car_test.service.impl.ExamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IExamRecordsService {
    void addGrade(int id,int n,int grade);
    List<ExamRecords> getExamRecordsById(int id);
    Page<ExamRecords> getExamRecordsList(Pageable pageable);
}
