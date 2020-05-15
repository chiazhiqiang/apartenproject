package com.offcn.userweb03.service;

import com.offcn.userweb03.model.User;

import java.util.Map;


public interface UserService {
     Map getUserMap();
     void createUser(User user);
     User getUser(Long id);
     void updateUser(Long id, User user);
     void deleteUser(Long id);
}
