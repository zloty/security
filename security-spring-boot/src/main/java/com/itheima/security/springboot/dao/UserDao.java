package com.itheima.security.springboot.dao;

import com.itheima.security.springboot.model.PermissionDto;
import com.itheima.security.springboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao
{
    @Autowired
    JdbcTemplate jdbcTemplate;
    //根据账号查询用户信息
    public UserDto getUserByUsername(String username)
    {
        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        List<UserDto> list = jdbcTemplate.query(sql, new Object[]{username},
                new BeanPropertyRowMapper<>(UserDto.class));
        if(list != null && list.size() == 1)
        {
            return list.get(0);
        }
        return null;
    }

    //根据用户ID查询用户权限
    public List<String> findPermissIONByUserId(String userId)
    {
        String sql = "select * from t_permission where id in (\n" +
                "\tselect permission_id from t_role_permission where role_id in (\n" +
                "\t\tselect role_id from t_user_role where user_id=?\n" +
                "\t)\n" +
                ")";
        List<PermissionDto> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permission = new ArrayList<>();
        list.forEach(v->permission.add(v.getCode()));
        return permission;
    }
}
