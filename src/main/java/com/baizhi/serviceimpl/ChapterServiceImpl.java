package com.baizhi.serviceimpl;

import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;

    //通过传过来的专辑Id查询对应的章节信息
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Chapter> queryAllChapterById(Integer parentId) {
        List<Chapter> chapterList = chapterMapper.selectByExample(parentId);
        return chapterList;
    }

    //添加一个章节
    @Override
    public void insert(Chapter chapter) {
        chapterMapper.insert(chapter);
    }
}
