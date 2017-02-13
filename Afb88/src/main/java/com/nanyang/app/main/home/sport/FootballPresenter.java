package com.nanyang.app.main.home.sport;

import com.nanyang.app.ApiService;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class
FootballPresenter extends SportPresenter<String,ApiSport> {
    public FootballPresenter(SportContract.View<String> view) {
        super(view);
    }

    @Override
    public ApiSport createRetrofitApi() {
        return new ApiSport() {
            @Override
            Flowable<String> refresh() {
                return applySchedulers(getService(ApiService.class).goRefresh());
            }
        };
    }

}
