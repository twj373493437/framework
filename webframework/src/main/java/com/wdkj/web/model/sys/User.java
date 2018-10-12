package com.wdkj.web.model.sys;

/**
 * 用户信息没别要加上业务相关的字段
 * 如果有业务相关的字段，加一张表来扩展即可
 * @author twj
 * @date 2018/9/19 17:23
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private String fullName;

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
