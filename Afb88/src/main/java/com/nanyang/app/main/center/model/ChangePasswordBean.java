package com.nanyang.app.main.center.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ChangePasswordBean {
    private Map<String, String> map;
    String __EVENTTARGET = "btnSave";
    String __VIEWSTATE = "/wEPDwULLTE0NDg5NDU0NjIPZBYCAgEPZBYMZg8PFgIeBFRleHQFBmViMTExMWRkAgIPDxYCHgxFcnJvck1lc3NhZ2UFG1Bhc3N3b3JkIG11c3Qgbm90IGJlIGJsYW5rLmRkAgQPDxYCHwEFG1Bhc3N3b3JkIG11c3Qgbm90IGJlIGJsYW5rLmRkAgYPDxYCHwEFG1Bhc3N3b3JkIG11c3Qgbm90IGJlIGJsYW5rLmRkAgcPDxYCHwEFMUNvbmZpcm0gUGFzc3dvcmQgZG9lcyBub3QgbWF0Y2ggdGhlIG5ldyBwYXNzd29yZC5kZAIIDw8WAh8ABQRTYXZlZGRkV0DTZ/54IeF/eVfJGAPXUZqIyIR6zJNYqtpsMyxb+ns=";
    String __VIEWSTATEGENERATOR = "7C2B7B59";
    String __EVENTVALIDATION = "/wEdAAUvrxdFTDSAuUuowYOd7N/1epRWkK7F0cJ07W8SmllpC0J0yJWcqvi8e69cDXV7JisbWspWvJra0RVqSz40I/V2lliAPKY+KvRmniYVjNuQiHXocLgZgAX8kc8L3kZn5nGbcvDQTd+1DRLwtIUQVNRu";
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
