package com.nanyang.app.main.home.sport.main;


public class SportPresenter implements SportContract.Presenter {


    public String type;

    public SportState getStateHelper() {
        return stateHelper;
    }

    private SportState stateHelper;

    public void setStateHelper(SportState stateHelperNew) {
        this.stateHelper = stateHelperNew;
        String type = stateHelper.getStateType().getType();
        if (this.type == null || !this.type.equals(type)) {
            this.type = type;
            stateHelper.getAdapterHelper().additionMap.put(true, "");

        }

    }

    public SportPresenter(SportContract.View view) {
    }


    @Override
    public void unSubscribe() {
        stateHelper.unSubscribe();
    }


}