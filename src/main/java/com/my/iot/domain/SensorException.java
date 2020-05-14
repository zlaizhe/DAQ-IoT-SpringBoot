package com.my.iot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class SensorException implements Serializable {
    private Integer id;//主键
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private Integer sensor_id; //关联传感器主键

    private Sensor sensor;//关联传感器对象

    public SensorException() {
    }

    public SensorException(Integer id, String description, Date time, Integer sensor_id) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.sensor_id = sensor_id;
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
        return "SensorException{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", sensor_id=" + sensor_id +
                '}';
    }
}
