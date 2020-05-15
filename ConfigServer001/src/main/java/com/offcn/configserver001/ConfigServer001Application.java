package com.offcn.configserver001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author chaizhiqiang
 * @email 150069020@qq.com
 * @data 2020/5/14
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer001Application {


    public static void main(String[] args) {
        SpringApplication.run(ConfigServer001Application.class, args);
    }
}

