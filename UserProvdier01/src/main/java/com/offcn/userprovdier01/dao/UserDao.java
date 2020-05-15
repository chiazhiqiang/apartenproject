package com.offcn.userprovdier01.dao;

import com.offcn.userprovdier01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chaizhiqiang
 * @email 150069020@qq.com
 * @data 2020/5/13
 */
public interface UserDao extends JpaRepository<User, Long> {

}

