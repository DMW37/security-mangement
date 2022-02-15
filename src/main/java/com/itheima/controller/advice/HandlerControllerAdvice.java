package com.itheima.controller.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 35612
 */
@RestControllerAdvice
public class HandlerControllerAdvice  {

    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedException(){
        return "redirect:403.jsp";
    }
}
