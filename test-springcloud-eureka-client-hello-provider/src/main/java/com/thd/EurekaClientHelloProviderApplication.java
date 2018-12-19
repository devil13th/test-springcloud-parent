package com.thd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@EnableEurekaClient
@SpringBootApplication
public class EurekaClientHelloProviderApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaClientHelloProviderApplication.class, args);
	}
	
	/**
	 * 集群启动命令
	 * java -jar test-springcloud-eureka-client-hello-provider-0.0.1-SNAPSHOT.jar --server.port=9001 --eureka.instance.instance-id=provider9001  --eureka.client.service-url.defaultZone=http://server1:7001/eureka
	 * java -jar test-springcloud-eureka-client-hello-provider-0.0.1-SNAPSHOT.jar --server.port=9002 --eureka.instance.instance-id=provider9002  --eureka.client.service-url.defaultZone=http://server2:7002/eureka
	 * java -jar test-springcloud-eureka-client-hello-provider-0.0.1-SNAPSHOT.jar --server.port=9003 --eureka.instance.instance-id=provider9003  --eureka.client.service-url.defaultZone=http://server3:7003/eureka
	 */

}
