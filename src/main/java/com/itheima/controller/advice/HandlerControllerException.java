/*
package com.itheima.controller.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * @author 35612
 *//*

@Component
public class HandlerControllerException implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg", e.getMessage());
        if (e instanceof AccessDeniedException) {
            modelAndView.setViewName("redirect:/403.jsp");
        } else {
            modelAndView.setViewName("forward:/500.jsp");
        }
        return modelAndView;
    }
}
*/
