package com.thd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * com.thd.service.UrlFeignService
 *
 * @author: wanglei62
 * @DATE: 2021/3/18 14:26
 **/
@FeignClient(value="test",url="http://www.baidu.com")
public interface UrlFeignService {
    @GetMapping(value = "/")
    public String test();
}
