package nanyang.com.dig88.Lottery4D.Bean;

/**
 * Created by Administrator on 2019/2/28.
 */

public class PoolBean {
    private String poolId;
    private String poolIdName;

    public PoolBean(String poolId, String poolIdName) {
        this.poolId = poolId;
        this.poolIdName = poolIdName;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public String getPoolIdName() {
        return poolIdName;
    }

    public void setPoolIdName(String poolIdName) {
        this.poolIdName = poolIdName;
    }
}
