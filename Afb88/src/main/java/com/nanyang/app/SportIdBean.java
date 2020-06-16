package com.nanyang.app;

import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/3/14.
 */

public class SportIdBean implements Serializable {
    private Class<? extends BaseToolbarActivity> cls;
    private int textColor;

    public String getKey() {
        if (StringUtils.isNull(key))
            return dbid;
        return key;
    }

    private String key;

    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    private String dbid;

    public BaseSportFragment getBaseFragment() {
        return baseFragment;
    }

    public void setBaseFragment(BaseSportFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    private BaseSportFragment baseFragment;

    public Class<? extends BaseToolbarActivity> getCls() {
        return cls;
    }

    public void setCls(Class<BaseToolbarActivity> cls) {
        this.cls = cls;
    }


    String id;

    public String getImgUrl() {
        return imgUrl;
    }

    String imgUrl = "";
    int textRes;
    int sportCount;
    int sportPic;

    public int getSportPic() {
        return sportPic;
    }

    public void setSportPic(int sportPic) {
        this.sportPic = sportPic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    public int getTextColor() {
        return textColor;
    }

    public SportIdBean(String g, String dbid, int textRes, String type, Class<? extends BaseToolbarActivity> cls, BaseSportFragment baseFragment, int textColor, String imgUrl) {
        this.dbid = dbid;
        this.id = g;
        this.textRes = textRes;
        this.type = type;
        this.cls = cls;
        this.baseFragment = baseFragment;
        this.textColor = textColor;
        this.imgUrl = imgUrl;
    }

    public SportIdBean(String g, String dbid, int textRes, String type, Class<? extends BaseToolbarActivity> cls, BaseSportFragment baseFragment, int textColor, int sportPic) {
        this.dbid = dbid;
        this.id = g;
        this.textRes = textRes;
        this.type = type;
        this.cls = cls;
        this.baseFragment = baseFragment;
        this.textColor = textColor;
        this.sportPic = sportPic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTextRes() {
        return textRes;
    }

    public void setTextRes(int textRes) {
        this.textRes = textRes;
    }

    public int getSportCount() {
        return sportCount;
    }

    public void setSportCount(int sportCount) {
        this.sportCount = sportCount;
    }

    @Override
    public String toString() {
        return "SportIdBean{" +
                "textColor=" + textColor +
                ", dbid='" + dbid + '\'' +
                ", baseFragment=" + baseFragment +
                ", cls=" + cls +
                ", id='" + id + '\'' +
                ", textRes=" + textRes +
                ", sportCount=" + sportCount +
                ", sportPic=" + sportPic +
                ", type='" + type + '\'' +
                '}';
    }

    public void setKey(String key) {
        this.key = key;
    }
}
