package com.shiro.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView error(AuthenticationException e){
        ModelAndView mv = new ModelAndView("pages/unauthorized");
        mv.addObject("error",e.getMessage());
        return mv;
    }
}
