package com.my.iot.domain;
//统一的json响应信息
public class Result {
    private Boolean status;//布尔量，表示本次响应是否成功
    private String message;//本次响应的基本描述信息
    private Object data;//本次响应的数据，可以是任何类型数据，如果没有数据，为null

    public Result() {
    }

    public Result(Boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
