package com.my.iot.mapper;

import com.my.iot.domain.Gateway;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GatewayMapper {

    @Insert("insert into tb_gateway values(null, #{ip}, #{port}, #{description}, #{location})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//返回自增主键，封装到gateway对象中
    public void insert(Gateway gateway);

    @Select("select * from tb_gateway where id = #{value}")
    public Gateway findById(int id);

    @Update("update tb_gateway set ip = #{ip}, port = #{port}, description = #{description}, location = #{location} where id = #{id}")
    public void update(Gateway gateway);

    @Delete("delete from tb_gateway where id = #{value}")
    public void deleteById(int id);

    @Select("select * from tb_gateway")
    public List<Gateway> findAll();


    @Select("select * from tb_gateway where id = #{value}")
    @Results(id = "resultMap2", value = {
            @Result(property = "id", column = "id", id = true),//主键  //固有属性property与column相同可以自动封装
            @Result(property = "sensors", column = "id", many = @Many(select = "com.my.iot.mapper.SensorMapper.findByGatewayId", fetchType = FetchType.DEFAULT))//一对多封装sensors属性
    })
    public Gateway findByIdWithSensors(int id);
}
