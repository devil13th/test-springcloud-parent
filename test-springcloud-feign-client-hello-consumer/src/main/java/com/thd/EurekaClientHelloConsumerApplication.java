package com.thd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@EnableHystrix // Feign默认是开启，这个注解可以不加的
@EnableEurekaClient
@EnableFeignClients(basePackages="com.thd")
@ComponentScan("com.thd")
@SpringBootApplication
@EnableCircuitBreaker//打开Hystrix断路器
public class EurekaClientHelloConsumerApplication {
	
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	public static void main(String[] args) {

		ConfigurableApplicationContext cfg = SpringApplication.run(EurekaClientHelloConsumerApplication.class, args);
		String[] beans = cfg.getBeanDefinitionNames();
		for(int i = 0 , j = beans.length ; i < j ; i++){
			System.out.println(beans[i]);
		}

	}
	
	
	/**
	 * 集群启动
	 * 
	 * java -jar test-springcloud-Dalston-eureka-client-consumer-0.0.1-SNAPSHOT.jar --server.port=9001 --eureka.instance.instance-id=consumer9001  --eureka.client.service-url.defaultZone=http://server1:7001/eureka
     * java -jar test-springcloud-Dalston-eureka-client-consumer-0.0.1-SNAPSHOT.jar --server.port=9002 --eureka.instance.instance-id=consumer9002  --eureka.client.service-url.defaultZone=http://server2:7002/eureka
     * java -jar test-springcloud-Dalston-eureka-client-consumer-0.0.1-SNAPSHOT.jar --server.port=9003 --eureka.instance.instance-id=consumer9003  --eureka.client.service-url.defaultZone=http://server3:7003/eureka
	 */

}
