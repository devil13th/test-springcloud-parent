# Ribbon 超时重试

> 版本：Finchley.SR1

## 添加maven依赖

```
<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
            <groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
		<dependency>
			<groupId>org.springframework.retry</groupId>
			<artifactId>spring-retry</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

注意：一定要添加spring-retry这个包

## 启动类加@EnableRetry

这个不假也重试了

```
@EnableRetry
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientHelloConsumerApplication {
	...
}
```

## 添加配置项

#### 开启重试配置

application.yml 中添加 spring.cloud.loadbalancer.retry.enabled=true

```

spring:
  cloud:
    loadbalancer:
      retry:
        enabled: true  # 开启Spring Cloud的重试功能  设置为false即可关掉ribbon的重试机制，建议关掉，否则要做好幂等处理
```



#### 配置超时时间和重试次数

application.yml 

```
# ========================== ribbon 配置 ===========================
# ribbon 全局配置
# 使用ribbon 的全局配置ConnectTimeout和ReadTimeout可能不生效，参见启动类中的RestTemplate
ribbon:
  ConnectTimeout: 1000 # 请求连接的超时时间
  ReadTimeout: 2000 # 请求处理的超时时间
  MaxTotalConnections: 500 # 最大连接数
  MaxConnectionsPerHost: 500 # 每个host最大连接数
  ######## 重试相关配置 , 这个重试配置 对于ribbon 是生效的  ########
  # 重试次数= 1(首次访问) + MaxAutoRetries(重试次数)  + MaxAutoRetriesNextServer(切换服务的首次访问) + MaxAutoRetriesNextServer* MaxAutoRetries(每次切换服务的重试次数)
  # 对当前实例的重试次数
  MaxAutoRetries: 3
  # 切换实例的重试次数
  MaxAutoRetriesNextServer: 1
  # 如果该值为 false 那么只对 get 方法请起作用,如果为 true 那么对所有的请求方法起作用, 但是要true要慎用,如果服务端的接口不是幂等接口那么很有可能会产生重复数据
  OkToRetryOnAllOperations: true
  # 对Http响应码进行重试
  # retryableStatusCodes: 500,404,502
```

注：配置ribbon全局超时时间会有坑，这个配置是不生效的，要在配置类中的RestTemplate中进行设置，代码如下:

```
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
```

## 如何关闭重试

applicatoin.yml 中的spring.cloud.loadbalancer.retry.enabled设置为false即可