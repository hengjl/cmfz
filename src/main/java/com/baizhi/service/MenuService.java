package com.baizhi.service;

import com.baizhi.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> queryAllFirstSort();

    public List<Menu> queryAllSecondSort(Integer parentId);
}
