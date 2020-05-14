package com.my.iot.mapper;

import com.my.iot.domain.GatewayException;
import com.my.iot.domain.PageBean;
import com.my.iot.domain.SensorException;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface SensorExceptionMapper {

    @Insert("insert into tb_sensor_exception values(null, #{description}, #{time}, #{sensor_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//返回自增主键
    public void add(SensorException sensorException);

    @Select("select * from tb_sensor_exception")
    public List<SensorException> findAll();

    @Select("select count(id) from tb_sensor_exception")
    public int findAllCount();

    @Select("select * from tb_sensor_exception limit #{startIndex}, #{pageSize}")
    public List<SensorException> findByPage(PageBean<SensorException> pageBean);

    @Select("select * from tb_sensor_exception where time >= #{from} and time <= #{to}")
    public List<SensorException> findByTime(HashMap<String, Date> conditions);
}
