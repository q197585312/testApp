package nanyang.com.dig88.Table.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/10.
 */
public class BallGameInfoBean implements Serializable{
    private boolean isBallGameLogin=false;
    private int RId=0;
    public boolean isBallGameLogin() {
        return isBallGameLogin;
    }

    public void setIsBallGameLogin(boolean isBallGameLogin) {
        this.isBallGameLogin = isBallGameLogin;
    }

    public int getRId() {
        return RId;
    }

    public void setRId(int RId) {
        this.RId = RId;
    }
}
