package com.baizhi.mapper;

import com.baizhi.entity.China;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    /**
     * 通过自定义查询语句得出每个省份的男女分别有多少人
     *
     * @return
     */
    public List<User> getAllManAndWomanByProvince();

    public List<China> getAllByProvince();


    /**
     * 使用动态sql得出最近三周的 人数统计   根据注册日期
     * 要有一个参数用来控制动态sql
     * 定义一个String week
     * week  :   一周
     * week  :   两周
     * week  :   三周
     */
    public Integer getAllUserByDate(@Param("day") Integer day);
}
