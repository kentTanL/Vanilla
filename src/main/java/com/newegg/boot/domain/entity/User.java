package com.newegg.boot.domain.entity;

import com.google.gson.Gson;

public class User {
    private Long id;
    private String name;
    private Integer age;

    public User() {
    }
    
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(Long id, String name, Integer age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String toJsons() {
        Gson g = new Gson();
        return String.format("[%s]", g.toJson(this));
    }
    
    public String toJson() {
        Gson g = new Gson();
        return String.format("%s", g.toJson(this));
    }
    
}
