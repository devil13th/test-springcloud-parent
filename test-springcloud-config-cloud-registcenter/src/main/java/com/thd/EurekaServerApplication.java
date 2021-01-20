package com.thd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;



@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
	
	/**
	 * 集群方式启动
	 * 修改host文件:	 
	 * 127.0.0.1 server1
	 * 127.0.0.1 server2
	 * 127.0.0.1 server3
	 * 
	 * 启动命令:
	 * java -jar eserver.jar --server.port=7001 --eureka.instance.hostname=server1 --eureka.client.service-url.defaultZone=http://server2:7002/eureka,http://server3:7003/eureka,http://server1:7001/eureka
	 * java -jar eserver.jar --server.port=7002 --eureka.instance.hostname=server2 --eureka.client.service-url.defaultZone=http://server1:7001/eureka,http://server3:7003/eureka,http://server2:7002/eureka
	 * java -jar eserver.jar --server.port=7003 --eureka.instance.hostname=server3 --eureka.client.service-url.defaultZone=http://server1:7001/eureka,http://server2:7002/eureka,http://server3:7003/eureka
	 */

}
