package com.nanyang.app.main.BetCenter.Presenter;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.Utils.TimeUtils;
import com.nanyang.app.main.BetCenter.Bean.BaseParamBean;
import com.nanyang.app.main.BetCenter.Bean.DataInfoBean;
import com.nanyang.app.main.BetCenter.Bean.GradeAllMatchBean;
import com.nanyang.app.main.BetCenter.Bean.GradeOpenDataBean;
import com.nanyang.app.main.BetCenter.GradeFragment;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static com.unkonw.testapp.libs.api.Api.getService;


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

    public List<DataInfoBean> getSportsDataList() {
        List<DataInfoBean> list = new ArrayList<>();
        list.add(new DataInfoBean(baseContext.getString(R.string.sports), ""));
        return list;
    }

    public List<DataInfoBean> getDateDataList() {
        List<DataInfoBean> list = new ArrayList<>();
        String dateType = TimeUtils.getTime("aa", Locale.ENGLISH);
        String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd");
        if (dateType.equals("AM")) {
            currentDate = DateUtils.getAddDay(currentDate, -1, "yyyy-MM-dd");
        }
        list.add(new DataInfoBean(baseContext.getString(R.string.today_match), currentDate));
        for (int i = -1; i > -8; i--) {
            String name;
            if (i == -1) {
                name = baseContext.getString(R.string.yesterday_match);
            } else {
                name = DateUtils.getAddDay(currentDate, i, "MM-dd");
            }
            list.add(new DataInfoBean(name, DateUtils.getAddDay(currentDate, i, "yyyy-MM-dd")));
        }
        return list;
    }

    public List<DataInfoBean> getFootballDataList(int type) {
        List<DataInfoBean> list = new ArrayList<>();
        if (type == 1) {
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
        } else {
            list.add(new DataInfoBean(baseContext.getString(R.string.Soccer_Outright), "S,SO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Basketball_Outright), "S,BO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Olympic_Outright), "S,OLO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.US_Football_Outright), "S,NO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Baseball_Outright), "S,BBO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Ice_Hockey_Outright), "S,HO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Pool_Snooker_Outright), "S,KO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Rugby_Outright), "S,RO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Tennis_Outright), "B,TO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Darts_Outright), "S,DO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.BOXING_Outright), "S,XO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Motor_Sports_Outright), "S,MSO,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Golf_Outright), "S,GO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Futsal_Outright), "S,FSO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Badminton_Outright), "B,BDO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Water_Polo_Outright), "B,WPO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Table_Tennis_Outright), "B,TTO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Cricket_Outright), "S,CKO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Volleyball_Outright), "S,VO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Handball_Outright), "B,HBO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Beach_Soccer_Outright), "B,BSO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Winter_Sport_Outright), "S,WSO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Cycling_Outright), "S,CYO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Athletics_Outright), "B,ATO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.E_Sports_Outright), "S,ESO,p3,g3"));
        }
        return list;
    }


    public void getAllMatchDataList(String gameType, String data, String LeagueType) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetTableL", gameType, gameType, data, data, "wfStatementH50", LeagueType, "", "").getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(0);
                    JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
                    List<DataInfoBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArrayData4.length(); i++) {
                        JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
                        DataInfoBean bean = new DataInfoBean(jsonArrayArr.getString(1), jsonArrayArr.getInt(0) + "");
                        list.add(bean);
                    }
                    String language = AfbUtils.getLanguage(gradeFragment.getContext());
                    if (language.equals("zh")) {
                        Collections.sort(list, new Comparator<DataInfoBean>() {
                            @Override
                            public int compare(DataInfoBean o1, DataInfoBean o2) {
                                return Collator.getInstance(Locale.CHINESE).compare(o1.getName(), o2.getName());
                            }
                        });
                    } else {
                        Collections.sort(list);
                    }
                    List<DataInfoBean> list1 = new ArrayList<>();
                    list1.add(new DataInfoBean(gradeFragment.getString(R.string.all_match), "0"));
                    for (int i = 0; i < list.size(); i++) {
                        list1.add(list.get(i));
                    }
                    gradeFragment.onGetAllMatchDataList(list1);
                }
            }
        });
    }

    public void getGradeData(String gameType, String data, String LeagueType) {
        String p = BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetDate", gameType, gameType, data, data, "wfStatementH50", LeagueType, "", "").getJson();
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(0);
                    JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
                    List<GradeAllMatchBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArrayData4.length(); i++) {
                        JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
                        GradeAllMatchBean bean = new GradeAllMatchBean(jsonArrayArr.getInt(0), jsonArrayArr.getInt(1), jsonArrayArr.getString(2));
                        list.add(bean);
                    }
                    gradeFragment.onGetGradeData(list);
                }
            }
        });
    }

    public void getGradeContentOpenData(String gameType, String data, String LeagueType) {
        String p = BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetDateD", gameType, gameType, data, data, "wfStatementH50", LeagueType, "", "").getJson();
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(0);
                    JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
                    List<GradeOpenDataBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArrayData4.length(); i++) {
                        JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
                        GradeOpenDataBean bean = new GradeOpenDataBean(jsonArrayArr.getInt(0), jsonArrayArr.getInt(1), jsonArrayArr.getString(2),
                                jsonArrayArr.getInt(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                                jsonArrayArr.getString(6), jsonArrayArr.getInt(7), jsonArrayArr.getString(8),
                                jsonArrayArr.getInt(9), jsonArrayArr.getString(10), jsonArrayArr.getString(11),
                                jsonArrayArr.getString(12), jsonArrayArr.getInt(13), jsonArrayArr.getInt(14),
                                jsonArrayArr.getInt(15), jsonArrayArr.getString(16), jsonArrayArr.getString(17));
                        list.add(bean);
                    }
                    gradeFragment.onGradeContentOpenData(list);
                }
            }
        });
    }
}
