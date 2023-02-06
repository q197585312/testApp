package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class BannerBean {

    /**
     * msg : 1
     * data : [{"id_mod_set_banner":"4746","web_id":"54","type":"21","create_time":"2018-03-07 10:03:34","update_date":"2018-03-07 10:03:34","status":"0","url":"https://image.ibb.co/bumJJS/1.jpg","lang":"cn","remark":"wechat","content":"","iPos":"1"},{"id_mod_set_banner":"4748","web_id":"54","type":"22","create_time":"2018-03-07 10:11:25","update_date":"2018-03-07 10:11:25","status":"0","url":"https://image.ibb.co/bumJJS/1.jpg","lang":"cn","remark":"wechat","content":"","iPos":"1"},{"id_mod_set_banner":"5307","web_id":"54","type":"1","create_time":"2018-04-06 19:31:45","update_date":"2018-04-06 19:31:45","status":"0","url":"https://image.ibb.co/kxC6Rc/1799_x_733.png","lang":"cn","remark":"","content":"","iPos":"1"},{"id_mod_set_banner":"5498","web_id":"54","type":"1","create_time":"2018-04-10 16:27:01","update_date":"2018-04-10 16:27:01","status":"0","url":"https://image.ibb.co/deegcH/600_x_600_5.jpg","lang":"cn","remark":"popup","content":"","iPos":"1"},{"id_mod_set_banner":"4747","web_id":"54","type":"21","create_time":"2018-03-07 10:04:04","update_date":"2018-03-07 10:04:04","status":"0","url":"https://preview.ibb.co/bM6eQ7/deposit2.jpg","lang":"cn","remark":"alipay","content":"","iPos":"2"},{"id_mod_set_banner":"4749","web_id":"54","type":"22","create_time":"2018-03-07 10:11:51","update_date":"2018-03-07 10:11:51","status":"0","url":"https://preview.ibb.co/bM6eQ7/deposit2.jpg","lang":"cn","remark":"alipay","content":"","iPos":"2"},{"id_mod_set_banner":"5308","web_id":"54","type":"1","create_time":"2018-04-06 19:32:44","update_date":"2018-04-09 15:29:36","status":"0","url":"https://image.ibb.co/m5ahzx/1799_x_733_1.png","lang":"cn","remark":"","content":"","iPos":"2"},{"id_mod_set_banner":"5309","web_id":"54","type":"1","create_time":"2018-04-06 19:33:27","update_date":"2018-04-09 15:32:13","status":"0","url":"https://image.ibb.co/dNrGRc/1799_x_733_2.png","lang":"cn","remark":"","content":"","iPos":"3"},{"id_mod_set_banner":"5310","web_id":"54","type":"1","create_time":"2018-04-06 19:34:13","update_date":"2018-04-06 19:34:13","status":"0","url":"https://image.ibb.co/bWbpex/1799_x_733_3.png","lang":"cn","remark":"","content":"","iPos":"4"},{"id_mod_set_banner":"5311","web_id":"54","type":"1","create_time":"2018-04-06 19:35:57","update_date":"2018-04-06 19:35:57","status":"0","url":"https://image.ibb.co/heEEzx/1799_x_733.png","lang":"cn","remark":"","content":"","iPos":"5"},{"id_mod_set_banner":"5312","web_id":"54","type":"1","create_time":"2018-04-06 19:37:05","update_date":"2018-04-06 19:37:05","status":"0","url":"https://image.ibb.co/iXz3sH/1799_x_733_14.png","lang":"cn","remark":"","content":"","iPos":"6"},{"id_mod_set_banner":"5313","web_id":"54","type":"1","create_time":"2018-04-06 19:40:23","update_date":"2018-04-06 19:40:23","status":"0","url":"https://image.ibb.co/fF5hmc/1799_x_733_11.png","lang":"cn","remark":"","content":"","iPos":"7"},{"id_mod_set_banner":"5315","web_id":"54","type":"1","create_time":"2018-04-06 19:41:55","update_date":"2018-04-06 19:41:55","status":"0","url":"https://image.ibb.co/gbf1CH/1799_x_733_7.png","lang":"cn","remark":"","content":"","iPos":"8"},{"id_mod_set_banner":"5508","web_id":"54","type":"1","create_time":"2018-04-11 14:02:06","update_date":"2018-04-11 14:02:06","status":"0","url":"https://image.ibb.co/ia9a6c/1799_x_733_5.png","lang":"cn","remark":"","content":"","iPos":"9"},{"id_mod_set_banner":"5509","web_id":"54","type":"1","create_time":"2018-04-11 14:02:43","update_date":"2018-04-11 14:02:43","status":"0","url":"https://image.ibb.co/h0M56c/1799_x_733_6.png","lang":"cn","remark":"","content":"","iPos":"10"}]
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
         * id_mod_set_banner : 4746
         * web_id : 54
         * type : 21
         * create_time : 2018-03-07 10:03:34
         * update_date : 2018-03-07 10:03:34
         * status : 0
         * url : https://image.ibb.co/bumJJS/1.jpg
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
