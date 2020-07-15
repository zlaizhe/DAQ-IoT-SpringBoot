package com.my.iot.service;

import com.my.iot.domain.Sensor;

import java.util.List;

public interface SensorService {

    public void add(Sensor sensor);

    public Sensor findById(int id);

    public List<Sensor> findAll();

    public void update(Sensor sensor);

    public void deleteById(int id);

    public List<Sensor> findByClassifyId(int classify_id);

    public List<Sensor> findByGatewayId(int gateway_id);

    public List<Sensor> findByGatewayIdAndClassifyId(Integer gateway_id,Integer classify_id);

    public Sensor findByIdWithDatas(int id);
}
