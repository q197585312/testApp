package com.nanyang.app.main.home.sportInterface;


public abstract class SportPresenter2 implements SportContract2.Presenter {



    public IObtainDataState getStateHelper() {
        return stateHelper;
    }

    private IObtainDataState stateHelper;

    public void setStateHelper(IObtainDataState stateHelperNew) {
        if (stateHelper != null)
            stateHelper.stopUpdateData();
        this.stateHelper = stateHelperNew;


    }

    public SportPresenter2(SportContract2.View view) {
    }


    @Override
    public void unSubscribe() {
        stateHelper.unSubscribe();
    }


    public abstract String getBallType();
}