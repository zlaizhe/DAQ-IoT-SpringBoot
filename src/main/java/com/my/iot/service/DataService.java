package com.my.iot.service;

import com.my.iot.domain.Data;

import java.util.Date;
import java.util.List;

public interface DataService {
    public void add(List<Data> datas);

    public void add(Data data);

    public List<Data> findAll();

    public List<Data> findBySensorId(int sensor_id);

    public List<Data> findBySensorIdInDatetime(Integer sensor_id, Date datetime1, Date datetime2);
}
