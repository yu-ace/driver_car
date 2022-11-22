package com.ee.car_test.controller;

import com.ee.car_test.model.ExamRecords;
import com.ee.car_test.model.Student;
import com.ee.car_test.model.User;
import com.ee.car_test.service.IExamRecordsService;
import com.ee.car_test.service.IStudentService;
import com.ee.car_test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    IStudentService studentService;
    @Autowired
    IExamRecordsService examRecordsService;
    @Autowired
    IUserService userService;


    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String index1(){
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/new",method = RequestMethod.GET)
    public String newStudent(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "newStudent";
    }

    @RequestMapping(path = "/studentList",method = RequestMethod.GET)
    public String studentList(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest page = PageRequest.of(0,10);
        Page<Student> studentList = studentService.getStudentList(page);
        model.addAttribute("list",studentList);
        return "studentList";
    }

    @RequestMapping(path = "/add",method = RequestMethod.GET)
    public String addGrade(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "addGrade";
    }

    @RequestMapping(path = "/examRecords",method = RequestMethod.GET)
    public String examRecords(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest page = PageRequest.of(0,10);
        Page<ExamRecords> examRecords =  examRecordsService.getExamRecordsList(page);
        if(examRecords == null){
            model.addAttribute("error","目前没有考试记录");
            return "examRecords";
        }
        model.addAttribute("examList",examRecords);
        return "examRecords";
    }

    @RequestMapping(path = "/studentGradeList",method = RequestMethod.GET)
    public String studentGradeList(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest page = PageRequest.of(0,10);
        Page<Student> examRecords = studentService.getStudentList(page);
        model.addAttribute("gradeList",examRecords);
        return "studentGradeList";
    }

    @RequestMapping(path = "/userList",method = RequestMethod.GET)
    public String userList(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        List<User> userList = userService.getUserList(0);
        model.addAttribute("userList",userList);
        return "userList";
    }

    @RequestMapping(path = "/addUser",method = RequestMethod.GET)
    public String addUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "addUser";
    }

    @RequestMapping(path = "/delete",method = RequestMethod.GET)
    public String deleteUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "delete";
    }

    @RequestMapping(path = "/changePassword",method = RequestMethod.GET)
    public String changePassword(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "changePassword";
    }

    @RequestMapping(path = "/driveBoard",method = RequestMethod.GET)
    public String driveBoard(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "redirect:/login";
        }else{
            model.addAttribute("user",user);
            return "driveBoard";
        }
    }
}