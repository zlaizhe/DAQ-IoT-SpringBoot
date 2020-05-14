package com.my.iot.controller;

import com.my.iot.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class HomeController {
    private final String[] s = {
            "Talk is cheap. Show me the code.",
            "Stay hungry Stay foolish.",
            "Go big or go home.",
            "Done is better than perfect."
    };
    private final Random r = new Random();

    @Autowired
    private ServerConfig serverConfig;

    @GetMapping("/home")
    public String home() {
        return s[r.nextInt(4)];
    }

    @GetMapping("/address")//显示部署服务器的IP和端口号，用于测试nginx反向代理和负载均衡功能
    public String getIPandPort() {
        return "IP: " + serverConfig.getIp() + "\tPort: " + serverConfig.getPort();
    }
}
