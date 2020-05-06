package com.itheima.security.springmvc.controller;

import com.itheima.security.springmvc.model.AuthenticationRequest;
import com.itheima.security.springmvc.model.UserDto;
import com.itheima.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController
{
    @Autowired
    private AuthenticationService authenticationService;
    @RequestMapping(value = "/login",produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest authenticationRequest, HttpSession session)
    {
        UserDto userDto = authenticationService.authentication(authenticationRequest);
        //存入session
        session.setAttribute(userDto.SESSION_USER_KEY,userDto);
        return userDto.getUsername() + "登录成功";
    }

    /**
     * 测试资源一，获取session
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=utf-8"})
    public String r1(HttpSession session)
    {
        String fullname = null;
        Object obj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if(obj == null)
        {
            fullname = "匿名";
        } else {
            UserDto userDto = (UserDto) obj;
            fullname = userDto.getFullname();
        }
        return fullname + "访问资r1";
    }

    /**
     * 测试资源二，获取session
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=utf-8"})
    public String r2(HttpSession session)
    {
        String fullname = null;
        Object obj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if(obj == null)
        {
            fullname = "匿名";
        } else {
            UserDto userDto = (UserDto) obj;
            fullname = userDto.getFullname();
        }
        return fullname + "访问资源r2";
    }

    @GetMapping(value = "logout",produces = "text/plain;charset=utf-8")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "退出成功";
    }
}
