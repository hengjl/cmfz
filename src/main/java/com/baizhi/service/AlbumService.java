package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    //将所有的专辑查出来
    public List<Album> queryAllAlbum();

    //插入一个专辑
    public void insertAlbum(Album album);

    //删除一张专辑
    public void delete(Integer id);

    //用于展示所有商品详情页的
    public List<Album> queryAll();

}
