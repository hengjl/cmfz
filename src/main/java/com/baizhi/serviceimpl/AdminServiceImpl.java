package com.baizhi.serviceimpl;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Boolean queryOne(Admin admin, String vCode, HttpSession session) {
        String code = (String) session.getAttribute("vCode");
        Admin admin1 = adminMapper.selectOne(admin);
        if (code.equalsIgnoreCase(vCode)) {
            // System.out.println("ffffffffff");
            if (admin1 != null) {
                // System.out.println("fffffffffffffffffddddddd");
                return true;
            }
        }

        return false;
    }


}
