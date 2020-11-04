package com.nanyang.app.main.home.sport.main;


public class SportPresenter implements SportContract.Presenter {


    public SportState getStateHelper() {
        return stateHelper;
    }

    private SportState stateHelper;

    public void setStateHelper(SportState stateHelperNew) {
        this.stateHelper = stateHelperNew;

    }

    public SportPresenter(SportContract.View view) {
    }


    @Override
    public void unSubscribe() {
        stateHelper.unSubscribe();
    }


}