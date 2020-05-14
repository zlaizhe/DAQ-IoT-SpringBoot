package com.my.iot.log;

import com.my.iot.BaseTest;
import com.my.iot.IoTSpringBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
public class LoggerTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void test1(){
        logger.debug("调试信息");//这句不会显示
        System.out.println("日志信息");
        logger.warn("警告信息");
    }
}
