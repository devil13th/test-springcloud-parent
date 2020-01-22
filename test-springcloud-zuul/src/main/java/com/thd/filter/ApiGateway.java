package com.thd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * com.thd.filter.ApiGateway
 *
 * @author: wanglei62
 * @DATE: 2020/1/6 12:35
 **/
@Configuration
public class ApiGateway extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        System.out.println("zuul route ");
        //Principal principal = request.getUserPrincipal();
        //获取用户的登录id
        //String userId = principal.getName();
        //context.addZuulRequestHeader("X-AUTH-ID",userId);

        return null;
    }
}
