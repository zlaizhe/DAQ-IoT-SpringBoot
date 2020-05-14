package com.my.iot.controller;

import com.my.iot.domain.GatewayException;
import com.my.iot.domain.PageBean;
import com.my.iot.domain.Result;
import com.my.iot.service.GatewayExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/gatewayException")
public class GatewayExceptionController {

    @Autowired
    private GatewayExceptionService gatewayExceptionService;

    @GetMapping("")
    public Result getAllGatewayException() {
        List<GatewayException> gatewayExceptions = gatewayExceptionService.findAll();
        return new Result(true, "get success", gatewayExceptions);
    }

    @GetMapping("/page/{page}")
    public Result getAllGatewayExceptionByPage(@PathVariable("page") int page) {
        PageBean<GatewayException> pageBean = gatewayExceptionService.findByPage(page);
        return new Result(true, "get success", pageBean);
    }

    @GetMapping("/{timetamp}")
    public Result getGatewayExceptionByTime(@PathVariable("timetamp") String timetamp) {//查询一段时间的异常，参数格式：时间戳1@时间戳2
        String[] s = timetamp.split("@");
        long dateFromTamp = Long.parseLong(s[0]);
        long dateToTamp = Long.parseLong(s[1]);
        List<GatewayException> gatewayExceptions = gatewayExceptionService.findByTime(new Date(dateFromTamp), new Date(dateToTamp));
        return new Result(true, "get success", gatewayExceptions);
    }
}
