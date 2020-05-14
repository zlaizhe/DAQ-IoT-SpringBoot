package com.my.iot.mapper;

import com.my.iot.domain.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
}
