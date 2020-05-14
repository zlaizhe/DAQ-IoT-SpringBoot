package com.my.iot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Sensor implements Serializable {
    private Integer id;//主键
    private String description;
    private String location;
    private String factory;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date install_time;//安装日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date produce_date;//生产日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date maintenance_time;//维修日期

    private Integer gate_id;//外键，关联网关主键
    private Integer classify_id;//外键，关联分类主键

    //以下为关联属性
    private Gateway gateway;//关联的网关 一对一
    private SensorClassify sensorClassify;//关联的分类 一对一
    private List<Data> datas;//关联的传感器数据 一对多

    public Sensor() {
    }

    public Sensor(Integer id, String description, String location, String factory, Date install_time, Date produce_date, Date maintenance_time, Integer gate_id, Integer classify_id) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.factory = factory;
        this.install_time = install_time;
        this.produce_date = produce_date;
        this.maintenance_time = maintenance_time;
        this.gate_id = gate_id;
        this.classify_id = classify_id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Date getInstall_time() {
        return install_time;
    }

    public void setInstall_time(Date install_time) {
        this.install_time = install_time;
    }

    public Date getProduce_date() {
        return produce_date;
    }

    public void setProduce_date(Date produce_date) {
        this.produce_date = produce_date;
    }

    public Date getMaintenance_time() {
        return maintenance_time;
    }

    public void setMaintenance_time(Date maintenance_time) {
        this.maintenance_time = maintenance_time;
    }

    public Integer getGate_id() {
        return gate_id;
    }

    public void setGate_id(Integer gate_id) {
        this.gate_id = gate_id;
    }

    public Integer getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(Integer classify_id) {
        this.classify_id = classify_id;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public SensorClassify getSensorClassify() {
        return sensorClassify;
    }

    public void setSensorClassify(SensorClassify sensorClassify) {
        this.sensorClassify = sensorClassify;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", factory='" + factory + '\'' +
                ", install_time=" + install_time +
                ", produce_date=" + produce_date +
                ", maintenance_time=" + maintenance_time +
                ", gate_id=" + gate_id +
                ", classify_id=" + classify_id +
                '}';
    }
}
