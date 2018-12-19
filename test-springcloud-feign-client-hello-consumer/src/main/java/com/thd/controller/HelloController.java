package com.thd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thd.service.HelloService;

@RestController
public class HelloController {
	/*@Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
    	System.out.println("consumer 被调用 ");
        return restTemplate.getForEntity("http://EUREKA-CLIENT-HELLO-PROVIDER/hello", String.class).getBody();
    }
    */
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	public String hello(@PathVariable String name) {
    	System.out.println("consumer.hello() 被调用 ");
    	System.out.println(name);
        return helloService.hello(name);
    }
	
}
