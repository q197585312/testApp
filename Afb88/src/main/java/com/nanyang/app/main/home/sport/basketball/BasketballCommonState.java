package com.nanyang.app.main.home.sport.basketball;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.europe.BallState;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.widget.PopOneBtn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class BasketballCommonState extends BallState {

    private boolean isCollection;


    public BasketballCommonState(SportContract.View baseView) {
        super(baseView);
    }


/*
    public boolean collection() {
        isCollection = !isCollection;
        initAllData(allData);
        return isCollection;
    }
*/

    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        IAdapterHelper<BallInfo> adapterHelper = onSetCommonAdapterHelper();
        return adapterHelper;
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<BallInfo>(baseRecyclerAdapter) {
            public boolean isItemCollection(BallInfo item) {
                return isItemCollectionCommon(item);
            }

            @Override
            public boolean isLeagueCollection(BallInfo item) {
                return isLeagueCollectionCommon(item);
            }

            @Override
            public void clickOdds(TextView v, BallInfo item, String type, boolean isHf, String odds, int oid, String sc, boolean hasPar) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, oid, type, odds, v, isHf, sc, hasPar);
                baseRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void clickView(View v, BallInfo item, int position) {
                switch (v.getId()) {
                    case R.id.module_match_collection_tv:
                        collectionItemCommon(item);
                        break;
                    case R.id.module_League_collection_tv:
                        collectionLeagueCommon(item);
                        break;
                    case R.id.module_right_mark_tv:

                        boolean visible = (checkLivePlayVisible(item) || checkWebRtsVisible(item));

                        if (getStateType().getType().toLowerCase().startsWith("r") && visible /*&& ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).hasBet*/) {
                            ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).clickRunMatchPlay(item, position, false);
                        } else {
                            if (StringUtils.isNull(getAdapterHelper().additionMap.get(true)) && !((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).hasBet) {
                                PopOneBtn popOneBtn = new PopOneBtn(getBaseView().getIBaseContext().getBaseActivity(), v) {
                                    @Override
                                    protected void initView(@NotNull View view) {
                                        super.initView(view);
                                        chooseMessage.setText(R.string.placing_a_bet);
                                    }
                                    @Override
                                    protected int onSetLayoutRes() {
                                        return R.layout.popupwindow_base_one_btn;

                                    }
                                    @Override
                                    protected void clickSure(@Nullable View v) {
                                        super.clickSure(v);
                                        getBaseView().clickItemAdd(v, item, position);
                                    }
                                };
                                popOneBtn.showPopupCenterWindow();

                            }else {
                                getBaseView().clickItemAdd(v, item, position);
                            }
                        }
                        break;
                    case R.id.iv_hall_btn:
                        clickHallBtn(v, item, position);
                        break;
                }

            }

        };
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new BasketballCommonBetHelper(getBaseView());
    }



    //http://a8197c.a36588.com/_Bet/JRecPanel.aspx?gt=s&b=under&oId=12159615&oId_fh=12159616&isFH=true&isRun=true&odds=4.70


    protected IAdapterHelper<BallInfo> onSetCommonAdapterHelper() {
        return new BasketballCommonAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }


    public boolean isItemCollectionCommon(BallInfo item) {

        return !(localCollectionMap.get(item.getModuleTitle()) == null || localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()));
    }


}
