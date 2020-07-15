package com.my.iot.mapper;

import com.my.iot.domain.GatewayException;
import com.my.iot.domain.PageBean;
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
public interface GatewayExceptionMapper {

    @Insert("insert into tb_gateway_exception values(null, #{description}, #{time}, #{gate_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//返回自增主键
    public void add(GatewayException gatewayException);

    @Select("select * from tb_gateway_exception")
    public List<GatewayException> findAll();

    @Select("select count(id) from tb_gateway_exception")
    public int findAllCount();

    @Select("select * from tb_gateway_exception limit #{startIndex}, #{pageSize}")
    public List<GatewayException> findByPage(PageBean<GatewayException> pageBean);

    @Select("select * from tb_gateway_exception where time between #{from} and #{to}")
    public List<GatewayException> findByTime(HashMap<String, Date> conditions);
}
