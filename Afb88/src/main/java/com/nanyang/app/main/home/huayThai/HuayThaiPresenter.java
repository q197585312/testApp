package com.nanyang.app.main.home.huayThai;

import android.graphics.Color;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.LoadMainDataHelper;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HuayThaiPresenter extends BaseRetrofitPresenter<HuayThaiFragment> {


    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param IBaseActivity
     */
    public HuayThaiPresenter(HuayThaiFragment IBaseActivity) {
        super(IBaseActivity);
    }


    public void refresh(String type) {
        LoadMainDataHelper helper = new LoadMainDataHelper(mApiWrapper, baseContext.getBaseActivity(), mCompositeSubscription);
        String language = new LanguageHelper(baseContext.getBaseActivity()).getLanguage();
        LoginInfo.HuayDataWfBean languageWfBean = new LoginInfo.HuayDataWfBean("BindWorkingDates", language, "wfMainH50");
        languageWfBean.setType(type);
        helper.doRetrofitApiOnUiThread(languageWfBean, new LanguagePresenter.CallBack<String>() {
            @Override
            public void onBack(String data) throws JSONException {
                JSONArray jsonArray = new JSONArray(data);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArray2 = jsonArray.getJSONArray(2);
                    HuayDrawDateInfo lists = new HuayDrawDateInfo();
                    List<HuayDrawDateInfo.DicAllBean> dicAll = new ArrayList<HuayDrawDateInfo.DicAllBean>();
                    for (int i = 0; i < jsonArray2.length(); i++) {
                        if (jsonArray2.getJSONArray(i).length() > 1) {
                            dicAll.add(new HuayDrawDateInfo.DicAllBean(jsonArray2.getJSONArray(i).getString(0), jsonArray2.getJSONArray(i).getString(1)));
                        }
                    }
                    lists.setDicAll(dicAll);
                    baseContext.onGetData(lists);
                }

            }
        });

    }


    public void submitBet(String typed, String txtNumber1, String txtAmt, String socOddsId, final TextView textView) {

        LoadMainDataHelper helper = new LoadMainDataHelper(mApiWrapper, baseContext.getBaseActivity(), mCompositeSubscription);
        String language = new LanguageHelper(baseContext.getBaseActivity()).getLanguage();
        LoginInfo.HuayBetWfBean languageWfBean = new LoginInfo.HuayBetWfBean("bet", language, "wfMainH50");
        languageWfBean.setTyped(typed);
        languageWfBean.setTxtNumber1(txtNumber1);
        languageWfBean.setTxtAmt(txtAmt);
        languageWfBean.setSocOddsId(socOddsId);
        helper.doRetrofitApiOnUiThread(languageWfBean, new LanguagePresenter.CallBack<String>() {
            @Override
            public void onBack(String data) throws JSONException {
                JSONObject jsonObj = new JSONObject(data);
                if (jsonObj.length() > 0) {
                    textView.setTextColor(Color.BLUE);
                    textView.setText(jsonObj.optString("msg"));
                } else {
                    textView.setTextColor(Color.RED);
                    textView.setText(R.string.bet_failed);
                }

            }
        });
        ;
    }

}
