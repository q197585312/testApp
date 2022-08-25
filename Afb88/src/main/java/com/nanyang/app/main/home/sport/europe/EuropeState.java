package com.nanyang.app.main.home.sport.europe;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.e_sport.ESportRunningState;
import com.nanyang.app.main.home.sport.e_sport.ESportTodayState;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */
public abstract class EuropeState extends BallState {
    public EuropeState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new EuropeCommonAdapter(getBaseView().getIBaseContext().getBaseActivity());
    }


    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new ESportTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new ESportRunningState(getBaseView()));
                break;
        }
    }



    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new EuropeBetHelper(getBaseView());
    }


    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean collection() {
        return false;
    }



    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<BallInfo>(baseRecyclerAdapter) {
            @Override
            public BallInfo getItem(int position) {
                return baseRecyclerAdapter.getItem(position);
            }


            @Override
            public void clickOdds(TextView v, BallInfo item, String type, boolean isHf, String odds, int oid, String sc, boolean hasPar) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, oid, type, odds, v, isHf, sc, hasPar);

            }
            @Override
            public boolean isItemCollection(BallInfo item) {
                return isItemCollectionCommon(item);
            }

            @Override
            public boolean isLeagueCollection(BallInfo item) {
                return isLeagueCollectionCommon(item);
            }
            @Override
            public void clickView(View v, final BallInfo item, int position) {
                switch (v.getId()) {
                    case R.id.module_right_mark_tv:
                        boolean visible = (checkLivePlayVisible(item) || checkWebRtsVisible(item));
                        if (getStateType().getType().toLowerCase().startsWith("r") && visible && ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).hasBet) {
                            ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).clickRunMatchPlay(item, position, false);
                        } else {
                           /* if (StringUtils.isNull(getAdapterHelper().additionMap.get(true)) && !((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).hasBet) {
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
                            }*/
                            getBaseView().clickItemAdd(v, item, position);
                        }
                        break;
                    case R.id.iv_hall_btn:
                        clickHallBtn(v, item, position);
                        break;
                }
            }

        };
    }


    public String getParentText() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Europe_View);
    }


}
