package com.my.iot.service.impl;

import com.my.iot.domain.Gateway;
import com.my.iot.domain.Sensor;
import com.my.iot.mapper.DataMapper;
import com.my.iot.mapper.GatewayMapper;
import com.my.iot.mapper.SensorMapper;
import com.my.iot.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("gatewayService")
public class GatewayServiceImpl implements GatewayService {

    public static final String GATEWAY = "gateway_";

    @Autowired
    private GatewayMapper gatewayMapper;

    @Autowired
    private SensorMapper sensorMapper;

    @Autowired
    private DataMapper dataMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void add(Gateway gateway) {
        gatewayMapper.insert(gateway);
    }

    @Override
    public Gateway findById(int id) {
        Gateway gateway = (Gateway) redisTemplate.opsForValue().get(GATEWAY + id);//先从缓存查询
        if (gateway != null) {
            //System.out.println("从缓存查询");
            return gateway;
        } else {
            //System.out.println("从数据库查询");
            gateway = gatewayMapper.findById(id);
            redisTemplate.opsForValue().set(GATEWAY + id, gateway);//存入缓存
        }
        return gateway;
    }

    @Override
    public void update(Gateway gateway) {
        redisTemplate.delete(GATEWAY + gateway.getId());//清除对应缓存
        gatewayMapper.update(gateway);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        //先删除网关下的传感器及其所有数据
        List<Sensor> sensors = sensorMapper.findByGatewayId(id);
        for (Sensor sensor : sensors) {
            Integer sensorId = sensor.getId();
            dataMapper.deleteBySensorId(sensorId);
            sensorMapper.deleteById(sensorId);
        }
        //再删除网关
        redisTemplate.delete(GATEWAY + id);//清除对应缓存
        gatewayMapper.deleteById(id);
    }

    @Override
    public List<Gateway> findAll(Boolean withSensors) {//所有信息可能经常变化，不使用缓存
        List<Gateway> gateways = gatewayMapper.findAll();//查询所有网关信息
        if (new Boolean(true).equals(withSensors)) {
            for (Gateway gateway : gateways) {//查询并封装网关的传感器信息
                Integer gatewayId = gateway.getId();
                List<Sensor> sensors = sensorMapper.findByGatewayId(gatewayId);
                gateway.setSensors(sensors);
            }
        }
        return gateways;
    }

    @Override
    public Gateway findByIdWithSensors(int id) {//传感器信息可能经常变化，不使用缓存
        return gatewayMapper.findByIdWithSensors(id);
    }
}
