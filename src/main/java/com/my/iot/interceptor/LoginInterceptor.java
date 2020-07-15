package com.my.iot.interceptor;

import com.my.iot.controller.UserController;
import com.my.iot.domain.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截所有未登录的请求
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String json = "{\"status\":false, \"message\":\"please login\", \"data\":null}";//未登录被拦截时的响应
    private static final String JSESSIONID = "JSESSIONID";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("拦截器...preHandle...");
        String sessionId = getJSESSIONID(request);
        response.setContentType("text/json;charset=UTF-8");
        if (sessionId == null) {//请求中没有JSESSIONID，不放行
            //response.getWriter().write(json);
            response.sendRedirect("/html/login.html");
            return false;
        }
        //从redis中查询登录用户
        User login_user = (User) redisTemplate.opsForHash().get(sessionId, UserController.LOGIN_USER);
        if (login_user == null) {  //未登录，不放行
            //response.getWriter().write(json);
            response.sendRedirect("/html/login.html");
            return false;
        } else {
            return true;//登陆后放行
        }
    }

    //从请求中获取名称为JSESSIONID的Cookie值
    private String getJSESSIONID(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (JSESSIONID.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
