package com.nanyang.app.main.BetCenter.Presenter;

import com.nanyang.app.R;
import com.nanyang.app.main.BetCenter.Bean.DataInfoBean;
import com.nanyang.app.main.BetCenter.GradeFragment;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2019/4/6.
 */

public class GradePresenter extends BaseRetrofitPresenter<GradeFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    private GradeFragment gradeFragment;

    public GradePresenter(GradeFragment iBaseContext) {
        super(iBaseContext);
        gradeFragment = iBaseContext;
    }

    public void getNormalGradeData() {
        gradeFragment.onGetNormalGradeData();
    }

    public void getChampionGradeData() {
        gradeFragment.onGeChampionGradeData();
    }

    public List<DataInfoBean> getSportsDataList() {
        return new ArrayList<DataInfoBean>();
    }

    public List<DataInfoBean> getDateDataList() {
        return new ArrayList<DataInfoBean>();
    }

    public List<DataInfoBean> getFootballDataList() {
        List<DataInfoBean> list = new ArrayList<>();
        list.add(new DataInfoBean(baseContext.getString(R.string.Soccer_name), "S,S,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string._1D_name), "1,S,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string._2D_name), "2,S,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Basketball_name), "B,B,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Olympic_name), "B,OL,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.USFootball_name), "B,N,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Baseball_name), "S,BB,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.IceHockey_name), "S,H,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.PoolSnooker_name), "S,K,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Rugby_name), "B,R,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Tennis_name), "B,T,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Darts_name), "S,D,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.boxing_name), "S,X,p4,g4"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Formula1_name), "S,F1H,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.MotorSports_name), "S,MSH,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Golf_name), "S,G,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Futsal_name), "S,FS,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Badminton_name), "B,BD,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.WaterPolo_name), "B,WP,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.TableTennis_name), "B,TT,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Cricket_name), "S,CK,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Volleyball_name), "S,V,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Handball_name), "B,HB,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.BeachSoccer_name), "B,BS,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Financials_name), "S,F,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.WinterSport_name), "S,WS,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Cycling_name), "S,CYH,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Squash_name), "B,SQ,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.HuayThai_name), "S,TH,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Athletics_name), "B,AT,p2,g2"));
        list.add(new DataInfoBean(baseContext.getString(R.string.ESports_name), "S,ES,p1,g1"));
        list.add(new DataInfoBean(baseContext.getString(R.string.Muay_Thai_name), "S,MT,p4,g4"));
        return new ArrayList<DataInfoBean>();
    }


    public List<DataInfoBean> getAllMatchDataList() {
        return new ArrayList<DataInfoBean>();
    }
}
