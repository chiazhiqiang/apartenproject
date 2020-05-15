package com.offcn.userweb03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangjian
 * @email 13120082225@163.com
 * @date 2020/5/13
 */
@SpringBootApplication
@EnableDiscoveryClient
/** 启动熔断器(保险丝)  */
@EnableCircuitBreaker
public class UserWeb03Application {
    public static void main(String[] args) {
        SpringApplication.run(UserWeb03Application.class,args);
    }

    @Bean
    @LoadBalanced //开启Ribbon
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
