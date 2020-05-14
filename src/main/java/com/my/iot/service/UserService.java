package com.my.iot.service;

import com.my.iot.domain.User;

public interface UserService {
    public boolean regist(User user);

    public User login(User user);

    public void update(User user);
}
