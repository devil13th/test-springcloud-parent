package com.thd.service;


import com.thd.cfg.FeignConfiguration;
import com.thd.vo.MyUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


/**
 * com.thd.service.UserService
 *
 * @author: wanglei62
 * @DATE: 2020/1/2 15:01
 **/


@FeignClient(value="eureka-client-hello-provider",fallbackFactory=UserServiceFallbackFactory.class,configuration=FeignConfiguration.class)
public interface UserService {

    @RequestMapping(value="/user/sleep/{second}", method = RequestMethod.GET)
    public String sleep(@PathVariable Long second);

    @RequestMapping(value="/user/test/{msg}",method= RequestMethod.GET)
    public String test(@PathVariable String msg);

    @RequestMapping(value="/user/queryUser",method= RequestMethod.GET)
    public Map<String, MyUser> queryUser();

    @RequestMapping(value="/user/addUser/{name}",method=RequestMethod.GET)
    public MyUser addUser(@PathVariable String name);

    @RequestMapping(value="/user/updateUser/{id}/{name}",method=RequestMethod.GET)
    public MyUser updateUser(@PathVariable String id, @PathVariable String name);

    @RequestMapping(value="/user/deleteUser/{id}",method=RequestMethod.GET)
    public MyUser deleteUser(@PathVariable String id);
}
