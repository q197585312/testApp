package com.nanyang.app.load;

import com.nanyang.app.load.welcome.AllBannerImagesBean;

import java.util.List;

/**
 * Created by ASUS on 2019/3/14.
 */

public class ListLoginBanners {
    public ListLoginBanners(List<AllBannerImagesBean.BannersBean> bannersBeen) {
        this.bannersBeen = bannersBeen;
    }

    public List<AllBannerImagesBean.BannersBean> getBannersBeen() {
        return bannersBeen;
    }

    public void setBannersBeen(List<AllBannerImagesBean.BannersBean> bannersBeen) {
        this.bannersBeen = bannersBeen;
    }

    List<AllBannerImagesBean.BannersBean> bannersBeen;

}
