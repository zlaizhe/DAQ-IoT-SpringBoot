package com.my.iot.domain;

import java.io.Serializable;
import java.util.List;

public class Gateway implements Serializable {
    //固有属性
    private Integer id;//主键
    private String ip;
    private String port;
    private String description;
    private String location;

    //关联属性
    private List<Sensor> sensors;//一对多关联的传感器

    public Gateway() {

    }

    public Gateway(Integer id, String ip, String port, String description, String location) {
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.description = description;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public String toString() {
        return "Gateway{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
