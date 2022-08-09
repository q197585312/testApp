package com.nanyang.app.data;

import java.util.List;

public class BannerBean {

    private List<BannerListBean> bannerList;


    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }


    public static class BannerListBean {

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

}
