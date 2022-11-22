package com.ee.car_test.controller;

import com.ee.car_test.model.ExamRecords;
import com.ee.car_test.model.User;
import com.ee.car_test.service.IExamRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ExamRecordsController {

    @Autowired
    IExamRecordsService examRecordsService;

    @RequestMapping(path = "examRecords",method = RequestMethod.POST)
    public String examRecords(
            @RequestParam(name = "id")
            int id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        List<ExamRecords> examRecordsList = examRecordsService.getExamRecordsById(id);
        model.addAttribute("examListById",examRecordsList);
        return "examRecords";
    }

    @RequestMapping(path = "/examRecordsByPage",method = RequestMethod.POST)
    public String examRecordsByPage(
            @RequestParam(name = "number")
            int n,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest page = PageRequest.of(n,10);
        Page<ExamRecords> examRecords =  examRecordsService.getExamRecordsList(page);
        model.addAttribute("examList",examRecords);
        return "examRecords";
    }
}
