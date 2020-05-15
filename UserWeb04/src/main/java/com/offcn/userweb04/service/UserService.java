package com.offcn.userweb04.service;

import com.offcn.userweb04.config.FeignConfig;
import com.offcn.userweb04.model.User;
import com.offcn.userweb04.service.impl.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@FeignClient(value = "USERPROVIDER", configuration= FeignConfig.class, fallback= UserServiceImpl.class)
public interface UserService {
    @GetMapping("/user/getall")
    Map getUserMap();

    @PostMapping("/user/save")
    void createUser(User user);

    @GetMapping("/user/get/{id}")
    User getUser(@RequestParam("id") Long id);

    @PutMapping("/user/update/{id}")
    void updateUser(@RequestParam("id") Long id, @RequestBody User user);

    @DeleteMapping("/user/delete/{id}")
    void deleteUser(@RequestParam("id") Long id);
}
