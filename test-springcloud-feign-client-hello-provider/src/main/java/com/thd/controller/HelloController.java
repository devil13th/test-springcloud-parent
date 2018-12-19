package com.thd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.EurekaInstanceConfig;

@RestController
public class HelloController {
	 protected Logger logger = LoggerFactory.getLogger(HelloController.class);
	 
    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;
    @Value("${server.port}")
    private int serverPort = 0;

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable String name) {
    	System.out.println("provider.hello() 被调用 ");
        this.logger.info("/hello, instanceId:{}, host:{}", eurekaInstanceConfig.getInstanceId(), eurekaInstanceConfig.getHostName(false));
        
        return "Hello " + name + ", Spring Cloud! My port is " + String.valueOf(serverPort) + " , instanceId:" + eurekaInstanceConfig.getInstanceId() + " , host:" + eurekaInstanceConfig.getHostName(false);
    }
}
