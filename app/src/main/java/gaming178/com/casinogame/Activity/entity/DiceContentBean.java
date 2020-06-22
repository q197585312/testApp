package gaming178.com.casinogame.Activity.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/23.
 */
public class DiceContentBean implements Serializable{

    List<DiceBean> list = new ArrayList<DiceBean>();
    int point;
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public List<DiceBean> getList() {
        return list;
    }

}
