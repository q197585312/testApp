package nanyang.com.dig88.Table.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import gaming178.com.mylibrary.popupwindow.BaseYseNoChoosePopupwindow;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.BallGameActivity;
import nanyang.com.dig88.Table.Imp.IClickListener;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.TableModuleBean;
import nanyang.com.dig88.Table.popupwindow.DigYesNoChoosePop;
import nanyang.com.dig88.Table.utils.TableAdapterHelper;
import nanyang.com.dig88.Table.utils.TableDataHelper;

/**
 * Created by Administrator on 2015/11/30.
 */
public abstract class TableBaseFragment extends BaseFragment  {


    public IClickListener IClickLisenter;
    protected String dataType;
    protected TableAdapterHelper adapterHelper;
    protected int tableErrorCount=0;
    private BaseYseNoChoosePopupwindow popYes;
    private TableDataHelper dataHelper;

    public TableAdapterHelper getAdapterHelper() {
        return adapterHelper;
    }

    public TableDataHelper getDataHelper() {
        return dataHelper;
    }

    public void getData(String params) {
        if(getAct()!=null)
            getAct().setParentEnable(false);
        getNetTableData(dataHelper,params);
        adapterHelper.setHasNewDate(true);

    }

    public  String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
        if(adapterHelper!=null)
            adapterHelper.setModleType(dataType);
    }

    public BallGameActivity getAct(){
        return (BallGameActivity)getActivity();
    }

    public abstract void notifyBetTypeChange();

    private void initTableData() {

        dataHelper = new TableDataHelper(getActivity(), 0, new ThreadEndT<ArrayList<TableModuleBean>>(new TypeToken<ArrayList<TableModuleBean>>() {
        }.getType()) {
            @Override
            public void endT(final ArrayList<TableModuleBean> result, final int model) {

                tableErrorCount=0;
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        endList(result);
                    }
                });
                if(getAct()!=null)
                    getAct().setParentEnable(true);

            }

            @Override
            public void endString(final String result,int model) {
                tableErrorCount=0;
                if(result!=null){
                    adapterHelper.setUpdateType(Integer.valueOf(result));
                }
            }

            @Override
            public void endError(String error) {
                if(error!=null&&error.contains("Object moved")){
                    tableErrorCount=5;

                }
                tableErrorCount++;
                if(tableErrorCount>5){
                    if(popYes==null||!popYes.isShowing()) {
                        popYes=new DigYesNoChoosePop(mContext,new View(mContext)) {
                            @Override
                            protected String getContentString() {
                                return "load data failed!    "+mContext.getString(R.string.do_you_want_to_load_main);
                            }
                            @Override
                            protected void clickSure(View v) {
                                getAct().finish();
                        }
                        };

                        popYes.showPopupCenterWindow();
                        tableErrorCount=0;
                    }
                }
                if(getAct()!=null)
                    getAct().setParentEnable(true);
            }

        });
        adapterHelper = new TableAdapterHelper(mContext, dataType, getListView(), dataHelper);
        initBallData();
    }

    protected abstract void initBallData();

    /*    protected void loginSport() {
            getAct().setDialog(new BlockDialog(mContext, getString(R.string.loading)));
            getAct().showBlockDialog();
            getApp().setCookie("");
            TableHttpHelper<String> loginHelper = new TableHttpHelper<String>(mContext, null, new ThreadEndT<String>(new TypeToken<String>() {
            }.getType()) {
                @Override
                public void endT(String result) {
                    BallGameInfoBean ball = new BallGameInfoBean();
                    ball.setIsBallGameLogin(true);
                    getApp().setBallGameInfo(ball);
                    getAct().dismissBlockDialog();
                    getData("");
                }

                @Override
                public void endString(String result) {
                    getAct().dismissBlockDialog();
                }

                @Override
                public void endError( String error) {
                    Toast.makeText(mContext,error,Toast.LENGTH_SHORT).show();
                    if(getAct()!=null)
                    getAct().dismissBlockDialog();
                }
            });
            loginHelper.login(((LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo")));

        }*/
    protected abstract ListView getListView();

    protected abstract void getNetTableData(TableDataHelper dataHelper,String params);

    protected abstract void endList(ArrayList<TableModuleBean> result);
    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initTableData();
    }

    public abstract void updateFromSelected();


    public  void setModelClickLisenter(IClickListener lisenter){
        this.IClickLisenter=lisenter;
    }
}

