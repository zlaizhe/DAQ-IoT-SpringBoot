package com.my.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String username;
    private String nickname;

    @JsonIgnore
    private String password;//响应json时不包含密码
    private String tel;
    private String email;

    public User() {
    }

    public User(Integer id, String username, String nickname, String password, String tel, String email) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
