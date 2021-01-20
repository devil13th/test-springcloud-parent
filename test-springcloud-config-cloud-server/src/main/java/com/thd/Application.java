package com.thd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * com.thd.controller.Application
 *
 * @author: wanglei62
 * @DATE: 2021/1/18 17:34
 **/
@SpringBootApplication
// 开启服务发现
@EnableDiscoveryClient
// 通过@EnableConfigServer注解激活配置服务
@EnableConfigServer
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // 启动后浏览 http://127.0.0.1:7007/master/common-a.yml和http://127.0.0.1:7007/master/common-dev.yml
    }
}
