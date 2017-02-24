package com.nanyang.app.main.home.sport.thaiThousand;

import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.SportPresenter;
import com.nanyang.app.main.home.sport.model.MatchBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class ThaiThousandPresenter extends SportPresenter<List<MatchBean>,SportContract.View<List<MatchBean>>>{
    public ThaiThousandPresenter(SportContract.View<List<MatchBean>> view) {
        super(view);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void collection() {

    }

    @Override
    public void menu() {

    }

    @Override
    public void mixParlay() {

    }
}
