package com.my.iot.service;

import com.my.iot.domain.PageBean;
import com.my.iot.domain.SensorException;

import java.util.Date;
import java.util.List;

public interface SensorExceptionService {

    public void add(SensorException sensorException);

    public List<SensorException> findAll();

    public PageBean<SensorException> findByPage(int page);

    public List<SensorException> findByTime(Date date1, Date date2);
}
