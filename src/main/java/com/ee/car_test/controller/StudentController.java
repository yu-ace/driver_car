package com.ee.car_test.controller;

import com.ee.car_test.model.Student;
import com.ee.car_test.model.User;
import com.ee.car_test.service.IExamRecordsService;
import com.ee.car_test.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    IStudentService studentService;

    @Autowired
    IExamRecordsService examRecordsService;


    @RequestMapping(path = "/newStudent",method = RequestMethod.POST)
    public String newStudent(
            @RequestParam(name = "name")
            String name, Model model, HttpSession session){
        User user =(User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        studentService.newStudent(name);
        model.addAttribute("tip","注册成功");
        return "newStudent";
    }

    @RequestMapping(path = "/studentList",method = RequestMethod.POST)
    public String studentList(
            @RequestParam(name = "status")
            String status, Model model, HttpSession session){
        User user =(User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        List<Student> studentList = studentService.getStudentListByStatus(status);
        model.addAttribute("listByStatus",studentList);
        return "studentList";
    }

    @RequestMapping(path = "addGrade" ,method = RequestMethod.POST)
    public String add(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "n")
            int n,
            @RequestParam(name = "grade")
            int grade,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        studentService.addGrade(id,n,grade);
        examRecordsService.addGrade(id,n,grade);
        model.addAttribute("tip","添加成功");
        return "addGrade";
    }
}
