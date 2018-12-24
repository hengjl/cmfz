package com.baizhi.controller;

import com.baizhi.conf.Audioutil;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //展示所有的章节
    @RequestMapping("/showAllChpater")
    public List<Chapter> showAllChpater(Integer parentId) {
        //  System.out.println("传过来的父类id"+parentId);
        List<Chapter> chapterList = chapterService.queryAllChapterById(parentId);
        return chapterList;
    }

    //添加一个章节
    @RequestMapping("/insert")
    public void insert(Chapter chapter, Integer parentId, HttpSession session, MultipartFile file) {
        //提供章节的id 和上传名字的冲突问题拼接在名字后面
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        //获取传过来音频的长度  是字节 B 单位的  需要进行转化
        long audioSize = file.getSize();
        double size = audioSize / (1024 * 1024.0);
        String realSize = String.format("%.2f", size);
        ServletContext servletContext = session.getServletContext();
        //获取根目录下面的绝对路径
        String realPath = servletContext.getRealPath("/music");
        System.out.println("RealPath=======" + realPath);
        //将时间用于加在图片路径上面，区分上传相同图片时候的文件名
        //long time = new Date().getTime();
        //System.out.println("原始名字=-======"+file.getOriginalFilename());
        File descFile = new File(realPath + "/" + uuid + file.getOriginalFilename());
        System.out.println("路径111===" + descFile);
        try {
            file.transferTo(descFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //发hi用uuid防止高并发时间内多个用户同时上传
        String lastPath = uuid + file.getOriginalFilename();
        System.out.println("最后路径====" + lastPath);
        //通过jar包和工具类获取时长
        String duration = Audioutil.getDuration(descFile).toString();
        //结果是秒 换算为分钟
        double d = Double.valueOf(duration) / 60;
        String realTime = String.format("%.2f", d);
        chapter.setDuration(realTime + "分钟");
        chapter.setSize(realSize + "MB");
        chapter.setUrl(lastPath);
        chapter.setId(uuid);
        chapter.setParentId(parentId);
        chapterService.insert(chapter);
    }

    //音频的下载
    @RequestMapping("/downLoadChapter")
    public void downLoadChapter(HttpSession session, String url, String title, HttpServletResponse response) {
        String realPath = session.getServletContext().getRealPath("/music");
        String filePath = realPath + "/" + realPath;
        File file = new File(filePath);
        //获取拓展名  需要依赖包
        String extension = FilenameUtils.getExtension(url);
        String oldName = title + "." + extension;
        //下面是将设置编码方式
        String encode = null;
        try {
            encode = URLEncoder.encode(oldName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        response.setContentType("audio/mpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
