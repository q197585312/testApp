package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/18.
 */
public class UpdateAppInfoBean implements Serializable{


    /**
     * code : 1
     * msg : 1
     * data : {"version":"2.8","description":"Version description","urlios":"https://down-hk01-cn2.k-api.com/ios_khmergaming_release.plist","versionAndroid":"1.0","descriptionAndroid":"Version description","urlAndroid":"https://down-hk01-cn2.k-api.com/android_khmergaming_release.apk"}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * version : 2.8
         * description : Version description
         * urlios : https://down-hk01-cn2.k-api.com/ios_khmergaming_release.plist
         * versionAndroid : 1.0
         * descriptionAndroid : Version description
         * urlAndroid : https://down-hk01-cn2.k-api.com/android_khmergaming_release.apk
         */

        private String version;
        private String description;
        private String urlios;
        private String versionAndroid;
        private String descriptionAndroid;
        private String urlAndroid;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrlios() {
            return urlios;
        }

        public void setUrlios(String urlios) {
            this.urlios = urlios;
        }

        public String getVersionAndroid() {
            return versionAndroid;
        }

        public void setVersionAndroid(String versionAndroid) {
            this.versionAndroid = versionAndroid;
        }

        public String getDescriptionAndroid() {
            return descriptionAndroid;
        }

        public void setDescriptionAndroid(String descriptionAndroid) {
            this.descriptionAndroid = descriptionAndroid;
        }

        public String getUrlAndroid() {
            return urlAndroid;
        }

        public void setUrlAndroid(String urlAndroid) {
            this.urlAndroid = urlAndroid;
        }
    }
}
