package com.my.iot.schedule;

import com.my.iot.domain.Data;
import com.my.iot.mapper.DataMapper;
import com.my.iot.service.impl.DataServiceImpl;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//异步任务服务，用于将Redis的Data缓存队列添加到数据库
@Component
public class AsyncTaskService implements InitializingBean, DisposableBean {

    @Autowired
    private DataMapper dataMapper;

    @Resource
    private RedisTemplate<String, Data> redisTemplate;

    private ExecutorService executorService;

    @Override
    public void afterPropertiesSet() {//Bean的初始化方法
        System.out.println("线程池初始化...");
        executorService = Executors.newSingleThreadExecutor();
        start(DataServiceImpl.DATA_BUFFER);
    }

    @Override
    public void destroy() {
        System.out.println("线程池初始化...");
        shutdown();
    }

    private volatile boolean run = false;

    public synchronized void start(String key) {//启动线程池，启动异步任务
        run = true;
        executorService.submit(() -> {
            while (run) {
                Data data = redisTemplate.opsForList().leftPop(key, 10, TimeUnit.SECONDS);//阻塞式地不断从缓存队列里拿出数据
                if (data != null) {
                    //System.out.println("向数据库保存数据" + data);
                    dataMapper.add(data);//存入数据库
                }
            }
        });
    }

    public synchronized void shutdown() {//关闭线程池
        run = false;
        executorService.shutdown();
    }


}
