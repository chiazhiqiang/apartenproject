package com.offcn.userweb01.service.impl;

import com.offcn.userweb01.model.User;
import com.offcn.userweb01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjian
 * @email 13120082225@163.com
 * @date 2020/5/13
 */
@Service
public class UserServiceImpl implements UserService {

    //远程服务调用客户端
    @Autowired
    private RestTemplate restTemplate;

    //Eureka客户端
    @Autowired
    private DiscoveryClient discoveryClient;

    /***
     * 通过客户端负载均衡器获取生产者服务器基础地址
     * @return
     */
    public String getServerUrl() {
        //通过客户端调用器查找指定服务
        List<ServiceInstance> instList = discoveryClient.getInstances("USERPROVIDER01");
        //获取第一个服务器
        ServiceInstance inst = instList.get(0);
        //获取服务提供者服务器IP
        String ip = inst.getHost();
        //获取服务提供者服务器端口号
        int port = inst.getPort();
        //拼接调用地址
        return "http://"+ip+":"+port+"/user";
    }



    @Override
    public Map getUserMap() {
        //getForObject 参数一：你调用的controller的具体地址 参数二:调用这个controller后返回的数据格式
        //RestTemplate对象就是帮助我们在java中实现HTTP请求的一个对象
        Map map = restTemplate.getForObject(getServerUrl() + "/getall", Map.class);
        return map;
    }

    @Override
    public void createUser(User user) {
        restTemplate.postForObject(getServerUrl() + "/save", user, String.class);
    }

    @Override
    public User getUser(Long id) {
        return restTemplate.getForObject(getServerUrl() + "/get/" + id, User.class);
    }

    @Override
    public void updateUser(Long id, User user) {
        restTemplate.put(getServerUrl() + "/update/" + id, user);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.delete(getServerUrl() + "/delete/" + id);
    }

}
