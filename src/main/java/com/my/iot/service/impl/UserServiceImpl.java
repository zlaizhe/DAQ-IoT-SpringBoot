package com.my.iot.service.impl;

import com.my.iot.domain.User;
import com.my.iot.mapper.UserMapper;
import com.my.iot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean regist(User user) {
        User u = userMapper.findByUsername(user.getUsername());
        if (u != null) {//用户名已存在，注册失败
            return false;
        }
        userMapper.insert(user);//不存在，注册
        return true;
    }

    @Override
    public User login(User user) {//登录，成功返回完整用户信息，失败返回null
        return userMapper.findByUsernameAndPassword(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }
}
