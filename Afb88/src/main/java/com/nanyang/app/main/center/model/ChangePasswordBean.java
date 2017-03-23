package com.nanyang.app.main.center.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ChangePasswordBean {
    private Map<String, String> map;
    String __EVENTTARGET = "btnSave";
    String __VIEWSTATE = "/wEPDwULLTE0NDg5NDU0NjIPZBYCAgEPZBYMZg8PFgIeBFRleHQFC2RlbW9hZmJhaTEwZGQCAg8PFgIeDEVycm9yTWVzc2FnZQUY5LiN5YWB6K6456m655m95a+G56CB44CCZGQCBA8PFgIfAQUY5LiN5YWB6K6456m655m95a+G56CB44CCZGQCBg8PFgIfAQUY5LiN5YWB6K6456m655m95a+G56CB44CCZGQCBw8PFgIfAQUk56Gu5a6a5a+G56CB5LiO5paw5a+G56CB5LiN55u45ZCM44CCZGQCCA8PFgIfAAUG5YKo5a2YZGRkUeLz7QXREd+Pdk8nB9dDAVKt9RBFC0WI13lr7yL39xU=";
    String __VIEWSTATEGENERATOR = "582CF378";
    String __EVENTVALIDATION = "/wEdAAV+YDjDmt0R/7xhIHizwunwepRWkK7F0cJ07W8SmllpC0J0yJWcqvi8e69cDXV7JisbWspWvJra0RVqSz40I/V2lliAPKY+KvRmniYVjNuQiDs+3VAstq+rPHPeTIOKmvuQXGuZxBsT0tB2nS5uWPjF";
    String txtOldPassword;
    String txtNewPassword;
    String txtConfirmPassword;

    public ChangePasswordBean(String txtOldPassword, String txtNewPassword, String txtConfirmPassword) {
        this.txtOldPassword = txtOldPassword;
        this.txtNewPassword = txtNewPassword;
        this.txtConfirmPassword = txtConfirmPassword;
    }

    public Map<String, String> getMap() {
        map = new HashMap<>();
        map.put("__EVENTTARGET", __EVENTTARGET);
        map.put("__VIEWSTATE", __VIEWSTATE);
        map.put("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR);
        map.put("__EVENTVALIDATION", __EVENTVALIDATION);
        map.put("txtOldPassword", txtOldPassword);
        map.put("txtNewPassword", txtNewPassword);
        map.put("txtConfirmPassword", txtConfirmPassword);
        return map;
    }
}
