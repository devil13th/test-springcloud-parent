package com.thd.service;

import com.thd.vo.MyUser;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * com.thd.service.UserServiceFallbackFactory
 *
 * @author: wanglei62
 * @DATE: 2020/1/2 15:01
 **/
@Component // 不要忘记添加，不要忘记添加
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable arg0) {
        return new UserService(){
            @Override
            public String sleep(Long msg) {
                System.out.println("cause:" + arg0);
                return "sleep err , i'm fallback";
            }
            @Override
            public String test(String msg) {
                return "test err";
            }

            @Override
            public Map<String, MyUser> queryUser() {
                return new HashMap<String,MyUser>();
            }

            @Override
            public MyUser addUser(String name) {
                return new MyUser("addUser err","addUser err");
            }

            @Override
            public MyUser updateUser(String id, String name) {
                return new MyUser("updateUser err","updateUser err");
            }

            @Override
            public MyUser deleteUser(String id) {
                return new MyUser("deleteUser err","deleteUser err");
            }
        };
    }
}
