package com.thd.service;

import com.thd.cfg.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="EUREKA-CLIENT-HELLO-PROVIDER",fallbackFactory=HelloServiceFallbackFactory.class,configuration=FeignConfiguration.class)
public interface HelloService {
	@RequestMapping(value="/hello/{name}",method=RequestMethod.GET)//只能是RequestMapping
	public String hello(@PathVariable("name") String name);  //@PathVariable需要设置括号中的名称
}
