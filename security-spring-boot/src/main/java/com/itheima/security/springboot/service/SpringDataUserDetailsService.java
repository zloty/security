package com.itheima.security.springboot.service;

import com.itheima.security.springboot.dao.UserDao;
import com.itheima.security.springboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringDataUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserDao userDao;
    //根据账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        //将来连接数据库根据账号查询用户信息
        UserDto user = userDao.getUserByUsername(username);
        if(user == null)
        {
            //如果用户查不到，返回null，由provider抛出异常
            return null;
        }
        UserDetails userDetails = User.withUsername(user.getUsername())
                .password(user.getPassword()).authorities("p1").build();
        return userDetails;
        //暂时采用静态数据
//        System.out.println("username = " + username);
//        UserDetails userDetails = User.withUsername("zs").password("$2a$10$/VgeNkua1JxAsqYs2irHFuGZ80Vdh7PIMOBSxTJqY0i4/7TQUDtci").authorities("p1").build();
//        return userDetails;
    }
}
