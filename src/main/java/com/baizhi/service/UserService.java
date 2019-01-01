package com.baizhi.service;

import com.baizhi.entity.China;
import com.baizhi.entity.User;

import java.util.List;

/**
 * User Service  （Interface）
 *
 * @author Mr  HENG JIA LI
 * @date 2018/12531
 */
public interface UserService {
    /**
     * //查所有
     *
     * @return
     */
    public void insert(User user);

    public Object update(User user);

    public List<User> getAllUser(Integer id);

    public void delete(Integer id);

    public User getOneUser(Object object);



    /**
     * 通过自定义查询语句得出每个省份的男女分别有多少人
     *
     * @return
     */
    public List<User> getAllManAndWomanByProvince();

    public List<China> getAllByPrivince();

    /**
     * 使用动态sql得出最近三周的 人数统计   根据注册日期
     * 要有一个参数用来控制动态sql
     * 定义一个String week
     * week  :   一周
     * week  :   两周
     * week  :   三周
     */
    public Integer getAllUserByDate(Integer day);
}
