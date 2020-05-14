package com.my.iot.domain;

import java.io.Serializable;
import java.util.List;

//传感器分类
public class SensorClassify implements Serializable {

    private Integer id;//主键
    private String name;//分类名称

    //该分类下关联的多个传感器（一对多）
    private List<Sensor> sensors;

    public SensorClassify() {
    }

    public SensorClassify(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public String toString() {
        return "SensorClassify{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
