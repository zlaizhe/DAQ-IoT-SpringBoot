package com.my.iot.service.impl;

import com.my.iot.domain.GatewayException;
import com.my.iot.domain.PageBean;
import com.my.iot.mapper.GatewayExceptionMapper;
import com.my.iot.service.GatewayExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("gatewayExceptionServiceImpl")
public class GatewayExceptionServiceImpl implements GatewayExceptionService {

    @Autowired
    private GatewayExceptionMapper gatewayExceptionMapper;

    @Override
    public void add(GatewayException gatewayException) {
        gatewayExceptionMapper.add(gatewayException);
    }

    @Override
    public List<GatewayException> findAll() {
        return gatewayExceptionMapper.findAll();
    }

    @Override
    public PageBean<GatewayException> findByPage(int page) {
        int count = gatewayExceptionMapper.findAllCount();
        int pageSize = 2;//默认每页5个
        int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;//计算总页数
        page = Math.max(1, page);//限制页数
        page = Math.min(page, pageCount);//限制页数
        PageBean<GatewayException> pageBean = new PageBean<>(count, pageCount, page, pageSize, null);//封装pageBean

        //调用持久层分页查询
        int startIndex = (page - 1) * pageSize;//计算开始索引
        pageBean.setStartIndex(startIndex);
        List<GatewayException> list = gatewayExceptionMapper.findByPage(pageBean);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public List<GatewayException> findByTime(Date date1, Date date2) {
        HashMap<String, Date> hashMap = new HashMap<>();
        hashMap.put("from", date1);
        hashMap.put("to",date2);
        return gatewayExceptionMapper.findByTime(hashMap);
    }
}
