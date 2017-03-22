package com.nanyang.app.main.home.sportInterface;


public class SportPresenter implements SportContract.Presenter {



    public IObtainDataState getStateHelper() {
        return stateHelper;
    }

    private IObtainDataState stateHelper;

    public void setStateHelper(IObtainDataState stateHelperNew) {
        if (stateHelper != null)
            stateHelper.stopUpdateData();
        this.stateHelper = stateHelperNew;


    }

    public SportPresenter(SportContract.View view) {
    }


    @Override
    public void unSubscribe() {
        stateHelper.unSubscribe();
    }



}