package com.my.iot.service.impl;

import com.my.iot.domain.Data;
import com.my.iot.mapper.DataMapper;
import com.my.iot.schedule.AsyncTaskService;
import com.my.iot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("dataService")
public class DataServiceImpl implements DataService {

    public static final String DATA_BUFFER = "data_buffer";

    @Resource
    private RedisTemplate<String, Data> redisTemplate;

    @Autowired
    private DataMapper dataMapper;

    @Override
    public void add(Data data) {
        //将数据存入缓存(程序一开始就会启动异步任务，不断从缓存队列取出元素存入数据库)
        redisTemplate.opsForList().rightPush(DATA_BUFFER, data);
    }

    @Override
    public List<Data> findAll() {
        return dataMapper.findAll();
    }

    @Override
    public List<Data> findBySensorId(int sensor_id) {
        return dataMapper.findBySensorId(sensor_id);
    }

    @Override
    public List<Data> findBySensorIdInDatetime(Integer sensor_id, Date datetime1, Date datetime2) {
        return dataMapper.findBySensorIdInDatetime(sensor_id, datetime1, datetime2);
    }

    @Override
    public void add(List<Data> datas) {
        //将数据存入缓存(程序一开始就会启动异步任务，不断从缓存队列取出元素存入数据库)
        redisTemplate.opsForList().rightPushAll(DATA_BUFFER, datas);
    }
}
