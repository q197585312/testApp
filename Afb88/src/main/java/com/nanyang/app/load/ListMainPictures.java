package com.nanyang.app.load;

import com.nanyang.app.load.welcome.AllBannerImagesBean;

import java.util.List;

/**
 * Created by ASUS on 2019/3/14.
 */

public class ListMainPictures {
    public ListMainPictures(List<AllBannerImagesBean.MainBannersBean> bannersBeen) {
        this.bannersBeen = bannersBeen;
    }

    public List<AllBannerImagesBean.MainBannersBean> getBannersBeen() {
        return bannersBeen;
    }

    public void setBannersBeen(List<AllBannerImagesBean.MainBannersBean> bannersBeen) {
        this.bannersBeen = bannersBeen;
    }

    List<AllBannerImagesBean.MainBannersBean> bannersBeen;

}
