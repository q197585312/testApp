package com.nanyang.app.main.home.sportInterface;


import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.training.ScrollLayout;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class SportPresenter2 implements SportContract2.Presenter {


    private IObtainDataState stateHelper;

    public void setStateHelper(IObtainDataState stateHelper) {
        this.stateHelper = stateHelper;
    }
    public SportPresenter2(SportContract2.View view) {
        mCompositeSubscription = new CompositeDisposable();
    }

    @Override
    public void refresh() {
        Disposable subscription = stateHelper.refresh();
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void collection() {
        stateHelper.collection();
    }

    @Override
    public void menu() {
        stateHelper.menu();
    }

    @Override
    public boolean mixParlay() {
        return stateHelper.mix();
    }

    @Override
    public void startUpdate() {
        Disposable disposable = stateHelper.startUpdateData();
        mCompositeSubscription.add(disposable);
    }

    @Override
    public void stopUpdate() {
        stateHelper.stopUpdateData();
    }

    @Override
    public void onPrevious(SwipeToLoadLayout swipeToLoadLayout) {
        stateHelper.onPrevious(swipeToLoadLayout);
    }

    @Override
    public void onNext(SwipeToLoadLayout swipeToLoadLayout) {
        stateHelper.onNext(swipeToLoadLayout);
    }

    protected CompositeDisposable mCompositeSubscription;
    @Override
    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }


    public BaseRecyclerAdapter switchTypeAdapter() {
        return stateHelper.switchTypeAdapter() ;
    }

    public void setHeaderContent(ScrollLayout slHeader) {
        stateHelper.setHeaderContent(slHeader);
    }
}