package com.my.iot.service.impl;

import com.my.iot.domain.Sensor;
import com.my.iot.domain.SensorClassify;
import com.my.iot.mapper.DataMapper;
import com.my.iot.mapper.SensorMapper;
import com.my.iot.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("SensorService")
public class SensorServiceImpl implements SensorService {

    public static final String SENSOR = "sensor_";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SensorMapper sensorMapper;

    @Autowired
    private DataMapper dataMapper;

    @Override
    public void add(Sensor sensor) {
        sensorMapper.add(sensor);
    }

    @Override
    public Sensor findById(int id) {
        Sensor sensor = (Sensor) redisTemplate.opsForValue().get(SENSOR + id);//先从缓存查询
        if (sensor == null) {
            sensor = sensorMapper.findById(id);
            redisTemplate.opsForValue().set(SENSOR + id, sensor);
        }
        return sensor;
    }

    @Override
    public List<Sensor> findAll() {
        return sensorMapper.findAll();
    }

    @Override
    public void update(Sensor sensor) {
        redisTemplate.delete(SENSOR + sensor.getId());//清除对应缓存
        sensorMapper.update(sensor);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        //先删除传感器下的所有数据
        Sensor sensor = sensorMapper.findById(id);
        dataMapper.deleteBySensorId(sensor.getId());
        //再删除传感器
        redisTemplate.delete(SENSOR + sensor.getId());//清除对应缓存
        sensorMapper.deleteById(id);
    }

    @Override
    public List<Sensor> findByClassifyId(int classify_id) {
        return sensorMapper.findByClassifyId(classify_id);
    }

    @Override
    public List<Sensor> findByGatewayId(int gateway_id) {
        return sensorMapper.findByGatewayId(gateway_id);
    }

    @Override
    public List<Sensor> findByGatewayIdAndClassifyId(Integer gateway_id,Integer classify_id) {
        return sensorMapper.findByGatewayIdAndClassifyId(gateway_id,classify_id);
    }

    @Override
    public Sensor findByIdWithDatas(int id) {
        return sensorMapper.findByIdWithDatas(id);
    }
}
