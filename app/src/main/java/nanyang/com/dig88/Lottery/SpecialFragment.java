package nanyang.com.dig88.Lottery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.Entity.SpecialBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.base.quick.QuickRequestBean;

/**
 * @author Administrator
 */
public class SpecialFragment extends LotteryBaseFragment<SpecialBean> {


    @Bind(R.id.special_bet_list_lv)
    ListView specialBetListLv;
    @Bind(R.id.special_no_record_tv)
    TextView specialNoRecordTv;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        specialBetListLv.setAdapter(adapter);
        getspecialbet();
    }

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.special_fragment;
    }

    /**
     * 获取特殊赔率列表
     */
    private void getspecialbet() {
        NyVolleyJsonThreadHandler<List<SpecialBean>> dividendThread = new NyVolleyJsonThreadHandler<List<SpecialBean>>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", "2");
                params.put("user_id", getUserInfo().getUser_id());
                params.put("session_id", getUserInfo().getSession_id());
                //游戏状态
                //"http://appi01.sgtest.dig88api.net/index.php?page=lott_special_submitter"
                return new QuickRequestBean(WebSiteUrl.LottSpecialSubmitter, params
                        , new TypeToken<NyBaseResponse<List<SpecialBean>>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, List<SpecialBean> data) {

                if (data == null || data.size() < 1) {
                    if (specialNoRecordTv != null) {
                        specialNoRecordTv.setVisibility(View.VISIBLE);
                        specialNoRecordTv.setText(getString(R.string.nojilu));
                    }
                    if(specialBetListLv!=null)
                        specialBetListLv.setVisibility(View.GONE);
                } else {
                    if (specialNoRecordTv != null) {
                        specialNoRecordTv.setVisibility(View.GONE);
                    }
                    if(specialBetListLv!=null) {
                        specialBetListLv.setVisibility(View.VISIBLE);
                        adapter.setList(data);
                    }
                }
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                if (specialNoRecordTv != null) {
                    specialNoRecordTv.setVisibility(View.VISIBLE);
                    specialNoRecordTv.setText(getString(R.string.nojilu));
                }
                if(specialBetListLv!=null)
                    specialBetListLv.setVisibility(View.GONE);

            }
        };

        dividendThread.startThread(null);
    }

    @Override
    public void updateGameState(List<DigGameOddsBean> oddsList, LotteryStateGameBean bean) {
        getspecialbet();
    }

    @Override
    public LotteryBaseAdapter<SpecialBean> getAdapter() {
        return new LotteryBaseAdapter<SpecialBean>(mContext, R.layout.item_specialbet_list) {
            @Override
            protected List<SpecialBean> initList() {
                return new ArrayList<>();
            }

            @Override
            protected void clearListMoney() {

            }

            @Override
            protected void countTotal() {

            }

            @Override
            protected void convert(ViewHolder helper, SpecialBean item, int position) {
                helper.setText(R.id.tv_bianhao, (position + 1) + "");
                helper.setText(R.id.tv_riqi, item.getDate());
                helper.setText(R.id.tv_qihao, item.getGameid());
                String name = getAct().getSelectedStateBean().getGame_name();
                if (name.equals("SINGAPORE")) {
                    name = getString(R.string.SINGAPORE);
                } else if (name.equals("TAIPEI")) {
                    name = getString(R.string.TAIPEI);
                } else if (name.equals("KUALA_LUMPUR")) {
                    name = getString(R.string.KUALA_LUMPUR);
                } else if (name.equals("HONGKONG")) {
                    name = getString(R.string.HONGKONG);
                } else if (name.equals("MALAYSIA")) {
                    name = getString(R.string.MALAYSIA);
                } else if (name.equals("CHINA")) {
                    name = getString(R.string.CHINA);
                } else if (name.equals("HK4D")) {
                    name = getString(R.string.HK4D);
                } else if (name.equals("CAMBODIA")) {
                    name = getString(R.string.CAMBODIA);
                } else if (name.equals("MACAU")) {
                    name = getString(R.string.MACAU);
                } else if (name.equals("CANADA4D")) {
                    name = getString(R.string.CANADA4D);
                }
                helper.setText(R.id.tv_area, name);
                String typeName = "2D";
                if (item.getNames().equals("1")) {
                    typeName = "2D";
                } else if (item.getNames().equals("2")) {
                    typeName = "3D";
                } else if (item.getNames().equals("3")) {
                    typeName = "4D";
                } else if (item.getNames().equals("4")) {
                    typeName = "BIG SMALL";
                } else if (item.getNames().equals("5")) {
                    typeName = "EVEN ODD";
                } else if (item.getNames().equals("6")) {
                    typeName = "COMBINATION";
                } else if (item.getNames().equals("7")) {
                    typeName = "COLOK ANGKA";
                } else if (item.getNames().equals("8")) {
                    typeName = "COLOK MACAU";
                } else if (item.getNames().equals("9")) {
                    typeName = "COLOK NAGA";
                } else if (item.getNames().equals("10")) {
                    typeName = "COLOK JITU";
                } else if (item.getNames().equals("11")) {
                    typeName = "COLOK SHIO(KUDA)";
                } else if (item.getNames().equals("12")) {
                    typeName = "COLOK SHIO(ULAR)";
                } else if (item.getNames().equals("13")) {
                    typeName = "COLOK SHIO（NAGA）";
                } else if (item.getNames().equals("14")) {
                    typeName = "COLOK SHIO（KELINCI）";
                } else if (item.getNames().equals("15")) {
                    typeName = "COLOK SHIO（HARIMAU）";
                } else if (item.getNames().equals("16")) {
                    typeName = "COLOK SHIO（KERBAU）";
                } else if (item.getNames().equals("17")) {
                    typeName = "COLOK SHIO（TIKUS）";
                } else if (item.getNames().equals("18")) {
                    typeName = "COLOK SHIO（BABI）";
                } else if (item.getNames().equals("19")) {
                    typeName = "COLOK SHIO（ANJING）";
                } else if (item.getNames().equals("20")) {
                    typeName = "COLOK SHIO（AYAM）";
                } else if (item.getNames().equals("21")) {
                    typeName = "COLOK SHIO（MONYET）";
                } else if (item.getNames().equals("22")) {
                    typeName = "COLOK SHIO（KAMBING）";
                }
                helper.setText(R.id.tv_mingcheng, typeName);
                helper.setText(R.id.tv_wanfa, item.getType());
                helper.setText(R.id.tv_peilv, item.getRate());
                helper.setText(R.id.tv_youhui, item.getDiscount());
                helper.setText(R.id.tv_kei, item.getKei());
            }
        };
    }

    @Override
    protected String getSubmitPageUrl() {
        return null;
    }

    @Override
    protected String constructorGetBetStr() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
