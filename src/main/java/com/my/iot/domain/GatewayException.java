package com.my.iot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class GatewayException implements Serializable {//网关异常
    private Integer id;//主键
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private Integer gate_id; //关联网关主键

    private Gateway gateway;//一对一关联网关对象

    public GatewayException() {
    }

    public GatewayException(Integer id, String description, Date time, Integer gate_id) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.gate_id = gate_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getGate_id() {
        return gate_id;
    }

    public void setGate_id(Integer gate_id) {
        this.gate_id = gate_id;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public String toString() {
        return "GatewayException{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", gate_id=" + gate_id +
                '}';
    }
}
