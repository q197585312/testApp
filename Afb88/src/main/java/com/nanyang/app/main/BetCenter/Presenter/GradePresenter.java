package com.nanyang.app.main.BetCenter.Presenter;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.main.BetCenter.Bean.BaseParamBean;
import com.nanyang.app.main.BetCenter.Bean.DataInfoBean;
import com.nanyang.app.main.BetCenter.Bean.GradeAllMatchBean;
import com.nanyang.app.main.BetCenter.Bean.GradeOpenDataBean;
import com.nanyang.app.main.BetCenter.GradeFragment;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.TimeUtils;

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
                name = DateUtils.getAddDay(currentDate, i, "dd/MM");
            }
            list.add(new DataInfoBean(name, DateUtils.getAddDay(currentDate, i, "yyyy-MM-dd")));
        }
        return list;
    }

    public List<DataInfoBean> getFootballDataList(int type) {
        List<DataInfoBean> list = new ArrayList<>();
        if (type == 1) {
            list.add(new DataInfoBean(baseContext.getString(R.string.Soccer), "S,S,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string._1D_name), "1,S,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string._2D_name), "2,S,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Basketball), "B,B,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.OLYMPIC), "B,OL,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.US_Football), "B,N,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Baseball), "S,BB,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.IceHockey), "S,H,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Pool), "S,K,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Rugby), "B,R,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Tennis), "B,T,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Darts), "S,D,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Boxing), "S,X,p4,g4"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Formula1), "S,F1H,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Motor_Sports), "S,MSH,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Golf), "S,G,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Futsal), "S,FS,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Badminton), "B,BD,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Water_Polo), "B,WP,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Table_Tennis), "B,TT,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Cricket), "S,CK,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Volleyball), "S,V,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Handball), "B,HB,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Beach_Soccer), "B,BS,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Financial), "S,F,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.WinterSport), "S,WS,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Cycling), "S,CYH,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Squash), "B,SQ,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Huay_Thai), "S,TH,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Athletics), "B,AT,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.E_Sport), "S,ES,p1,g1"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Muay_Thai), "S,MT,p4,g4"));
        } else {
            list.add(new DataInfoBean(baseContext.getString(R.string.Soccer) + " - " + baseContext.getString(R.string.OutRight), "S,SO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Basketball) + " - " + baseContext.getString(R.string.OutRight), "S,BO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.OLYMPIC) + " - " + baseContext.getString(R.string.OutRight), "S,OLO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.US_Football) + " - " + baseContext.getString(R.string.OutRight), "S,NO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Baseball) + " - " + baseContext.getString(R.string.OutRight), "S,BBO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.IceHockey) + " - " + baseContext.getString(R.string.OutRight), "S,HO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Pool) + " - " + baseContext.getString(R.string.OutRight), "S,KO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Rugby) + " - " + baseContext.getString(R.string.OutRight), "S,RO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Tennis) + " - " + baseContext.getString(R.string.OutRight), "B,TO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Darts) + " - " + baseContext.getString(R.string.OutRight), "S,DO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Boxing) + " - " + baseContext.getString(R.string.OutRight), "S,XO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Motor_Sports) + " - " + baseContext.getString(R.string.OutRight), "S,MSO,p2,g2"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Golf) + " - " + baseContext.getString(R.string.OutRight), "S,GO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Futsal) + " - " + baseContext.getString(R.string.OutRight), "S,FSO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Badminton) + " - " + baseContext.getString(R.string.OutRight), "B,BDO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Water_Polo) + " - " + baseContext.getString(R.string.OutRight), "B,WPO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Table_Tennis) + " - " + baseContext.getString(R.string.OutRight), "B,TTO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Cricket) + " - " + baseContext.getString(R.string.OutRight), "S,CKO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Volleyball) + " - " + baseContext.getString(R.string.OutRight), "S,VO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Handball) + " - " + baseContext.getString(R.string.OutRight), "B,HBO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Beach_Soccer) + " - " + baseContext.getString(R.string.OutRight), "B,BSO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.WinterSport) + " - " + baseContext.getString(R.string.OutRight), "S,WSO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Cycling) + " - " + baseContext.getString(R.string.OutRight), "S,CYO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.Athletics) + " - " + baseContext.getString(R.string.OutRight), "B,ATO,p3,g3"));
            list.add(new DataInfoBean(baseContext.getString(R.string.E_Sport) + " - " + baseContext.getString(R.string.OutRight), "S,ESO,p3,g3"));
        }
        return list;
    }


    public void getAllMatchDataList(String gameType, String data, String LeagueType) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetTableL", gameType, gameType, data, data, "wfStatementH50", LeagueType, "", "").getJson()), new BaseConsumer<String>(baseContext) {
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
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetDate", gameType, gameType, data, data, "wfStatementH50", LeagueType, "", "").getJson();
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
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetDateD", gameType, gameType, data, data, "wfStatementH50", LeagueType, "", "").getJson();
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
