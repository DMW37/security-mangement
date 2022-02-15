package com.itheima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/order")
public class OrderController {
    //@Secured("{ROLE_ORDER,ROLE_ADMIN}")  //springSecurity框架内部提供
    //@RolesAllowed("{ROLE_ORDER,ROLE_ADMIN}")  //jsr250提供
    @PreAuthorize("hasAnyRole('ROLE_ORDER','ROLE_ADMIN')") //spring提供 需要开启spring的el写法
    @RequestMapping("/findAll")
    public String findAll(){
        return "order-list";
    }
}
