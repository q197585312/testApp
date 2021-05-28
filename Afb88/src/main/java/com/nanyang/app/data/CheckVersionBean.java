package com.nanyang.app.data;

/**
 * Created by Administrator on 2019/5/6.
 */

public class CheckVersionBean {

    /**
     * appName : afb1188
     * data : {"version":"1.69","url":"http://www.appgd88.com/androidAppDownload/afb1188.apk"}
     */

    private String appName;
    private DataBean data;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * version : 1.69
         * url : http://www.appgd88.com/androidAppDownload/afb1188.apk
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
