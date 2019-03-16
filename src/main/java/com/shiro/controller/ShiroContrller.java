package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ShiroContrller {
    @GetMapping("/")
    public String hello(){
        return "welcome";
    }
    @GetMapping("login")
    public String login(){
        return "pages/login";
    }
    @GetMapping("unauthorized")
    public String unauthorized(){
        return "pages/unauthorized";
    }

    @PostMapping("/login")
    public String doLogin(String username, String password, Model model, HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            session.setAttribute("token",token.getUsername());
        }catch (AuthenticationException e){
            model.addAttribute("error","用户名或密码错误！");
            return "pages/login";
        }
        return "redirect:/";

    }

//    @RequiresRoles("root")
    @GetMapping("/level3/1")
    public String root(){
        return "/pages/level3/1";
    }
//    @RequiresRoles("root")
    @GetMapping("/level3/2")
    public String root2(){
        return "/pages/level3/2";
    }
//    @RequiresRoles("root")
    @GetMapping("/level3/3")
    public String root3(){
        return "/pages/level3/3";
    }

//    @RequiresRoles(value = {"admin","root"},logical = Logical.OR)
    @GetMapping("/level2/1")
    public String admin(){
        return "/pages/level2/1";
    }
//    @RequiresRoles(value = {"admin","root"},logical = Logical.OR)
    @GetMapping("/level2/2")
    public String admin2(){
        return "/pages/level2/2";
    }
//    @RequiresRoles(value = {"admin","root"},logical = Logical.OR)
    @GetMapping("/level2/3")
    public String admin3(){
        return "/pages/level2/3";
    }

//    @RequiresRoles(value = {"user","admin","root"},logical = Logical.OR)
    @GetMapping("/level1/1")
    public String user(){
        return "/pages/level1/1";
    }
//    @RequiresRoles(value = {"user","admin","root"},logical = Logical.OR)
    @GetMapping("/level1/2")
    public String user2(){
        return "/pages/level1/2";
    }
//    @RequiresRoles(value = {"user","admin","root"},logical = Logical.OR)
    @GetMapping("/level1/3")
    public String user3(){
        return "/pages/level1/3";
    }
}
