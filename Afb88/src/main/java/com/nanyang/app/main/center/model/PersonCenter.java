package com.nanyang.app.main.center.model;

/**
 * Created by 47184 on 2019/3/18.
 */

public class PersonCenter {
    private String name;
    private String value;

    public PersonCenter() {
    }

    public PersonCenter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
