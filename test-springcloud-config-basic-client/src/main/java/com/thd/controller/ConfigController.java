package com.thd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * com.thd.controller.ConfigController
 *
 * @author: wanglei62
 * @DATE: 2021/1/19 9:53
 **/
@Controller
@RequestMapping("/config")
public class ConfigController {

    @Value("${name}")
    private String baseInfoName = "hello";

    @ResponseBody
    @RequestMapping("/test")
    // url : http://127.0.0.1:8012/config/test
    public String test(){

        return this.baseInfoName;
    }
}
