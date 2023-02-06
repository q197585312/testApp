package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class FishScoreTransferBean {

    private List<DicDataBean> dicData;

    public List<DicDataBean> getDicData() {
        return dicData;
    }

    public void setDicData(List<DicDataBean> dicData) {
        this.dicData = dicData;
    }

    public static class DicDataBean {
        /**
         * MessageId : 0
         */

        private int MessageId;

        public int getMessageId() {
            return MessageId;
        }

        public void setMessageId(int MessageId) {
            this.MessageId = MessageId;
        }
    }
}
