package com.itheima.security.springmvc.service;

import com.itheima.security.springmvc.model.AuthenticationRequest;
import com.itheima.security.springmvc.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenicationServiceImpl implements AuthenticationService
{
    /**
     * 校验用户身份信息
     * @param authenticationRequest 用户认证请求，账号和密码
     * @return
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest)
    {
        //校验参数是否为空
        if(authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword()))
        {
            throw new RuntimeException("账号或密码为空");
        }

        UserDto user = getUserDto(authenticationRequest.getUsername());
        //判断用户是否为空
        if(user == null)
        {
            throw new RuntimeException("查询不到该用户");
        }

        //校验密码
        if(!user.getPassword().equals(authenticationRequest.getPassword()))
        {
            throw new RuntimeException("账号或密码错误");
        }

        //认证通过，返回用户身份信息
        return user;
    }

    //模拟用户查询
    public UserDto getUserDto(String username)
    {
        return userMap.get(username);
    }

    //用户信息
    private Map<String,UserDto> userMap = new HashMap<>();
    {
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");//这个p1我们人为让它和/r/r1对应
        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");//这个p2我们人为让它和/r/r2对应
        userMap.put("zhangsan",new UserDto("1010","zhangsan","123","张三","13343",authorities1));
        userMap.put("lisi",new UserDto("1011","lisi","456","李四","144553",authorities2));
    }
}
