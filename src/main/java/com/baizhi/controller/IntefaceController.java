package com.baizhi.controller;

import com.aliyuncs.exceptions.ClientException;
import com.baizhi.entity.User;
import com.baizhi.serviceimpl.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("interface")
public class IntefaceController {
    @Autowired
    private TestInterface testInteface;

    @RequestMapping("/showFirstPage")
    public Map<String, Object> showFirstPage(String id, String type, String sub_type) {
        Map<String, Object> map = (Map<String, Object>) testInteface.homePage(id, type, sub_type);
        return map;
    }

    @RequestMapping("/albumDetail")
    public Map<String, Object> albumDetail(Integer id, Integer uid) {
        Map<String, Object> map = (Map<String, Object>) testInteface.albumDetail(id, uid);
        return map;
    }

    @RequestMapping("/login")
    public void login(String phone, String password, String code, String type) {
        testInteface.login(phone, password, code, type);
    }

    @RequestMapping("/regist")
    public void regist(User user) {
        testInteface.regist(user);
    }

    @RequestMapping("/update")
    public void update(User user) {
        testInteface.update(user);
    }

    @RequestMapping("/phoneCode")
    public void phoneCode(HttpSession session, String phone) {
        try {
            testInteface.phoneCode(null, phone);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/verifyPhoneCode")
    public String verifyPhoneCode(HttpSession session, String phone, String code) {
        String result = testInteface.verifyPhoneCode(null, phone, code);
        if (result.equals("success")) {
            return "success";
        } else {
            return "fail";
        }

    }

    public List<User> getFriends(Integer id) {
        List<User> friends = (List<User>) testInteface.getFriends(id);
        return friends;
    }


}
