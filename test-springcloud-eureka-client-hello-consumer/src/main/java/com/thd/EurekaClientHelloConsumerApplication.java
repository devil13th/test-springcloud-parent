package com.thd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

// 开启重试，但是不加貌似也可以开启重试 用的是spring-retry ， 配置文件为 spring.cloud.loadbalancer.retry.enabled=true
@EnableRetry
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientHelloConsumerApplication {


	/**
	 * ribbon会拦截RestTemplate之后取出"user-server"字符串（参见下面例如中的内容），
	 * 以"user-server"作为服务ID找eureka获取服务实例，之后自动进行拼接（即自动拼接地址及端口），
	 * 并使用拼接后的url重新发送请求
	 *
	 *  例如：
	 *  String url = "http://user-server/user/"+id; //此处直接写服务ID即可
	 *  User user = restTemplate.getForObject(url, User.class);
	 *
	 *
	 * 因此@LoadBalanced注解必须加在RestTemplate类上
	 * @return
	 */
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(2000);
		factory.setConnectTimeout(2000);
      return new RestTemplate(factory);

      // 用下面的代码全局超时设置ribbon.ConnectionTimeout和 ribbon.ReadTimeout不生效
	  // return new RestTemplate();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaClientHelloConsumerApplication.class, args);
	}
	
	
	/**
	 * 集群启动
	 * 
	 * java -jar test-springcloud-eureka-client-hello-consumer-0.0.1-SNAPSHOT.jar --server.port=9001 --eureka.instance.instance-id=consumer9001  --eureka.client.service-url.defaultZone=http://server1:7001/eureka
     * java -jar test-springcloud-eureka-client-hello-consumer-0.0.1-SNAPSHOT.jar --server.port=9002 --eureka.instance.instance-id=consumer9002  --eureka.client.service-url.defaultZone=http://server2:7002/eureka
     * java -jar test-springcloud-eureka-client-hello-consumer-0.0.1-SNAPSHOT.jar --server.port=9003 --eureka.instance.instance-id=consumer9003  --eureka.client.service-url.defaultZone=http://server3:7003/eureka
	 */

}
