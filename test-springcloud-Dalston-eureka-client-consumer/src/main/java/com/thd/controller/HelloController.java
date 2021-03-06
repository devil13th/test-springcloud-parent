package com.thd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
	@Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
    	System.out.println("consumer 被调用 ");
        return restTemplate.getForEntity("http://MICROSERVICECLOUD-DEPT/hello", String.class).getBody();
    }
}
