package nanyang.com.dig88.Entity;


import java.util.List;

/**
 * Created by Administrator on 2019/11/18.
 */

public class JdbFishBean {


    /**
     * code : 1
     * msg : 1
     * data : [{"id_mod_game_type_slots":"7001","status":"0","image":"7001.jpg","gname_en":"Dragon Fishing","gname_cn":"龙王捕鱼","gametype":"1","iPos":"0"},{"id_mod_game_type_slots":"7002","status":"0","image":"7002.jpg","gname_en":"Dragon Fishing II","gname_cn":"龙王捕鱼2","gametype":"1","iPos":"2"},{"id_mod_game_type_slots":"7003","status":"0","image":"7003.jpg","gname_en":"Cai Shen Fishing","gname_cn":"财神捕鱼","gametype":"1","iPos":"3"},{"id_mod_game_type_slots":"7004","status":"0","image":"7004.jpg","gname_en":"Five Dragons Fishing","gname_cn":"五龙捕魚","gametype":"1","iPos":"4"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id_mod_game_type_slots : 7001
         * status : 0
         * image : 7001.jpg
         * gname_en : Dragon Fishing
         * gname_cn : 龙王捕鱼
         * gametype : 1
         * iPos : 0
         */

        private String gid;
        private String status;
        private String image;
        private String gname_en;
        private String gname_cn;
        private String gametype;
        private String iPos;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGname_en() {
            return gname_en;
        }

        public void setGname_en(String gname_en) {
            this.gname_en = gname_en;
        }

        public String getGname_cn() {
            return gname_cn;
        }

        public void setGname_cn(String gname_cn) {
            this.gname_cn = gname_cn;
        }

        public String getGametype() {
            return gametype;
        }

        public void setGametype(String gametype) {
            this.gametype = gametype;
        }

        public String getIPos() {
            return iPos;
        }

        public void setIPos(String iPos) {
            this.iPos = iPos;
        }
    }
}
