package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(Admin admin) {
        // System.out.print("传过来的值++++++++++++"+admin);
        Boolean aBoolean = adminService.queryOne(admin);
        //System.out.print("判断---------"+aBoolean);
        if (aBoolean == true) {
            return "ok";
        }
        return "no";
    }
}
