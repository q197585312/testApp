package com.nanyang.app.main.home.sport.main;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class OutRightState extends SportState<SportInfo, SportContract.View<SportInfo>> {


    public OutRightState(SportContract.View baseView) {
        super(baseView);
    }

    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean collection() {
        return false;
    }

    @Override
    public SportAdapterHelper<SportInfo> onSetAdapterHelper() {
        return new SportAdapterHelper<SportInfo>() {
            @Override
            public void onConvert(MyRecyclerViewHolder holder, final int position, final SportInfo item) {
                TextView matchTitleTv = holder.getView(R.id.out_right_title_tv);
                View headV = holder.getView(R.id.v_out_right_header_space);
                TextView homeTv = holder.getView(R.id.out_right_home_tv);

                final TextView markTv = holder.getView(R.id.out_right_mark_tv);
                homeTv.setText(item.getHome());
                markTv.setText(item.getX12_1Odds());
                markTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        back.clickOdds(markTv, item, "1", false, item.getX12_1Odds(),Integer.valueOf(item.getSocOddsId()),"");
                    }
                });
                if (item.getType() == SportInfo.Type.ITME) {
                    matchTitleTv.setVisibility(View.GONE);
                    headV.setVisibility(View.GONE);

                } else {
                    matchTitleTv.setVisibility(View.VISIBLE);
                    headV.setVisibility(View.VISIBLE);
                    matchTitleTv.setText(item.getModuleTitle());
                    if (position == 0) {
                        headV.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public int onSetAdapterItemLayout() {
                return R.layout.sport_out_right_item;
            }

        };
    }

    @Override
    protected SportInfo parseMatch(JSONArray matchArray,boolean notify) throws JSONException {
        SportInfo info = new SportInfo();
        info.setSocOddsId(matchArray.getString(0));
        info.setHome(matchArray.getString(1));
        info.setIsInetBet(matchArray.getString(2));
        info.setIsX12New(matchArray.getString(3));
        info.setHasX12(matchArray.getString(4));
        info.setX12_1Odds(matchArray.getString(5));
        info.setPreSocOddsId(matchArray.getString(6));
        info.setNotify(notify);
        return info;
    }


    @Override
    protected List<TableSportInfo<SportInfo>> filterChildData(List<TableSportInfo<SportInfo>> allData) {
        return allData;
    }



    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new SportAdapterHelper.ItemCallBack<SportInfo>() {
            @Override
            public SportInfo getItem(int position) {
                return baseRecyclerAdapter.getItem(position);
            }

            @Override
            public void clickOdds(TextView v, SportInfo item, String type, boolean isHf, String odds,int oid,String sc) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type, odds, v, isHf, sc);
            }

            @Override
            public void clickView(View v, SportInfo item, int position) {

            }

        };
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new OutRightBetHelper(getBaseView());
    }

/*small wen, [20.04.19 10:53]
for (int i = 0, len = arrDbIds.Length; i < len; i++)
            {
                switch (arrDbIds[i])
                {
                    case "1":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf,"1");
                        break;
                    case "1_3":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "4");
                        break;
                    case "1_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "2") ;
                        break;
                    case "2":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "9");
                        break;
                    case "2_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "10");
                        break;
                    case "2_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "31");
                        break;
                    case "3":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "21");
                        break;
                    case "3_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "36");
                        break;
                    case "4":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "7");
                        break;
                    case "5":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "6");
                        break;
                    case "33":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "th");
                        break;
                    case "35":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "108");
                        break;
                    case "34":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "106");
                        break;
                    case "34_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "107");
                        break;
                    case "7":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "85");
                        break;
                    case "7_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "86");
                        break;
                    case "8":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "12");
                        break;
                    case "8_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "13");
                        break;
                    case "8_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "30");
                        break;
                    case "9":
                    case "9_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "29");
                        break;
                    case "9_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "39");
                        break;
                    case "10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "14");
                        break;
                    case "10_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "15");
                        break;
                    case "10_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "33");
                        break;
                    case "11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "11");
                        break;
                    case "11_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "32");
                        break;
                    case "12":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "17");
                        break;
                    case "12_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "18");
                        break;
                    case "12_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "34");
                        break;
                    case "13":

small wen, [20.04.19 10:53]
OddsUrl = OddsUrl + GetParam_H50(ot, tf, "19");
                        break;
                    case "13_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "20");
                        break;
                    case "13_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "35");
                        break;
                    case "14":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "16");
                        break;
                    case "14_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "92");
                        break;
                    case "15":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "25");
                        break;
                    case "15_13":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "26");
                        break;
                    case "15_16":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "27");
                        break;
                    case "16_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "46");
                        break;
                    case "16":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "49");
                        break;
                    case "17":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "22");
                        break;
                    case "17_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "37");
                        break;
                    case "19":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "28");
                        break;
                    case "19_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "40");
                        break;
                    case "19_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "63");
                        break;
                    case "20":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "51");
                        break;
                    case "20_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "52");
                        break;
                    case "21":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "53");
                        break;
                    case "21_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "54");
                        break;
                    case "22":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "57");
                        break;
                    case "22_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "58");
                        break;
                    case "23":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "44");
                        break;
                    case "23_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "61");
                        break;
                    case "24":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "23");
                        break;
                    case "24_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "62");
                        break;
                    case "25":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "41");
                        break;
                    case "25_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "42");
                        break;
                    case "25_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "43");
                        break;
                    case "26_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "64");
                        break;
                    case "26":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "65");
                        break;
                    case "27":

small wen, [20.04.19 10:53]
OddsUrl = OddsUrl + GetParam_H50(ot, tf, "67");
                        break;
                    case "27_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "68");
                        break;
                    case "27_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "69");
                        break;
                    case "28_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "91");
                        break;
                    case "29":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "101");
                        break;
                    case "29_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "102");
                        break;
                    case "30":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "103");
                        break;
                    case "30_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "104");
                        break;
                    case "31":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "105");
                        break;
                    case "32":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "99");
                        break;
                    case "36":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "182");
                        break;
                    case "36_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "183");
                        break;
                    case "666":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "200");
                        break;
                    default:
                        OddsUrl = "";
                        break;
                }
                if (OddsUrl != "")
                {
                    OddsUrl = OddsUrl + "&um=" + um;
                    _dicForm.AddExecJs("LinkWS('" + arrDbIds[i] + "_" + ot + "','" + OddsUrl + "'," + (ot == "e" ? Config.OddsRefresh : Config.RunRefresh) + ");");
                    OddsUrl = Config.WebSocketUrl + "?";
                }
            }*/
}
