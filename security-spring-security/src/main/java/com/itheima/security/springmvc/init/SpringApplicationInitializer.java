package com.itheima.security.springmvc.init;

import com.itheima.security.springmvc.config.ApplicationConfig;
import com.itheima.security.springmvc.config.WebConfig;
import com.itheima.security.springmvc.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    /* Spring容器 相当于加载了applicationContext.xml*/
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]{ApplicationConfig.class, WebSecurityConfig.class};
    }

    /* servletContext，相当于加载了springmvc.xml */
    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]{WebConfig.class};
    }

    /* url-mapping */
    @Override
    protected String[] getServletMappings()
    {
        /* 可以自定义路径，这里用/：跟路径 */
        return new String[]{"/"};
    }
}