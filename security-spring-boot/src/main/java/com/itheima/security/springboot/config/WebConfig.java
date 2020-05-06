package com.itheima.security.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 这个文件相当于springmvc.xml文件
 * Spring Security通过了认证授权拦截的功能，无需在定义拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer
{

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("redirect:/login");
    }
}


