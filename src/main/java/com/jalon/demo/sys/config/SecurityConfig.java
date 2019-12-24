package com.jalon.demo.sys.config;

import com.jalon.demo.sys.service.DemoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DemoUserDetailService userDetailService;

    /**
     * 认证管理器配置
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置Spring Security的Filter链
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",//swagger api json
                "/swagger-resources/configuration/ui",//用来获取支持的动作
                "/swagger-resources",//用来获取api-docs的URI
                "/swagger-resources/configuration/security",//安全选项
                "/swagger-ui.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //认证相关
                // 这里指定不需要认证的请求
                .authorizeRequests().antMatchers("/home", "/login").permitAll()
                // 其他则需要通过认证
                .anyRequest().authenticated()
                .and()
                // 登录相关
                .formLogin()
                // loginPage:指定登录页面
                .loginPage("/login")
                // defaultSuccessUrl:指定登录成功后跳转页面
                .defaultSuccessUrl("/index")
                // loginProcessingUrl:指定登录请求
                .loginProcessingUrl("/login.json")
                // usernameParameter:用户名参数名
                .usernameParameter("username")
                // passwordParameter:密码参数名
                .passwordParameter("password")
                .permitAll()
                .and()
                // 登出相关
                .logout()
                // logoutUrl:登出请求url
                .logoutUrl("/logout.json")
                // logoutSuccessUrl:登出成功跳转页面
                .logoutSuccessUrl("/home").permitAll();
    }
}
