package gaming178.com.casinogame.entity;

import java.util.List;

public class BannerBean {

    /**
     * result : Success
     * code : 0
     * data : [{"id":"AFBGAMING","path":"https://i.ibb.co/DDd3dfz/AFBGAMING.png"},{"id":"WE1POKER","path":"https://i.ibb.co/xgmJvTP/WE1POKER.png"},{"id":"CASINO GAMES","path":"https://i.ibb.co/6H3BzSk/NEW-GAMES.png"},{"id":"AFB88 SPORT","path":"https://i.ibb.co/86kFYKh/banner-afb88.png"},{"id":"SLOT RAJACASINO88","path":"https://i.ibb.co/XYdrXp1/Slot-Raja-Casino88.png"},{"id":"DEPOSIT 24 JAM ONLINE","path":"https://i.ibb.co/S0xXmsZ/RAJACASINO-DEPOSIT-PULSA-3.png"},{"id":" BONUS ROLLINGAN CASINO","path":"https://image.ibb.co/gLkZbe/Bonus_Rollingan_Casino.jpg"}]
     */

    private String result;
    private String code;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : AFBGAMING
         * path : https://i.ibb.co/DDd3dfz/AFBGAMING.png
         */

        private String id;
        private String path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
