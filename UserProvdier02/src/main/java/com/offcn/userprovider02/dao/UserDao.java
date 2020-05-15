package com.offcn.userprovider02.dao;

import com.offcn.userprovider02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao  extends JpaRepository<User,Long> {

}
