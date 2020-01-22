package com.thd.cfg;

import ch.qos.logback.core.util.TimeUtil;
import feign.Logger;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * com.thd.cfg.FeignServiceConfiguration
 *
 * @author: wanglei62
 * @DATE: 2020/1/2 15:46
 **/
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLevel() {
        /**
         * Logger.Level有如下几种选择：
         * NONE, 不记录日志 (默认)。
         * BASIC, 只记录请求方法和URL以及响应状态代码和执行时间。
         * HEADERS, 记录请求和应答的头的基本信息。
         * FULL, 记录请求和响应的头信息，正文和元数据。
         */
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer feignRetryer(){
        /**
         * 第一个参数：超时时间
         * 第二个参数：最大超时时间
         * 第三个参数：重试5次
         *
         *
         */

        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1),5);

        // 关闭feign的重试
        //return Retryer.NEVER_RETRY;
    }
}
