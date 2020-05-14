package com.my.iot.config;

import com.my.iot.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//添加登录拦截器的配置
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")//拦截所有路径
        .excludePathPatterns(
                "/api/address",
                "/api/home",
                "/api/user/login",
                "/api/user/info",
                "/api/user/exit");//放行路径
    }
}
