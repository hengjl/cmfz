package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error implements Serializable {
    //这是用来返回并显示错误信息的额
    private String msg;
    //    public static void main(String[] args) {
//        //监听器
//        Timer timer=new Timer();
//        timer.schedule(new MyTask(),1000,3000);
//
//    }
}
