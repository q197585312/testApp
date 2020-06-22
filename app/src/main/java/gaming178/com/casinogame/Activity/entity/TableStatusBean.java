package gaming178.com.casinogame.Activity.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/2.
 */
public class TableStatusBean implements Serializable{
    private List<SeatBean> seatBeans = new ArrayList<SeatBean>();
    private int areaId;

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    private String vip;

    public TableStatusBean(int id,String vip) {
        super();
        areaId = id;
        this.vip=vip;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public List<SeatBean> getSeatBeans() {
        return seatBeans;
    }


}
