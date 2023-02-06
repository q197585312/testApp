package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/12.
 */

public class Afb33QrCodeBean {

    /**
     * msg : 1
     * data : [{"id_mod_set_banner":"3126","web_id":"54","type":"1","create_time":"2017-12-18 09:57:01","update_date":"2017-12-18 09:57:01","status":"0","url":"https://image.ibb.co/hbjccc/1799_x_733_3.jpg","lang":"cn","remark":"爱发宝","content":"","iPos":"1"},{"id_mod_set_banner":"4746","web_id":"54","type":"21","create_time":"2018-03-07 10:03:34","update_date":"2018-03-07 10:03:34","status":"0","url":"https://image.ibb.co/bumJJS/1.jpg","lang":"cn","remark":"wechat","content":"","iPos":"1"},{"id_mod_set_banner":"4748","web_id":"54","type":"22","create_time":"2018-03-07 10:11:25","update_date":"2018-03-07 10:11:25","status":"0","url":"https://image.ibb.co/bumJJS/1.jpg","lang":"cn","remark":"wechat","content":"","iPos":"1"},{"id_mod_set_banner":"3123","web_id":"54","type":"1","create_time":"2017-12-18 09:31:14","update_date":"2018-03-05 15:12:54","status":"0","url":"https://image.ibb.co/eSuVTS/1799_x_733.jpg","lang":"cn","remark":"","content":"","iPos":"2"},{"id_mod_set_banner":"4747","web_id":"54","type":"21","create_time":"2018-03-07 10:04:04","update_date":"2018-03-07 10:04:04","status":"0","url":"https://preview.ibb.co/bM6eQ7/deposit2.jpg","lang":"cn","remark":"alipay","content":"","iPos":"2"},{"id_mod_set_banner":"4749","web_id":"54","type":"22","create_time":"2018-03-07 10:11:51","update_date":"2018-03-07 10:11:51","status":"0","url":"https://preview.ibb.co/bM6eQ7/deposit2.jpg","lang":"cn","remark":"alipay","content":"","iPos":"2"},{"id_mod_set_banner":"3161","web_id":"54","type":"1","create_time":"2017-12-21 00:21:28","update_date":"2018-03-05 15:27:01","status":"0","url":"https://image.ibb.co/cyNaTS/1799_x_733_1.jpg","lang":"cn","remark":"爱发宝","content":"","iPos":"3"},{"id_mod_set_banner":"4732","web_id":"54","type":"1","create_time":"2018-03-06 19:01:58","update_date":"2018-03-06 19:01:58","status":"0","url":"https://image.ibb.co/jRL9TS/1799_x_733_8.jpg","lang":"cn","remark":"","content":"","iPos":"3"},{"id_mod_set_banner":"3124","web_id":"54","type":"1","create_time":"2017-12-18 09:40:42","update_date":"2017-12-18 09:40:42","status":"0","url":"https://image.ibb.co/bFD8Fx/1799_x_733_1.jpg","lang":"cn","remark":"              ","content":"","iPos":"4"},{"id_mod_set_banner":"3128","web_id":"54","type":"1","create_time":"2017-12-18 10:14:29","update_date":"2018-02-26 14:12:30","status":"0","url":"https://image.ibb.co/hGeRqx/1799_x_733.jpg","lang":"cn","remark":"","content":"","iPos":"4"},{"id_mod_set_banner":"3127","web_id":"54","type":"1","create_time":"2017-12-18 10:01:15","update_date":"2017-12-31 12:42:55","status":"0","url":"https://image.ibb.co/h8chkc/Untitled_4_01.jpg","lang":"cn","remark":"爱发宝","content":"","iPos":"5"},{"id_mod_set_banner":"3154","web_id":"54","type":"1","create_time":"2017-12-20 16:58:19","update_date":"2018-01-18 15:17:03","status":"0","url":"https://image.ibb.co/e0bbE6/23_soda_afb33.png","lang":"cn","remark":"爱发宝","content":"","iPos":"7"}]
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
         * id_mod_set_banner : 3126
         * web_id : 54
         * type : 1
         * create_time : 2017-12-18 09:57:01
         * update_date : 2017-12-18 09:57:01
         * status : 0
         * url : https://image.ibb.co/hbjccc/1799_x_733_3.jpg
         * lang : cn
         * remark : 爱发宝
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
