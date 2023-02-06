package nanyang.com.dig88.Entity;

import java.io.Serializable;


public class RecommendGameBean implements Serializable{
    String name;
    String img;
    String time;
    String description;
    String action;
    String gameType;
    int rightDrawableRes;
    private int res;
    private int contentRes;
    private int type;

    public RecommendGameBean(String name, String img, String description,String action) {
        this.name = name;
        this.img = img;
        this.description = description;
        this.action=action;
    }

    public RecommendGameBean(String name, int res, String description,String action) {
        this.name = name;
        this.res = res;
        this.description = description;
        this.action=action;
    }

    public RecommendGameBean(String name, int res, String description,String action,int contentRes,String gameType) {
        this.name = name;
        this.res = res;
        this.description = description;
        this.action=action;
        this.contentRes = contentRes;
        this.gameType = gameType;
    }

    public RecommendGameBean(String name, int res, String description,String action,int rightDrawableRes) {
        this.name = name;
        this.res = res;
        this.description = description;
        this.action=action;
        this.rightDrawableRes=rightDrawableRes;
    }

    public RecommendGameBean(String name, int res, String description,String action,int rightDrawableRes,int type) {
        this.name = name;
        this.res = res;
        this.description = description;
        this.action=action;
        this.rightDrawableRes=rightDrawableRes;
        this.type = type;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getContentRes() {
        return contentRes;
    }

    public void setContentRes(int contentRes) {
        this.contentRes = contentRes;
    }

    public int getRightDrawableRes() {
        return rightDrawableRes;
    }

    public void setRightDrawableRes(int rightDrawableRes) {
        this.rightDrawableRes = rightDrawableRes;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
