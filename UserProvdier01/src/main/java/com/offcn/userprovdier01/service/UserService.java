package com.offcn.userprovdier01.service;

import com.offcn.userprovdier01.model.User;

import java.util.List;

/**
 * @author chaizhiqiang
 * @email 150069020@qq.com
 * @data 2020/5/13
 */
public interface UserService {

    //获取全部用户数据
    public List<User> getUserList();
    //新增用户数据
    public void createUser(User user);
    //获取指定id用户信息
    public User getUser(Long id);
    //更新指定id用户信息
    public void updateUser(Long id,User user);
    //删除指定id用户
    public void deleteUser(Long id);

}
