package com.my.iot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义的字符串转日期的转换器
 * SpringMVC需要在springmvc.xml中配置
 * SpringBoot直接加@Configuration注解即可
 */

@Configuration
public class StringToDateConverter implements Converter<String, Date> {
    private static final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date convert(String source) {
        if (source == null) {
            throw new RuntimeException("需要传入数据！");
        }

        try {
            if (source.length() > 10) {
                return DATE_TIME.parse(source);
            } else {
                return DATE.parse(source);
            }
        } catch (ParseException e) {
            throw new RuntimeException("数据类型转换出错！");
        }
    }
}
