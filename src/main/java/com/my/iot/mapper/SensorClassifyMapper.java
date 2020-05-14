package com.my.iot.mapper;

import com.my.iot.domain.SensorClassify;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SensorClassifyMapper {
    @Insert("insert into tb_sensor_classify values(null, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//返回自增主键
    public void add(SensorClassify classify);

    @Select("select * from tb_sensor_classify")
    public List<SensorClassify> findAll();

    @Select("select * from tb_sensor_classify where id = #{value}")
    public SensorClassify findById(int id);

    @Select("select t1.id cid, t1.name cname " +
            "from tb_sensor_classify t1, tb_sensor t2, tb_gateway t3 " +
            "where t2.gate_id = #{value} and t1.id = t2.classify_id and t3.id = t2.gate_id " +
            "group by t1.id;")
    @Results(id = "resultMap1",value = {
            @Result(id = true,column = "cid",property = "id"),
            @Result(column = "cname",property = "name")
    })
    public List<SensorClassify> findByGatewayId(int gate_id);//使用传感器表作为中间表多表查询（多对多）
}
