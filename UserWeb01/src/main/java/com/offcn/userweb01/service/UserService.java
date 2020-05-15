package com.offcn.userweb01.service;

import com.offcn.userweb01.model.User;

import java.util.Map;

/**
 * @author chaizhiqiang
 * @email 150069020@qq.com
 * @data 2020/5/13
 */
public interface UserService {
    public Map getUserMap();
    public void createUser(User user);
    public User getUser(Long id);
    public void updateUser(Long id,User user);
    public void deleteUser(Long id);
}

