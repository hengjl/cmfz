package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;

public interface AdminService {
    public Boolean queryOne(Admin admin, String vCode, HttpSession session);

}
