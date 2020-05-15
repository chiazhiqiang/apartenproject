package com.offcn.userweb04.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chaizhiqiang
 * @email 150069020@qq.com
 * @data 2020/5/14
 */
@Configuration
public class FeignConfig {
    /**
     *  Level 取值 NONE BASIC HEADERS FULL
     *  */
    @Bean
    public Logger.Level getFeignlogger(){
        return Logger.Level.FULL;
    }
}

