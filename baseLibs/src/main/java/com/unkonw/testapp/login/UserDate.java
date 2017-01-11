package com.unkonw.testapp.login;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class UserDate {
    public String name;
    public String phone;

    public UserDate() {
        this.name = "name1";
        this.phone = "phone1";
    }

    @Override
    public String toString() {
        return "UserDate{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
