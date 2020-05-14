package com.my.iot.service;

import com.my.iot.domain.SensorClassify;

import java.util.List;

public interface SensorClassifyService {

    public void add(SensorClassify classify);

    public List<SensorClassify> findAll(Boolean withSensors);

    public SensorClassify findById(int id);

    public List<SensorClassify> findByGatewayId(int gateway_id);
}
