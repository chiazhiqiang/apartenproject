package com.offcn.userweb03.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.userweb03.model.User;
import com.offcn.userweb03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    //远程服务调用客户端
    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    private String url = "http://USERPROVIDER";

    /***
     * 通过客户端负载均衡器获取生产者服务器基础地址
     * @return
     */
//    public String getServerUrl() {
//        ServiceInstance inst = loadBalancerClient.choose("USERPROVIDER");
//        //获取服务提供者服务器IP
//        String ip = inst.getHost();
//        //获取服务提供者服务器端口号
//        int port = inst.getPort();
//        //拼接调用地址
//        return "http://" + ip + ":" + port + "/user";
//    }


    @Override
    @HystrixCommand(fallbackMethod="getUserMapFallbackMethod")
    public Map getUserMap() {
        //getForObject 参数一：你调用的controller的具体地址 参数二:调用这个controller后返回的数据格式
        //RestTemplate对象就是帮助我们在java中实现HTTP请求的一个对象
//        Map map = restTemplate.getForObject(getServerUrl() + "/getall", Map.class);
        // http://ip:port/controller/
        //http://USERPROVIDER/user/getall
        long beginTime = System.currentTimeMillis();
        Map map = restTemplate.getForObject(url + "/user/getall", Map.class);
        long endTime=System.currentTimeMillis();
        System.out.println("程序执行时间:"+(endTime-beginTime));

        return map;
    }

    @Override
    public void createUser(User user) {
//        restTemplate.postForObject(getServerUrl() + "/save", user, String.class);
        restTemplate.postForObject(url + "/user/save", user, String.class);
    }

    @Override
    public User getUser(Long id) {
//        return restTemplate.getForObject(getServerUrl() + "/get/" + id, User.class);
        return restTemplate.getForObject(url + "/user/get/" + id, User.class);
    }

    @Override
    public void updateUser(Long id, User user) {
//        restTemplate.put(getServerUrl() + "/update/" + id, user);
        restTemplate.put(url + "/user/update/" + id, user);
    }


    @Override
    public void deleteUser(Long id) {
//        restTemplate.delete(getServerUrl() + "/delete/" + id);
        restTemplate.delete(url + "/user/delete/" + id);
    }

    /**
     * 获取全部用户数据，发生熔断后调用方法
     * @return
     */
    public Map<String, Object> getUserMapFallbackMethod() {
        Map map = new HashMap();
        map.put("list", new ArrayList<>());
        map.put("ProviderVersion", "获取远程调用失败");
        return map;
    }


}
