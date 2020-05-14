package com.my.iot.service.impl;

import com.my.iot.domain.GatewayException;
import com.my.iot.domain.PageBean;
import com.my.iot.domain.SensorException;
import com.my.iot.mapper.SensorExceptionMapper;
import com.my.iot.service.SensorExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("sensorExceptionService")
public class SensorExceptionServiceImpl implements SensorExceptionService {

    @Autowired
    private SensorExceptionMapper sensorExceptionMapper;

    @Override
    public void add(SensorException sensorException) {
        sensorExceptionMapper.add(sensorException);
    }

    @Override
    public List<SensorException> findAll() {
        return sensorExceptionMapper.findAll();
    }

    @Override
    public PageBean<SensorException> findByPage(int page) {
        int count = sensorExceptionMapper.findAllCount();
        int pageSize = 2;//默认每页5个
        int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;//计算总页数
        page = Math.max(1, page);//限制页数
        page = Math.min(page, pageCount);//限制页数
        PageBean<SensorException> pageBean = new PageBean<>(count, pageCount, page, pageSize, null);//封装pageBean

        //调用持久层分页查询
        int startIndex = (page - 1) * pageSize;//计算开始索引
        pageBean.setStartIndex(startIndex);
        List<SensorException> list = sensorExceptionMapper.findByPage(pageBean);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public List<SensorException> findByTime(Date date1, Date date2) {
        HashMap<String, Date> hashMap = new HashMap<>();
        hashMap.put("from", date1);
        hashMap.put("to",date2);
        return sensorExceptionMapper.findByTime(hashMap);
    }
}
