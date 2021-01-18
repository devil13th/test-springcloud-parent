package com.thd.cfg;

import ch.qos.logback.core.util.TimeUtil;
import feign.Logger;

import feign.RequestInterceptor;
import feign.Retryer;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * com.thd.cfg.FeignServiceConfiguration
 *
 * @author: wanglei62
 * @DATE: 2020/1/2 15:46
 **/
@Configuration
public class FeignConfiguration {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
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



    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            System.out.println(" FeignConfiguration : =================================");
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                // 如果在Cookie内通过如下方式取
                Cookie[] cookies = request.getCookies();

                if (cookies != null && cookies.length > 0) {
                    for (Cookie cookie : cookies) {
                        logger.info(" restTemplate set cookie :" +  cookie.getName() + "," + cookie.getValue());
                        requestTemplate.header(cookie.getName(), cookie.getValue());
                    }
                } else {
                    logger.warn("FeignHeadConfiguration", "获取Cookie失败！");
                }
                // 如果放在header内通过如下方式取
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String value = request.getHeader(name);
                        /**
                         * 遍历请求头里面的属性字段，将jsessionid添加到新的请求头中转发到下游服务
                         * */
                        logger.info(" restTemplate set head :" +  name + "," + value);
                        if ("jsessionid".equalsIgnoreCase(name)) {
                            logger.debug("添加自定义请求头key:" + name + ",value:" + value);
                            requestTemplate.header(name, value);
                        } else {
                            logger.debug("FeignHeadConfiguration", "非自定义请求头key:" + name + ",value:" + value + "不需要添加!");
                        }

                        if ("cookie".equalsIgnoreCase(name)) {
                            logger.debug("添加自定义请求头key:" + name + ",value:" + value);
                            requestTemplate.header(name, value);
                        } else {
                            logger.debug("FeignHeadConfiguration", "非自定义请求头key:" + name + ",value:" + value + "不需要添加!");
                        }
                    }
                } else {
                    logger.warn("FeignHeadConfiguration", "获取请求头失败！");
                }
            }
        };
    }
}
