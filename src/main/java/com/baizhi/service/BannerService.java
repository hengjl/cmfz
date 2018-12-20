package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

@SuppressWarnings("ALL")
public interface BannerService {
    //查询所有的轮播图
    public List<Banner> queryAllImg();

    //修改
    public void update(Banner banner);

    //删除
    public void delete(Integer id);

    //添加
    public void insert(Banner banner);

    //通过id查一个
    public Banner queryOneById(Integer id);


}
