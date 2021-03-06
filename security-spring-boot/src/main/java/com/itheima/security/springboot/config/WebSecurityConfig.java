package com.itheima.security.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
//    //定义用户信息服务（查询用户信息）
//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        //可以从数据库查询，这里暂时从内存中查询
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return manager;
//    }

    //定义密码编码器
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        //这里暂时使用字符串比较的密码编码规则
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }


    //配置安全拦截机制（重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/r/r1").hasAnyAuthority("p1")
//                .antMatchers("/r/r2").hasAnyAuthority("p2")
                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
                .anyRequest().permitAll()//除了/r/**，其它的请求可以访问
                .and()
                .formLogin()//允许表单登录
                .loginPage("/login-view")//登录页面
                .loginProcessingUrl("/login")
                .successForwardUrl("/login-success")//自定义登录成功的页面地址
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .logout()
                .logoutUrl("logout")
                .logoutSuccessUrl("/login-view?logout")
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .addLogoutHandler(logoutHandler)
                .invalidateHttpSession(true);
    }
}

