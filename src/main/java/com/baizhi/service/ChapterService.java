package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterService {
    //通过穿过专辑的id查所有的章节
    public List<Chapter> queryAllChapterById(Integer parentId);

    //添加专辑
    public void insert(Chapter chapter);
}
