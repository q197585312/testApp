package com.nanyang.app.main.home.sport;


import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

abstract class SportPresenter<T, A extends  ApiSport> extends BaseRetrofitPresenter<T, SportContract.View<T>,A> implements SportContract.Presenter {
    public SportPresenter(SportContract.View<T> view) {
        super(view);
    }
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）



    @Override
    public void refresh() {
    }
    @Override
    public void collection() {
    }
    @Override
    public void menu() {
    }
    @Override
    public void mix() {
    }
}