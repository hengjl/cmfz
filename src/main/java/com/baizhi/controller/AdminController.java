package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.entity.China;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(Admin admin, String vCode, HttpSession session) {
        System.out.print("传过来的值++++++++++++" + vCode);
        Boolean aBoolean = adminService.queryOne(admin, vCode, session);
        //System.out.print("判断---------"+aBoolean);
        if (aBoolean == true) {
            //System.out.println("oooooooooooooooooooooo");
            return "ok";
        }
        //System.out.println("BNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        return "no";
    }

    @RequestMapping("/showAllManAndWomanByProvince")
    public List<User> showAllManAndWomanByProvince() {
        List<User> userList = userService.getAllManAndWomanByProvince();

        System.out.println(userList);

        return userList;
    }

    @RequestMapping("/showAllByProvince")
    public List<China> showAllByProvince() {
        List<China> mapList = userService.getAllByPrivince();
        List<China> list = null;
       /* for(int i=0;i<mapList.size();i++){
            int count=1020;
            mapList.get(i).setValue(count);
         //map.setValue(i*count+4);
       //  list.add(map);
            count+=107;
        }*/

        System.out.println(mapList);
        //System.out.println(list);

        return mapList;
    }

    /**
     * 将最近用户的状态
     */
    @RequestMapping("/userCount")
    public Map<String, Object> userCount() {
        int[] array = {7, 14, 21, 28};
        int[] arr = new int[4];
        for (int i = 0; i < array.length; i++) {
            arr[i] = userService.getAllUserByDate(array[i]);
            // System.out.println(arr[i]+"------------------------");

        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", arr);

        //System.out.println(map);
        return map;
    }
}
