package com.my.iot.service.impl;

import com.my.iot.domain.Gateway;
import com.my.iot.domain.Sensor;
import com.my.iot.domain.SensorClassify;
import com.my.iot.mapper.SensorClassifyMapper;
import com.my.iot.mapper.SensorMapper;
import com.my.iot.service.SensorClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sensorClassifyService")
public class SensorClassifyServiceImpl implements SensorClassifyService {

    public static final String SENSOR_CLASSIFY = "sensor_classify_";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SensorClassifyMapper sensorClassifyMapper;

    @Autowired
    private SensorMapper sensorMapper;

    @Override
    public void add(SensorClassify classify) {
        sensorClassifyMapper.add(classify);
    }

    @Override
    public List<SensorClassify> findAll(Boolean withSensors) {
        List<SensorClassify> classifies = sensorClassifyMapper.findAll();//查询所有分类
        if (new Boolean(true).equals(withSensors)) {
            for (SensorClassify classify : classifies) {//查询并封装每个分类下的传感器
                Integer classifyId = classify.getId();
                List<Sensor> sensors = sensorMapper.findByClassifyId(classifyId);
                classify.setSensors(sensors);
            }
        }
        return classifies;
    }

    @Override
    public SensorClassify findById(int id) {
        SensorClassify sensorClassify = (SensorClassify) redisTemplate.opsForValue().get(SENSOR_CLASSIFY + id);//先从缓存查询
        if (sensorClassify == null) {
            sensorClassify = sensorClassifyMapper.findById(id);
            redisTemplate.opsForValue().set(SENSOR_CLASSIFY + id, sensorClassify);
        }
        return sensorClassify;
    }

    @Override
    public List<SensorClassify> findByGatewayId(int gateway_id) {
        return sensorClassifyMapper.findByGatewayId(gateway_id);
    }
}
