package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")//指定查询表的名字
public class Admin implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)//mysql中id自增
    private Integer id;
    private String name;
    private String password;
}
