package gaming178.com.casinogame.Activity.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/2.
 */
public class SeatBean implements Serializable{
    private String name;
    private int  status;
    private int serialId;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAreaId() {
        return serialId;
    }

    public void setAreaId(int serialId) {
        this.serialId = serialId;
    }
}
