package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chapter")
public class Chapter implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号")
    private String id;
    @Excel(name = "单曲名称")
    private String title;
    @Excel(name = "单曲大小")
    private String size;//对应音频大小
    @Excel(name = "单曲时长")
    private String duration;//每一个章节的时长
    @ExcelIgnore
    private String url;//音频路径
    @Column(name = "upload_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "单曲发行时间", format = "YYYY年MM月dd日", width = 20)
    private Date uploadDate;//音频下载日期
    @Column(name = "parent_id")
    @ExcelIgnore
    private Integer parentId;


}
