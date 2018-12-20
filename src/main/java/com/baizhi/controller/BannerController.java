package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired

    private BannerService bannerService;

    //展示所有轮播图
    @RequestMapping("/showAllImg")
    public List<Banner> showAllImg() {
        List<Banner> bannerList = bannerService.queryAllImg();
        return bannerList;
    }

    //查一个
    @RequestMapping("/showOneImg")
    public Banner showOneImg(Integer id) {
        Banner banner = bannerService.queryOneById(id);
        return banner;
    }

    //删除
    @RequestMapping("/delete")
    public void delete(Integer id) {
        bannerService.delete(id);
    }

    //添加
    @RequestMapping("/insert")
    public void insert(Banner banner) {
        bannerService.insert(banner);
    }

    //修改
    @RequestMapping("/update")
    public void update(Banner banner) {
        System.out.printf("传过的对象" + banner);
        bannerService.update(banner);
    }
}
