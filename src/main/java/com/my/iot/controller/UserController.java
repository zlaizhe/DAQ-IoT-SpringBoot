package com.my.iot.controller;

import com.my.iot.domain.Result;
import com.my.iot.domain.User;
import com.my.iot.service.UserService;
import com.my.iot.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/user")
public class UserController {

    public static final String LOGIN_USER = "login_user";

    @Autowired
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;//利用Redis实现分布式Session

    @PostMapping("/regist")
    public Result regist(User user) throws Exception {//注册
        //调用service完成注册
        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));//对密码加密
        boolean flag = userService.regist(user);
        return new Result(flag, flag ? "regist success" : "regist failed", null);
    }

    @PostMapping("/login")
    public Result login(User user, HttpSession session) throws Exception {//登录
        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));//对密码加密
        //调用service完成登录
        User u = userService.login(user);
        if (u == null) {
            return new Result(false, "login failed", null);
        } else {
            String sessionId = session.getId();//登录成功创建session，获取sessionId
            redisTemplate.opsForHash().put(sessionId, LOGIN_USER, u);//key sessionId, hkey login_user, value 登录用户
            redisTemplate.expire(sessionId, 7, TimeUnit.DAYS);//有效时间7天
            return new Result(true, "login success", u);
        }
    }

    @GetMapping("/info")
    public Result loginUserInfo(@CookieValue("JSESSIONID") String sessionId) {//查看登录用户信息
        if (sessionId == null || sessionId.isEmpty()) {
            return new Result(false, "no login, without JSESSIONID", null);
        }
        User login_user = (User) redisTemplate.opsForHash().get(sessionId, LOGIN_USER);
        if (login_user == null) {//未登录
            return new Result(false, "no login", null);
        }
        return new Result(true, "success", login_user);//已登录
    }

    @GetMapping("/exit")
    public Result exit(@CookieValue("JSESSIONID") String sessionId) {//退出登录
        User login_user = (User) redisTemplate.opsForHash().get(sessionId, LOGIN_USER);
        if (login_user == null) {
            return new Result(false, "exit failed, no login", null);
        }
        redisTemplate.delete(sessionId);
        return new Result(true, "exit success", null);
    }

    //以下方法要经过拦截器的登录拦截
    @PostMapping("/password")
    public Result changePassword(User user, String newPassword, @CookieValue("JSESSIONID") String sessionId) throws Exception {//修改密码
        //1.拿到登录用户
        User login_user = (User) redisTemplate.opsForHash().get(sessionId, LOGIN_USER);
        //2.验证用户旧密码是否正确
        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));//对密码加密
        if (userService.login(user) == null) {
            return new Result(false, "failed, old password error", null);
        }
        //3.更改新密码
        newPassword = Md5Util.encodeByMd5(newPassword);
        login_user.setPassword(newPassword);
        //调用service完成修改密码
        userService.update(login_user);
        //更新redis
        redisTemplate.opsForHash().put(sessionId, LOGIN_USER, login_user);
        return new Result(true, "change password success", null);
    }

    //以下方法要经过拦截器的登录拦截
    @PostMapping("/modify")
    public Result changeBasicInformation(User user, @CookieValue("JSESSIONID") String sessionId) {//修改用户基本信息（用户名不能修改）
        //1.拿到登录用户
        User login_user = (User) redisTemplate.opsForHash().get(sessionId, LOGIN_USER);
        //2.更新基本信息
        login_user.setEmail(user.getEmail());
        login_user.setNickname(user.getNickname());
        login_user.setTel(user.getTel());
        //调用service完成修改基本信息
        userService.update(login_user);
        //更新redis
        redisTemplate.opsForHash().put(sessionId, LOGIN_USER, login_user);
        return new Result(true, "change basic information success", login_user);
    }
}
