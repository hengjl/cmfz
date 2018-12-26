package com.baizhi.serviceimpl;

import com.baizhi.entity.China;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> getAllUser() {
        List<User> userList = userMapper.selectAll();
        return userList;
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);

    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getOneUser(Integer id) {
        User user = (User) userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> getAllManAndWomanByProvince() {
        List<User> userList = userMapper.getAllManAndWomanByProvince();
        return userList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<China> getAllByPrivince() {
        List<China> list = userMapper.getAllByProvince();
        return list;

    }

    @Override
    public Integer getAllUserByDate(Integer day) {
        return userMapper.getAllUserByDate(day);


    }

}
