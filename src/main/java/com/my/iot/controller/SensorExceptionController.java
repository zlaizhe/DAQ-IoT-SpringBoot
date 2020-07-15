package com.my.iot.controller;

import com.my.iot.domain.GatewayException;
import com.my.iot.domain.PageBean;
import com.my.iot.domain.Result;
import com.my.iot.domain.SensorException;
import com.my.iot.service.SensorExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sensorException")
public class SensorExceptionController {
    @Autowired
    private SensorExceptionService sensorExceptionService;

    @GetMapping("")
    public Result getAllSensorException() {
        List<SensorException> sensorExceptions = sensorExceptionService.findAll();
        return new Result(true, "get success", sensorExceptions);
    }

    @GetMapping("/page/{page}")
    public Result getAllSensorExceptionByPage(@PathVariable("page") int page) {
        PageBean<SensorException> pageBean = sensorExceptionService.findByPage(page);
        return new Result(true, "get success", pageBean);
    }

    @GetMapping("/{timetamp}")
    public Result getAllSensorExceptionByTime(@PathVariable("timetamp") String timetamp) {//查询一段时间的异常，参数格式：时间戳1@时间戳2
        String[] s = timetamp.split("@");
        long dateFromTamp = Long.parseLong(s[0]);
        long dateToTamp = Long.parseLong(s[1]);
        List<SensorException> sensorExceptions = sensorExceptionService.findByTime(new Date(dateFromTamp), new Date(dateToTamp));
        return new Result(true, "get success", sensorExceptions);
    }
    @GetMapping("/time")
    public Result getGatewayExceptionByDateTime(Date datetime1, Date datetime2) {//查询一段时间的异常
        List<SensorException> sensorExceptions = sensorExceptionService.findByTime(datetime1, datetime2);
        return new Result(true, "get success", sensorExceptions);
    }
}
