package com.thd.vo;

/**
 * com.thd.vo.User
 *
 * @author: wanglei62
 * @DATE: 2020/1/2 14:35
 **/
public class MyUser {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
