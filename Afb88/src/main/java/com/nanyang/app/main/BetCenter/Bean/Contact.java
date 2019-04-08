package com.nanyang.app.main.BetCenter.Bean;

import java.util.List;

/**
 * Created by 47184 on 2019/3/14.
 */

public class Contact {

    private List<ContactBean> contact;

    public List<ContactBean> getContact() {
        return contact;
    }

    public void setContact(List<ContactBean> contact) {
        this.contact = contact;
    }

    public static class ContactBean {
        /**
         * title : AFB88
         * type : 2
         * icon_type : 2
         * layout_type : 1
         */

        private String title;
        private String type;
        private String icon_type;
        private String layout_type;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIcon_type() {
            return icon_type;
        }

        public void setIcon_type(String icon_type) {
            this.icon_type = icon_type;
        }

        public String getLayout_type() {
            return layout_type;
        }

        public void setLayout_type(String layout_type) {
            this.layout_type = layout_type;
        }
    }
}
