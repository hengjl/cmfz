package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
@ExcelTarget(value = "album")
public class Album implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;
    @Excel(name = "专辑名称", needMerge = true)
    private String title;
    @ExcelIgnore
    private Integer count;//专辑里面章节的数量
    @Column(name = "cover_img")
    @Excel(name = "封面", type = 2, width = 40, height = 70, needMerge = true)
    private String coverImg;//专辑的封面
    @ExcelIgnore
    private Integer score;//专辑的评分
    @ExcelIgnore
    private String author;//专辑的作者
    @ExcelIgnore
    private String broadcast;//声优
    @ExcelIgnore
    private String brief;//简介
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "pub_date")
    @Excel(format = "YYYY年mm月dd日", name = "专辑发行时间", width = 20)
    private Date pubDate;//上架时间
    @ExcelCollection(name = "发行的单曲歌曲")
    private List<Chapter> children;
}
