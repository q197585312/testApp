package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class QrCodeBean {

    /**
     * msg : 1
     * data : [{"id_mod_set_banner":"3838","web_id":"8","type":"21","create_time":"2018-01-24 19:09:56","update_date":"2018-01-26 19:56:19","status":"0","url":"https://image.ibb.co/nmq1Ob/depo_qrcode.png","lang":"cn","remark":"wechat","content":"","iPos":"1"}]
     */

    private String msg;
    private List<DataBean> data;

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
         * id_mod_set_banner : 3838
         * web_id : 8
         * type : 21
         * create_time : 2018-01-24 19:09:56
         * update_date : 2018-01-26 19:56:19
         * status : 0
         * url : https://image.ibb.co/nmq1Ob/depo_qrcode.png
         * lang : cn
         * remark : wechat
         * content :
         * iPos : 1
         */

        private String id_mod_set_banner;
        private String web_id;
        private String type;
        private String create_time;
        private String update_date;
        private String status;
        private String url;
        private String lang;
        private String remark;
        private String content;
        private String iPos;

        public String getId_mod_set_banner() {
            return id_mod_set_banner;
        }

        public void setId_mod_set_banner(String id_mod_set_banner) {
            this.id_mod_set_banner = id_mod_set_banner;
        }

        public String getWeb_id() {
            return web_id;
        }

        public void setWeb_id(String web_id) {
            this.web_id = web_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIPos() {
            return iPos;
        }

        public void setIPos(String iPos) {
            this.iPos = iPos;
        }
    }
}
