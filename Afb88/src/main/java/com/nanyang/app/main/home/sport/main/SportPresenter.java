package com.nanyang.app.main.home.sport.main;


public class SportPresenter implements SportContract.Presenter {


    public SportState getStateHelper() {
        return stateHelper;
    }

    private SportState stateHelper;

    public void setStateHelper(SportState stateHelperNew) {
        if (stateHelper != null) {
            stateHelper.stopUpdateData();
        }
        this.stateHelper = stateHelperNew;
        if (stateHelper.isMix())
            stateHelper.clearMix();


    }

    public SportPresenter(SportContract.View view) {
    }


    @Override
    public void unSubscribe() {
        stateHelper.unSubscribe();
    }


}