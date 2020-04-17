package com.nanyang.app.main.message;

import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.BaseSwitchFragment;
import com.nanyang.app.main.LoadMainDataHelper;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

/**
 * Created by Administrator on 2019/5/23.
 */

class MessagePresenter extends BaseRetrofitPresenter<BaseSwitchFragment> {

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public MessagePresenter(BaseSwitchFragment iBaseContext) {
        super(iBaseContext);
    }

    public void getMessages( MainPresenter.CallBack<String> back) {
        LoadMainDataHelper<LoginInfo.LanguageWfBean> helper = new LoadMainDataHelper<>(mApiWrapper, baseContext.getBaseActivity(), mCompositeSubscription);
        //{"ACT":"GetTables","PT":"wfMessagesH50","DateFrom":"2019-08-27","DateTo":"2019-8-31","pgLable":"0.14382696923993654","vsn":"4.0.12"}
        LoginInfo.LanguageWfBean wf = new LoginInfo.LanguageWfBean("GetTables", new LanguageHelper(baseContext.getBaseActivity()).getLanguage(), "wfMessagesH50");
        helper.doRetrofitApiOnUiThreadHTML(wf, back);
    }

}
