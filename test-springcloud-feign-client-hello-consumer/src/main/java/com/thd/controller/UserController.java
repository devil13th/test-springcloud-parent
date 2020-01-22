package com.thd.controller;

import com.netflix.discovery.converters.Auto;
import com.thd.service.UserService;
import com.thd.vo.MyUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.HystrixAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.eureka.RibbonEurekaAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * com.thd.controller.UserController
 *
 * @author: wanglei62
 * @DATE: 2020/1/2 15:05
 **/
@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    //org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration
    @Autowired
    private EurekaClientAutoConfiguration eurekaCfg ;
    //org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration
    @Autowired
    private RibbonAutoConfiguration ribbonCfg;
    //org.springframework.cloud.netflix.hystrix.HystrixAutoConfiguration
    @Autowired
    private HystrixAutoConfiguration hystrixAutoConfiguration;
    //org.springframework.cloud.netflix.ribbon.eureka.RibbonEurekaAutoConfiguration
    @Autowired
    private RibbonEurekaAutoConfiguration ribbonEurekaAutoConfiguration;
    //org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration
    @Autowired
    private FeignRibbonClientAutoConfiguration feignRibbonClientAutoConfiguration;
    //org.springframework.cloud.openfeign.FeignAutoConfiguration
    @Autowired
    private FeignAutoConfiguration feignAutoConfiguration;


    @Autowired
    private HystrixProperties HystrixProperties;
    @Autowired
    private FeignClientProperties feignClientProperties;
    @RequestMapping(value="/user/sleep/{second}", method = RequestMethod.GET)
    // url : http://127.0.0.1:9001/user/sleep/5
    public String sleep(@PathVariable Long second){
        logger.info("sleep");
        return userService.sleep(second);
    }

    @RequestMapping(value="/user/test/{msg}", method = RequestMethod.GET)
    // url : http://127.0.0.1:9001/user/test/zhangsan
    public String test(@PathVariable String msg){
//        logger.debug("test");
//        logger.warn("test");
        System.out.println(eurekaCfg);
        System.out.println(ribbonCfg);
        System.out.println(hystrixAutoConfiguration);
        System.out.println(ribbonEurekaAutoConfiguration);
        System.out.println(feignRibbonClientAutoConfiguration);
        System.out.println(HystrixProperties);
        System.out.println(feignAutoConfiguration);

        logger.info("test");
        return userService.test(msg);
    }


    @RequestMapping("/user/queryUser")
    @ResponseBody
    // url : http://127.0.0.1:9001/user/queryUser
    public Map<String, MyUser> queryUser(){
        logger.info("queryUser");
        return userService.queryUser();
    };

    @RequestMapping("/user/addUser/{name}")
    @ResponseBody
    // url : http://127.0.0.1:9001/user/addUser/zhangsan
    public MyUser addUser(@PathVariable String name){
        logger.info("addUser");
        return userService.addUser(name);
    };

    @RequestMapping("/user/updateUser/{id}/{name}")
    @ResponseBody
    // url : http://127.0.0.1:9001/user/updateUser/xxx/zhangsan
    public MyUser updateUser(@PathVariable String id, @PathVariable String name){
        logger.info("updateUser");
        return userService.updateUser(id,name);
    };
    // url : http://127.0.0.1:9001/user/deleteUser/xxx
    @RequestMapping("/user/deleteUser/{id}")
    @ResponseBody
    public MyUser deleteUser(@PathVariable String id){
        logger.info("deleteUser");
        return userService.deleteUser(id);
    };
}
