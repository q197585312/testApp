package com.nanyang.app.main.home.sport.main;


import com.nanyang.app.main.home.sportInterface.IObtainDataState;

public class SportPresenter implements SportContract.Presenter {


    public IObtainDataState getStateHelper() {
        return stateHelper;
    }

    private IObtainDataState stateHelper;

    public void setStateHelper(IObtainDataState stateHelperNew) {
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