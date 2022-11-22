package com.ee.car_test.service.impl;

import com.ee.car_test.dao.IExamRecordsDao;
import com.ee.car_test.model.ExamRecords;
import com.ee.car_test.service.IExamRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamService implements IExamRecordsService {

    @Autowired
    IExamRecordsDao examRecordsDao;

    @Override
    public void addGrade(int id, int n, int grade) {
        ExamRecords examRecords = new ExamRecords();
        examRecords.setStudentId(id);
        examRecords.setExamTime(new Date());
        examRecords.setClassId(n);
        examRecords.setGrade(grade);
        examRecordsDao.save(examRecords);
    }

    @Override
    public List<ExamRecords> getExamRecordsById(int id) {
        return examRecordsDao.findByStudentId(id);
    }

    @Override
    public Page<ExamRecords> getExamRecordsList(Pageable pageable) {
        return examRecordsDao.findAll(pageable);
    }
}
