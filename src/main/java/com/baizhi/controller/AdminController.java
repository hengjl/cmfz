package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
//@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(Admin admin, String vCode, HttpSession session) {
        System.out.print("传过来的值++++++++++++" + vCode);
        Boolean aBoolean = adminService.queryOne(admin, vCode, session);
        //System.out.print("判断---------"+aBoolean);
        if (aBoolean == true) {
            System.out.println("oooooooooooooooooooooo");
            return "ok";
        }
        System.out.println("BNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        return "no";
    }
}
