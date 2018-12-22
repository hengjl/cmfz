package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //查询所有专辑
    @RequestMapping("/showAllAlbums")
    public List<Album> showAllAlbums() {
        List<Album> albumList = albumService.queryAllAlbum();
        //System.out.println("查出来的所有专辑====="+albumList);
        return albumList;
    }

    //添加一个专辑
    @RequestMapping("/insertAlbum")
    public void insertAlbum(Album album, HttpSession session, MultipartFile file) throws IOException {
        ServletContext servletContext = session.getServletContext();
        //获取根目录下面的绝对路径
        String realPath = servletContext.getRealPath("/images");
        //将时间用于加在图片路径上面，区分下载相同图片时候的文件名
        long time = new Date().getTime();
        File descFile = new File(realPath + "/" + time + file.getOriginalFilename());

        file.transferTo(descFile);
        String lastPath = "images/" + time + file.getOriginalFilename();
        album.setCoverImg(lastPath);
        albumService.insertAlbum(album);

    }

    //用于展示所有专辑详情   查所有
    @RequestMapping("/showAll")
    public List<Album> showAll() {
        List<Album> albums = albumService.queryAll();
        return albums;
    }

    //用于删除专辑
    @RequestMapping("/delete")
    public void delete(Integer id) {
        albumService.delete(id);
    }
}
