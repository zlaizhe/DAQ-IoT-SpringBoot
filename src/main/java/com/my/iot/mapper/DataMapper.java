package com.my.iot.mapper;

import com.my.iot.domain.Data;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface DataMapper {

    @Insert("insert into tb_data values(null, #{data}, #{time}, #{sensor_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//返回自增主键
    public void add(Data data);

    @Select("select * from tb_data")
    public List<Data> findAll();

    @Select("select * from tb_data where sensor_id = #{value}")
    public List<Data> findBySensorId(int sensor_id);

    @Select("delete from tb_data where sensor_id = #{value}")
    public void deleteBySensorId(int sensor_id);

    @Select("select * from tb_data where sensor_id = #{sensor_id} and time between #{datetime1} and #{datetime2}")
    List<Data> findBySensorIdInDatetime(@Param("sensor_id") Integer sensor_id, Date datetime1, Date datetime2);
}
