package com.my.iot.service;

import com.my.iot.domain.GatewayException;
import com.my.iot.domain.PageBean;

import java.util.Date;
import java.util.List;

public interface GatewayExceptionService {
    public void add(GatewayException gatewayException);

    public List<GatewayException> findAll();

    public PageBean<GatewayException> findByPage(int page);

    public List<GatewayException> findByTime(Date date1, Date date2);
}
