package com.my.iot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

//传感器的数据
public class Data implements Serializable {
    private Integer id;//主键
    private Float data;//数据值
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;//数据采集时间
    private Integer sensor_id;//外键，关联传感器表的主键

    //一（多）对一关联传感器
    private Sensor sensor;

    public Data() {
    }

    public Data(Integer id, Float data, Date time, Integer sensor_id) {
        this.id = id;
        this.data = data;
        this.time = time;
        this.sensor_id = sensor_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getData() {
        return data;
    }

    public void setData(Float data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(Integer sensor_id) {
        this.sensor_id = sensor_id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", data=" + data +
                ", time=" + time +
                ", sensor_id=" + sensor_id +
                '}';
    }
}
