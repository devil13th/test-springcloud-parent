package com.thd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

        /**
         * ribbon会拦截RestTemplate之后取出"user-server"字符串（参见下面例如中的内容），
         * 以"user-server"作为服务ID找eureka获取服务实例，之后自动进行拼接（即自动拼接地址及端口），
         * 并使用拼接后的url重新发送请求
         *
         *  例如：
         *  String url = "http://user-server/user/"+id; //此处直接写服务ID即可
         *  User user = restTemplate.getForObject(url, User.class);
         *
         * 因此@LoadBalanced注解必须加在RestTemplate类上（参见启动类中的注解）
         */
        return restTemplate.getForEntity("http://EUREKA-CLIENT-HELLO-PROVIDER/hello", String.class).getBody();

    }

    // 测试重试
    // url:http://127.0.0.1:9001/sleep/8000
    @RequestMapping(value = "/sleep/{t}", method = RequestMethod.GET)
    public String sleep(@PathVariable long t) {
        System.out.println("consumer.sleep 被调用 ");
        return restTemplate.getForEntity("http://EUREKA-CLIENT-HELLO-PROVIDER/sleep/" + t, String.class).getBody();
    }


    @RequestMapping(value = "/retryTest", method = RequestMethod.GET)
    public String retryTest() {
        System.out.println("consumer.retryTest 被调用 ");
        return restTemplate.getForEntity("http://EUREKA-CLIENT-HELLO-PROVIDER/retryTest", String.class).getBody();
    }
}
