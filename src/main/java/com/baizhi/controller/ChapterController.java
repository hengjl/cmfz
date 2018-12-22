package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void insert(Chapter chapter, Integer parentId) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        chapter.setId(uuid);
        System.out.println("传过来的父类ID" + parentId);
        chapter.setParentId(parentId);
        chapterService.insert(chapter);
    }


}
