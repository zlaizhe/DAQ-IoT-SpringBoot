package com.my.iot.mapper;

import com.my.iot.domain.Sensor;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SensorMapper {
    @Insert("insert into tb_sensor values(null, #{description}, #{location}, #{factory}, #{install_time}, #{produce_date}, #{maintenance_time}, #{gate_id}, #{classify_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//返回自增主键
    public void add(Sensor sensor);

    @Select("select * from tb_sensor where id = #{value}")
    public Sensor findById(int id);

    @Select("select * from tb_sensor")
    public List<Sensor> findAll();

    @Update("update tb_sensor set description = #{description}, location = #{location}, " +
            "factory = #{factory}, install_time = #{install_time}, produce_date = #{produce_date}, " +
            "maintenance_time = #{maintenance_time}, gate_id = #{gate_id}, classify_id = #{classify_id} " +
            "where id = #{id}")
    public void update(Sensor sensor);

    @Delete("delete from tb_sensor where id = #{value}")
    public void deleteById(int id);

    @Select("select * from tb_sensor where classify_id = #{value}")
    public List<Sensor> findByClassifyId(int classify_id);

    @Select("select * from tb_sensor where gate_id = #{value}")
    public List<Sensor> findByGatewayId(int gateway_id);

    @Select("select * from tb_sensor where id = #{value}")
    @Results(id = "resultMap3", value = {
            @Result(property = "id", column = "id", id = true),//主键 //固有属性property与column相同可以自动封装
            @Result(property = "datas", column = "id", many = @Many(select = "com.my.iot.mapper.DataMapper.findBySensorId", fetchType = FetchType.DEFAULT))//一对多封装datas属性
    })
    public Sensor findByIdWithDatas(int id);
}
