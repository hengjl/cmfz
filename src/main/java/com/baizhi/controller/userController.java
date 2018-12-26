package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    @RequestMapping("/insert")
    public void insert(User user) {
        userService.insert(user);
    }

    @RequestMapping("/update")
    public void update(User user) {
        userService.update(user);
    }
}
