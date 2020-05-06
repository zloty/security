package com.itheima.security.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor //所有参数作为构造方法参数
public class UserDto
{
    public static final String SESSION_USER_KEY = "_user";
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    /**
     * 用户权限
     */
    private Set<String> authorities;
}
