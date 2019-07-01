package com.nanyang.app.main.person;

import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.main.LoadMainDataHelper;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

/**
 * Created by Administrator on 2019/5/23.
 */

class PersonPresenter extends BaseRetrofitPresenter<PersonCenterFragment>{

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public PersonPresenter(PersonCenterFragment iBaseContext) {
        super(iBaseContext);
    }
    public void saveNickName(String name, MainPresenter.CallBack back){
        LoadMainDataHelper<SetNameWfBean> helper=new LoadMainDataHelper<>(mApiWrapper,baseContext.getBaseActivity(),mCompositeSubscription);

        SetNameWfBean setNameWfBean=new SetNameWfBean("SaveNick", new LanguageHelper(baseContext.getBaseActivity()).getLanguage(),"wfMycountH50");
        setNameWfBean.setNickname(name);
        helper.doRetrofitApiOnUiThread(setNameWfBean,back );
    }

}
