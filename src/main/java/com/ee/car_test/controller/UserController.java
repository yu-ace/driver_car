package com.ee.car_test.controller;

import com.ee.car_test.model.User;
import com.ee.car_test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    IUserService userService;



    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password, Model model,HttpSession session){
        User user = userService.getUserByName(name);
        if(user == null){
            model.addAttribute("error","该用户不存在");
            return "login";
        }
        if(user.getPassword().equals(password)){
            session.setAttribute("user",user);
            return "redirect:/driveBoard";
        }
        model.addAttribute("error","密码错误");
        return "login";
    }

    @RequestMapping(path = "/addUser",method = RequestMethod.POST)
    public String register(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","该用户不存在！");
            return "login";
        }
        userService.newUser(name,password);
        model.addAttribute("tip","创建成功");
        return "addUser";
    }

    @RequestMapping(path = "/deleteUser",method = RequestMethod.POST)
    public String delete(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "password")
            String password, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","该用户不存在！");
            return "login";
        }
        if(id == 1){
            model.addAttribute("tip","对不起，该用户为管理员，您没有权限删除");
            return "delete";
        }
        User user1 = userService.getUserById(id);
        if(user1 == null){
            model.addAttribute("tip","该id不存在，删除失败");
            return "delete";
        }else{
            if(user1.getPassword().equals(password)){
                userService.deleteUser(id);
                model.addAttribute("tip","删除成功");
                return "delete";
            }else{
                model.addAttribute("tip","密码错误，删除失败");
                return "delete";
            }
        }
    }

    @RequestMapping(path = "/changePassword",method = RequestMethod.POST)
    public String change(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password,
            @RequestParam(name = "password1")
            String newPassword,
            @RequestParam(name = "password2")
            String newPassword1,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","该用户不存在！");
            return "login";
        }
        if(name.equals("admin")){
            model.addAttribute("tip","对不起，该用户为管理员，您没有权限更改密码");
            return "changePassword";
        }
        User user1 = userService.getUserByName(name);
        if(user1 == null){
            model.addAttribute("tip","对不起，该用户不存在");
            return "changePassword";
        }
        if(user1.getPassword().equals(password)){
            if(newPassword.equals(newPassword1) && !newPassword.equals(password)){
                userService.changePassword(name,newPassword);
                model.addAttribute("tip","更改成功");
                return "changePassword";
            }else if(newPassword.equals(password) || newPassword1.equals(password)){
                model.addAttribute("tip","新密码与原密码相同，更改失败");
                return "changePassword";
            }else {
                model.addAttribute("tip", "两次密码不同，更改失败");
                return "changePassword";
            }
        }else{
            model.addAttribute("tip","对不起，密码错误，更改失败");
            return "changePassword";
        }
    }

    @RequestMapping(path="/userList",method = RequestMethod.POST)
    public String list(
            @RequestParam(name = "number")
            int n,Model model,HttpSession session){
        User user= (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","该用户不存在！");
            return "login";
        }
        PageRequest o = PageRequest.of(n,10);
        Page<User> userList = userService.getUserList(0,o);
        model.addAttribute("userList",userList);
        return "userList";
    }
}
