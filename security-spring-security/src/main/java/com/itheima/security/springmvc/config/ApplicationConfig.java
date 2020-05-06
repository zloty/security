package com.itheima.security.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 这个文件相当于applicationContext.xml
 *
 * basePackages = "com.itheima.security.springmvc",
 * excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)}
 * 扫描com.itheima.security.springmvc这个包，并且排除Controller这个注解的class
 */
@ComponentScan(
        basePackages = "com.itheima.security.springmvc",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)}
)
@Configuration
public class ApplicationConfig
{
    /* 在此配置除了Controller的其他bean，比如：数据库连接池、事务管理器、业务bean等 */
}
