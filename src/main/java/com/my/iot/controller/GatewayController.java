package com.my.iot.controller;

import com.my.iot.domain.Gateway;
import com.my.iot.domain.Result;
import com.my.iot.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {//该控制器采用restful风格，不同控制器方法访问url相同，通过请求方式和请求参数区分

    @Autowired
    private GatewayService gatewayService;

    @PostMapping(path = "")
    public Result addGateway(Gateway gateway) {//添加
        //保证网关有ip和port
        if (gateway.getIp() == null || gateway.getIp().isEmpty()
                || gateway.getPort() == null || gateway.getPort().isEmpty()) {
            return new Result(false, "some info null", null);
        }
        gatewayService.add(gateway);
        return new Result(true, "add success", gateway);
    }

    @PutMapping(path = "")
    public Result updateGateway(Gateway gateway) {//更改
        //System.out.println(gateway);
        //保证网关有ip和port
        if (gateway.getIp() == null || gateway.getIp().isEmpty()
                || gateway.getPort() == null || gateway.getPort().isEmpty()) {
            return new Result(false, "some info null", null);
        }
        Gateway g = gatewayService.findById(gateway.getId());
        if (g != null) {
            gatewayService.update(gateway);
            return new Result(true, "update success", gateway);
        } else {
            return new Result(false, "update failed, gateway not exist", null);
        }
    }

    @GetMapping(path = "/{id}")
    public Result getGatewayById(@PathVariable("id") int id) {//根据id查询
        Gateway gateway = gatewayService.findById(id);
        if (gateway != null) {
            return new Result(true, "get success", gateway);
        } else {
            return new Result(false, "get failed, gateway not exist", null);
        }
    }

    @DeleteMapping(path = "/{id}")
    public Result deleteGatewayById(@PathVariable("id") int id) {//根据id删除，注意！该操作会删除该网关下的所有传感器及其所有数据！
        Gateway gateway = gatewayService.findById(id);
        if (gateway != null) {
            gatewayService.deleteById(id);
            return new Result(true, "delete success", null);
        } else {
            return new Result(false, "delete failed , gateway not exist", null);
        }
    }

    @GetMapping(path = "")
    public Result getAllGateway(Boolean withSensors) {//请求不带这个布尔参数就不关联查询传感器
        List<Gateway> gateways = gatewayService.findAll(withSensors);//获取所有网关，关联?查询所有传感器
        if (gateways == null || gateways.size() == 0) {
            return new Result(false, "get failed , no gateway exist", null);
        }
        return new Result(true, "get success", gateways);
    }

}
