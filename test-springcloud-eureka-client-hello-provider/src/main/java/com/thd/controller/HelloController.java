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

import java.util.List;

@RestController
public class HelloController {
	 protected Logger logger = LoggerFactory.getLogger(HelloController.class);
	 
    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;
    @Value("${server.port}")
    private int serverPort = 0;



    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
    	System.out.println("provider 被调用 ");
        this.logger.info("/hello, instanceId:{}, host:{}", eurekaInstanceConfig.getInstanceId(), eurekaInstanceConfig.getHostName(false));
        
        return "Hello, Spring Cloud! My port is " + String.valueOf(serverPort) + " , instanceId:" + eurekaInstanceConfig.getInstanceId() + " , host:" + eurekaInstanceConfig.getHostName(false);
    }

    @RequestMapping(value = "/sleep/{t}", method = RequestMethod.GET)
    public String sleep(@PathVariable long t) {
        System.out.println("provider.sleep 被调用 ");
        System.out.println("sleep  ");
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("wake up  ");
        return "Hello, Spring Cloud! My port is " + String.valueOf(serverPort) + " , instanceId:" + eurekaInstanceConfig.getInstanceId() + " , host:" + eurekaInstanceConfig.getHostName(false) + " i sleep " + t + " million second";
    }

    @RequestMapping(value = "/retryTest", method = RequestMethod.GET)
    public String retryTest() {
        System.out.println("provider.retryTest 被调用 ");
        int i = 1/0;
        return "Hello, Spring Cloud! My port is " + String.valueOf(serverPort) + " , instanceId:" + eurekaInstanceConfig.getInstanceId() + " , host:" + eurekaInstanceConfig.getHostName(false);
    }
}
