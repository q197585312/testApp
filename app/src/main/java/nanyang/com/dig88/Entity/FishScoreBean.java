package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class FishScoreBean {

    private List<DicDataBean> dicData;

    public List<DicDataBean> getDicData() {
        return dicData;
    }

    public void setDicData(List<DicDataBean> dicData) {
        this.dicData = dicData;
    }

    public static class DicDataBean {
        /**
         * Score : 0
         */

        private String Score;

        public String getScore() {
            return Score;
        }

        public void setScore(String Score) {
            this.Score = Score;
        }
    }
}
