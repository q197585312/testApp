package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/7/15.
 */

public class ContactBean {

    /**
     * msg : 1
     * data : [{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/live.png","chatType":"livechat_2","chatContent":"8425091","chat":"ff"},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/live.png","chatType":"livechat_1","chatContent":"85510200888","chat":"85510200888"},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/line.png","chatType":"line","chatContent":"066717888","chat":""},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/whatsapp.png","chatType":"whatsapp","chatContent":"2354234234","chat":"https://api.whatsapp.com/send?phone=85569492887&amp;text=&amp;source=&amp;data="},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/skype.png","chatType":"skype","chatContent":"skypetest","chat":""},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/wechat.png","chatType":"wechat","chatContent":"wechattest","chat":""},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/telegram.png","chatType":"telegram","chatContent":"telegram test","chat":""},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/bbm.png","chatType":"bbm","chatContent":"bbmtest24234234","chat":""},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/mail.png","chatType":"email","chatContent":"abdserc@email.com","chat":""},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/facebook.png","chatType":"facebook","chatContent":"fsdfsdfsdfsdftest","chat":""},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/twitter.png","chatType":"twitter","chatContent":"twitter45","chat":""},{"piclogo":"https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/tel.png","chatType":"tel","chatContent":"214124323456","chat":""}]
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
         * piclogo : https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/kg/ico/sns/live.png
         * chatType : livechat_2
         * chatContent : 8425091
         * chat : ff
         */

        private String piclogo;
        private String chatType;
        private String chatContent;
        private String chat;
        private String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getPiclogo() {
            return piclogo;
        }

        public void setPiclogo(String piclogo) {
            this.piclogo = piclogo;
        }

        public String getChatType() {
            return chatType;
        }

        public void setChatType(String chatType) {
            this.chatType = chatType;
        }

        public String getChatContent() {
            return chatContent;
        }

        public void setChatContent(String chatContent) {
            this.chatContent = chatContent;
        }

        public String getChat() {
            return chat;
        }

        public void setChat(String chat) {
            this.chat = chat;
        }
    }
}
