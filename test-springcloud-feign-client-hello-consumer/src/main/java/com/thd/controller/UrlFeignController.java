package com.thd.controller;

import com.thd.service.HelloService;
import com.thd.service.UrlFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlFeignController {

	@Autowired
	private UrlFeignService urlFeignService;
	
	@RequestMapping(value = "/testUrlFeign", method = RequestMethod.GET)
	public String testUrlFeign() {
		String html = urlFeignService.test();
		return html;
    }
	
}
