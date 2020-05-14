package com.my.iot.service;

import com.my.iot.domain.Gateway;

import java.util.List;

public interface GatewayService {
    public void add(Gateway gateway);

    public Gateway findById(int id);

    public void update(Gateway gateway);

    public void deleteById(int id);

    public List<Gateway> findAll(Boolean withSensors);

    public Gateway findByIdWithSensors(int id);
}
