package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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

    //添加包含文件上传
    @RequestMapping("/insert")
    public void insert(Banner banner, HttpSession session, MultipartFile file) throws IOException {
        ServletContext servletContext = session.getServletContext();
        //获取根目录下面的绝对路径
        String realPath = servletContext.getRealPath("/images");
        //将时间用于加在图片路径上面，区分下载相同图片时候的文件名
        long time = new Date().getTime();
        File descFile = new File(realPath + "/" + time + file.getOriginalFilename());
        //System.out.println("路径11111"+descFile);
        file.transferTo(descFile);
        String lastPath = "images/" + time + file.getOriginalFilename();
        // System.out.println("路径22222222222"+lastPath);
        banner.setImgPath(lastPath);
        bannerService.insert(banner);
    }

    //修改
    @RequestMapping("/update")
    public void update(Banner banner) {
        System.out.printf("传过的对象" + banner);
        bannerService.update(banner);
    }
}
