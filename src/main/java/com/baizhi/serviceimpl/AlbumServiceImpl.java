package com.baizhi.serviceimpl;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Album> queryAllAlbum() {
        List<Album> albumList = albumMapper.queryAllAlbumChapter();
        return albumList;
    }

    @Override
    public void insertAlbum(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public void delete(Integer id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Album> queryAll() {
        List<Album> albums = albumMapper.selectAll();
        return albums;
    }
}
