package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    /**
     * 可以作为账号
     */
    private String phone;
    private String password;
    /**
     * 用户密码加密用的盐
     */
    private String salt;
    /**
     * 修改密码时候  个人签名
     */
    private String sign;
    /**
     * 用户头像
     */
    @Column(name = "head_picture")
    private String headPicture;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户的上师
     */
    private String dharma;
    private String sex;
    private String province;
    private String city;
    /**
     * 用户的状态是否处于激活状态
     */
    private String status;
    /**
     * 用户注册时间,
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "regist_date")
    private Date registDate;
}
