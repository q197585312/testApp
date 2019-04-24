package com.nanyang.app.Been;

/**
 * Created by Administrator on 2019/3/6.
 */

public class AppVersionBean {

    /**
     * Labelid : 48
     * data : {"version":"3.08","url":"http://www.appgd88.com/androidDownload/gd88.apk"}
     */

    private String Labelid;
    private DataBean data;

    public String getLabelid() {
        return Labelid;
    }

    public void setLabelid(String Labelid) {
        this.Labelid = Labelid;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * version : 3.08
         * url : http://www.appgd88.com/androidDownload/gd88.apk
         */

        private String version;
        private String url;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
