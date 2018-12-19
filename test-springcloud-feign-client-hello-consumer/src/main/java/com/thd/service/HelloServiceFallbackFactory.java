package com.thd.service;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component // 不要忘记添加，不要忘记添加
public class HelloServiceFallbackFactory implements FallbackFactory<HelloService>{

	@Override
	public HelloService create(Throwable arg0) {
		return new HelloService(){

			@Override
			public String hello(String name) {
				// TODO Auto-generated method stub
				return " sorry " + name + ",  error";
			}
		};
	}
}
