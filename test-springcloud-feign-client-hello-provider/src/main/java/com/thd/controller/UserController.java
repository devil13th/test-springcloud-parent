package com.thd.controller;

import com.thd.vo.MyUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * com.thd.controller.UserController
 *
 * @author: wanglei62
 * @DATE: 2020/1/2 14:37
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String,MyUser> userList = new HashMap<String,MyUser>();

    @RequestMapping(value="/sleep/{second}", method = RequestMethod.GET)
    public String sleep(@PathVariable Long second){
        logger.info("sleep " + second + " MS");
        try {
            Thread.sleep(second );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(" wake up !");
        return "sleep " + second + " millin second";
    }

    @RequestMapping(value="/test/{msg}", method = RequestMethod.GET)
    public String test(@PathVariable String msg){
        logger.info("test");
        return "test " + msg;
    }

    @RequestMapping(value="/queryUser", method = RequestMethod.GET)
    public Map<String,MyUser> queryUser(){
        logger.info("queryUser");
        return userList;
    }

    @RequestMapping(value="/addUser/{name}", method = RequestMethod.GET)
    public MyUser addUser(@PathVariable String name){
        logger.info("addUser");
        MyUser u = new MyUser();
        u.setName(name);
        String userId = UUID.randomUUID().toString().replace("-","");
        u.setId(userId);
        this.userList.put(userId,u);
        return u;
    }

    @RequestMapping(value="/updateUser/{id}/{name}", method = RequestMethod.GET)
    public MyUser updateUser(@PathVariable String id, @PathVariable String name){
        logger.info("updateUser");
        MyUser u = this.userList.get(id);
        if( u == null){
            throw new RuntimeException("未找到id:" + id);
        }
        u.setName(name);
        this.userList.put(id,u);
        return u;
    }


    @RequestMapping(value="/deleteUser/{id}", method = RequestMethod.GET)
    public MyUser deleteUser(@PathVariable String id){
        logger.info("deleteUser");
        MyUser u = this.userList.get(id);
        if( u == null){
            throw new RuntimeException("未找到id:" + id);
        }
        u = this.userList.remove(id);
        return u;
    }
}
