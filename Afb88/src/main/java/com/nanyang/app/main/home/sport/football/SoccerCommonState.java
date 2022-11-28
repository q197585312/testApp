package com.nanyang.app.main.home.sport.football;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.europe.BallState;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class SoccerCommonState extends BallState {
    public SoccerCommonState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        SoccerCommonAdapterHelper adapterHelper = onSetCommonAdapterHelper();
        return adapterHelper;
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<BallInfo>(baseRecyclerAdapter) {
            @Override
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
//                (B itemData, int oid, String type, String odds, TextView v, boolean isHf, String params ,boolean hasPar);
                helper.clickOdds(item, oid, type, odds, v, isHf, sc, hasPar);
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
                     /*   boolean visible = (checkLivePlayVisible(item) || checkWebRtsVisible(item));

                        boolean hasBet = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).hasBet;
//                        boolean hasBet =true;
                        if (getStateType().getType().toLowerCase().startsWith("r") && visible && hasBet) {
                            ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).clickRunMatchPlay(item, position, false);
                        } else {
                            String s = getAdapterHelper().additionMap.get(true);
                            if (StringUtils.isNull(s) && !hasBet) {
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

                            } else {
                                getBaseView().clickItemAdd(v, item, position);
                            }

                        }*/
                        getBaseView().clickItemAdd(v, item, position);
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
        return new SoccerCommonBetHelper(getBaseView());
    }

    protected abstract SoccerCommonAdapterHelper onSetCommonAdapterHelper();

}
