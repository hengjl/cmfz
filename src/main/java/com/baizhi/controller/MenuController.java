package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/showAllFirstSort")
    public List<Menu> showAllFirstSort() {
        // System.out.printf("分类进来了=============");
        List<Menu> menuList = menuService.queryAllFirstSort();
        //System.out.print(menuList);
        return menuList;
    }

    @RequestMapping("/showAllSecondSort")
    public List<Menu> showAllSecondSort(Integer parentId) {
        // System.out.printf("传过来的父类ID=============" + parentId);
        List<Menu> menuList = menuService.queryAllSecondSort(parentId);
        // System.out.print(menuList);
        return menuList;
    }
}
