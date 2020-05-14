package com.my.iot.controller;

import com.my.iot.domain.Gateway;
import com.my.iot.domain.Result;
import com.my.iot.domain.Sensor;
import com.my.iot.domain.SensorClassify;
import com.my.iot.service.GatewayService;
import com.my.iot.service.SensorClassifyService;
import com.my.iot.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private SensorClassifyService sensorClassifyService;

    @Autowired
    private SensorService sensorService;

    @PostMapping(path = "")
    public Result addSensor(Sensor sensor) {//此处需要配置自定义类型转换器将日期默认yyyy/MM/dd绑定格式改为yyyy-MM-dd
        Integer gate_id = sensor.getGate_id();
        Integer classify_id = sensor.getClassify_id();
        //gate_id 和 classify_id 保证非空
        if (gate_id == null || classify_id == null) {
            return new Result(false, "gate_id and classify_id is nessesary", null);
        }
        //查询关联网关
        Gateway gateway = gatewayService.findById(gate_id);
        if (gateway == null) {
            return new Result(false, "gate_id not exist", null);
        }
        //查询关联种类
        SensorClassify sensorClassify = sensorClassifyService.findById(classify_id);
        if (sensorClassify == null) {
            return new Result(false, "classify_id not exist", null);
        }
        //设置关联数据
        //sensor.setGateway(gateway);
        // sensor.setSensorClassify(sensorClassify);
        sensorService.add(sensor);
        return new Result(true, "add success", sensor);
    }

    @GetMapping(path = "/{id}")
    public Result getSensorById(@PathVariable("id") int id) {
        Sensor sensor = sensorService.findById(id);
        if (sensor == null) {
            return new Result(false, "sensor not exist", null);
        }
        return new Result(true, "get success", sensor);
    }

    @GetMapping(path = "/")
    public Result getAllSensors() {
        List<Sensor> sensors = sensorService.findAll();
        if (sensors == null || sensors.size() == 0) {
            return new Result(false, "no sensor exist", null);
        }
        return new Result(true, "get success", sensors);
    }

    @PutMapping(path = "")
    public Result updateSensor(Sensor sensor) {
        Integer id = sensor.getId();
        if (id == null) {//id 非空
            return new Result(false, "id is nessesary", null);
        }
        Sensor s = sensorService.findById(id);
        if (s == null) {// 传感器存在
            return new Result(false, "sensor not exist", null);
        }

        Integer gate_id = sensor.getGate_id();
        Integer classify_id = sensor.getClassify_id();
        if (gate_id == null || classify_id == null) {//gate_id 和 classify_id 保证非空
            return new Result(false, "gate_id and classify_id is nessesary", null);
        }
        //查询关联网关
        Gateway gateway = gatewayService.findById(gate_id);
        if (gateway == null) {
            return new Result(false, "gate_id not exist", null);
        }
        //查询关联种类
        SensorClassify sensorClassify = sensorClassifyService.findById(classify_id);
        if (sensorClassify == null) {
            return new Result(false, "classify_id not exist", null);
        }

        sensorService.update(sensor);
        return new Result(true, "update success", sensor);
    }

    @DeleteMapping(path = "/{id}")
    public Result deteleSensorById(@PathVariable("id") int id) {//注意！删除传感器会将其所有数据一并删除！
        Sensor s = sensorService.findById(id);
        if (s == null) {// 传感器存在
            return new Result(false, "delete failed, sensor not exist", null);
        }
        sensorService.deleteById(id);
        return new Result(true, "detele success", null);
    }

    @GetMapping(path = "/classify/{id}")
    public Result getSensorByClassifyId(@PathVariable("id") int classify_id) {//获取某一分类下的所有传感器
        SensorClassify sensorClassify = sensorClassifyService.findById(classify_id);
        if (sensorClassify == null) {// 传感器分类存在
            return new Result(false, "sensor classify not exist", null);
        }
        List<Sensor> sensors = sensorService.findByClassifyId(classify_id);
        if (sensors == null || sensors.isEmpty()) {
            return new Result(false, "sensors not exist", null);
        }
        return new Result(true, "get success", sensors);
    }

    @GetMapping(path = "/gateway/{id}")
    public Result getSensorByGatewayId(@PathVariable("id") int gateway_id) {//获取某一网关下的所有传感器
        Gateway gateway = gatewayService.findById(gateway_id);
        if (gateway == null) {// 网关存在
            return new Result(false, "gateway not exist", null);
        }
        List<Sensor> sensors = sensorService.findByGatewayId(gateway_id);
        if (sensors == null || sensors.isEmpty()) {
            return new Result(false, "sensors not exist", null);
        }
        return new Result(true, "get success", sensors);
    }
}
