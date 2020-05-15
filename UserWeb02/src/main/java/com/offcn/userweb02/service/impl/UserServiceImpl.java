package com.offcn.userweb02.service.impl;

import com.offcn.userweb02.model.User;
import com.offcn.userweb02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    //远程服务调用客户端
    @Autowired
    private RestTemplate restTemplate;

    //Eureka客户端
//    @Autowired
//    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;


    /***
     * 通过客户端负载均衡器获取生产者服务器基础地址
     * @return
     */
    public String getServerUrl() {
        //通过客户端调用器查找指定服务
//        List<ServiceInstance> instList = discoveryClient.getInstances("USERPROVIDER01");
        //获取第一个服务器
//        ServiceInstance inst = instList.get(0);
        ServiceInstance inst = loadBalancerClient.choose("USERPROVIDER");
        //获取服务提供者服务器IP
        String ip = inst.getHost();
        //获取服务提供者服务器端口号
        int port = inst.getPort();
        //拼接调用地址
        return "http://" + ip + ":" + port + "/user";
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
