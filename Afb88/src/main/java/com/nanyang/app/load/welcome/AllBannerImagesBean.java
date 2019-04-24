package com.nanyang.app.load.welcome;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2019/3/12.
 */

public class AllBannerImagesBean implements Serializable {


    @Override
    public String toString() {
        return "AllBannerImagesBean{" +
                "status='" + status + '\'' +
                ", loginBanners=" + loginBanners +
                ", mainBanners=" + mainBanners +
                ", main=" + main +
                '}';
    }



    private String status;


    /**
     * id : 1
     * url :
     * img : https://www.afb1188.com/H50/img/slide_show_festival.jpg
     */

    private List<BannersBean> loginBanners;
    /**
     * id : 1
     * url :
     * img : http://appgd88.com/images/afb88/home_banner_1.jpg
     */

    private List<BannersBean> mainBanners;

    /**
     * id : 1
     * img : https://www.afb1188.com/H50/Img/soccer.jpg
     */


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<BannersBean> getLoginBanners() {
        return loginBanners;
    }

    public void setLoginBanners(List<BannersBean> loginBanners) {
        this.loginBanners = loginBanners;
    }

    public List<BannersBean> getMainBanners() {
        return mainBanners;
    }

    public void setMainBanners(List<BannersBean> mainBanners) {
        this.mainBanners = mainBanners;
    }

    public List<MainBannersBean> getMain() {
        return main;
    }

    public void setMain(List<MainBannersBean> main) {
        this.main = main;
    }


    public static class LoginBannersBean {
        private String id;
        private String url;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        @Override
        public String toString() {
            return "LoginBannersBean{" +
                    "id='" + id + '\'' +
                    ", url='" + url + '\'' +
                    ", img='" + img + '\'' +
                    '}';
        }
    }

    private List<MainBannersBean> main;

    public static class MainBannersBean {
        /*   "dbid": "1",
                   "g": "1",
                   "img": "https:\/\/www.afb1188.com\/H50\/Img\/soccer.jpg"
       }, {*/
        private String dbid;
        private String g;

        public String getDbid() {
            return dbid;
        }

        public void setDbid(String dbid) {
            this.dbid = dbid;
        }

        public String getG() {
            return g;
        }

        public void setG(String g) {
            this.g = g;
        }

        private String img;



        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        @Override
        public String toString() {
            return "MainBannersBean{" +
                    "dbid='" + dbid + '\'' +
                    ", g='" + g + '\'' +
                    ", img='" + img + '\'' +
                    '}';
        }
    }

    public static class BannersBean {
        private String id;
        private String url;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        @Override
        public String toString() {
            return "BannersBean{" +
                    "id='" + id + '\'' +
                    ", url='" + url + '\'' +
                    ", img='" + img + '\'' +
                    '}';
        }
    }


}
