package com.nanyang.app.main.home.sport.allRunning;

import android.widget.LinearLayout;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.home.sport.main.BaseAllFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ASUS on 2019/4/23.
 */

public class AllRunningFragment extends BaseAllFragment {


    @Bind(R.id.ll_footer_sport)
    protected LinearLayout ll_footer_sport;
    @Bind(R.id.ll_header_sport)
    protected LinearLayout ll_header_sport;

    @Override
    public void initData() {
        super.initData();
        setTitle(getString(R.string.all_running));
        switchType("Running");
//        ((SportActivity)getBaseActivity()).switchTypeAdapter
    }

    @Override
    protected void addSportHeadAndFoot(final SportIdBean sportIdBean) {
        if (sportIdBean == null)
            return;
        this.currentIdBean = sportIdBean;
        getBaseActivity().presenter.loadAllMainData(new LoginInfo.LanguageWfBean("Getmenu", new LanguageHelper(mContext).getLanguage(), "wfMainH50"), new LanguagePresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                try {
                    final JSONObject jsonObjectNum = new JSONObject(data);
                    List<String> all = Arrays.asList("1", "9", "21", "29", "14", "182");
                    List<SportIdBean> allTopSport = new ArrayList<>();
                    List<SportIdBean> allBottomSport = new ArrayList<>();
                    boolean addHead = true;

                    for (int i = 0; i < all.size(); i++) {
                        String s = all.get(i);
                        SportIdBean sportIdIndex = AfbUtils.getSportFromOtherAndSportByG(s);
                        if (!StringUtils.isNull(jsonObjectNum.optString("M_RAm" + sportIdIndex.getDbid()))) {
                            if (addHead) {
                                allTopSport.add(sportIdIndex);
                            } else {
                                allBottomSport.add(sportIdIndex);
                            }
                        }
                        if (s.equals(sportIdBean.getId())) {
                            addHead = false;
                        }
                    }
                    initHeadAndFoot(allTopSport, ll_header_sport);
                    initHeadAndFoot(allBottomSport, ll_footer_sport);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void switchType(String type) {
        switchState(new AllRunningRunningState(this));
    }

    @Override
    public String getTitle() {
        return getString(R.string.all_running);
    }
}
