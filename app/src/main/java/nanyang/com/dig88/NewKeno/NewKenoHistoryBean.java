package nanyang.com.dig88.NewKeno;

import java.util.List;

/**
 * Created by Administrator on 2018/9/13.
 */

public class NewKenoHistoryBean {

    /**
     * code : 1
     * msg : 1
     * data : {"over_under":["1","0","1","1","0","1","1","1","1","0","0","0","0","0","0","1","1","0","1","0"],"odd_even":["1","0","0","1","0","0","0","0","1","0","0","0","0","1","0","1","0","0","0","1"],"parlay":["1","4","2","1","4","2","2","2","1","4","4","4","4","3","4","1","2","4","2","3"],"range":["4","1","3","4","1","5","3","5","3","2","2","3","3","3","2","3","4","1","4","2"],"smallest":["3-5","1-2","3-5","3-5","3-5","1-2","3-5",">=6","1-2","3-5","3-5","1-2","3-5","3-5","3-5","1-2",">=6","1-2","3-5","3-5"],"biggest":["76-78","79-80","76-78","<=75","<=75","79-80","<=75","79-80","<=75","79-80","<=75","76-78","<=75","76-78","<=75","76-78","79-80","<=75","76-78","76-78"]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> over_under;
        private List<String> odd_even;
        private List<String> parlay;
        private List<String> range;
        private List<String> smallest;
        private List<String> biggest;

        public List<String> getOver_under() {
            return over_under;
        }

        public void setOver_under(List<String> over_under) {
            this.over_under = over_under;
        }

        public List<String> getOdd_even() {
            return odd_even;
        }

        public void setOdd_even(List<String> odd_even) {
            this.odd_even = odd_even;
        }

        public List<String> getParlay() {
            return parlay;
        }

        public void setParlay(List<String> parlay) {
            this.parlay = parlay;
        }

        public List<String> getRange() {
            return range;
        }

        public void setRange(List<String> range) {
            this.range = range;
        }

        public List<String> getSmallest() {
            return smallest;
        }

        public void setSmallest(List<String> smallest) {
            this.smallest = smallest;
        }

        public List<String> getBiggest() {
            return biggest;
        }

        public void setBiggest(List<String> biggest) {
            this.biggest = biggest;
        }
    }
}
