package gaming178.com.casinogame.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.entity.SeatBean;
import gaming178.com.casinogame.Activity.entity.TableStatusBean;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2016/4/1.
 */
public class SelectSeatActivity extends BaseActivity {
    @Bind(R.id.gridview_content_gv)
    GridView gridviewContentGv;
    private AdapterViewContent<TableStatusBean> adapterViewContent;
    private int tableId=0;
    private String strSeat = "";
    private int areaId;
    private int serialId;

    private Thread threadStatus = null;
    private Thread threadChooseSeat = null;
    private ChooseSeat chooseSeat = null;
    private boolean bGetStatus = true;
    private TableStatusBean tableStatusBean1;
    private TableStatusBean tableStatusBean2;
    private TableStatusBean tableStatusBean3;
    private TableStatusBean tableStatusBean5;
    private TableStatusBean tableStatusBean6;
    private TableStatusBean tableStatusBean7;
    private TableStatusBean tableStatusBean8;
    private TableStatusBean tableStatusBean9;
    private TableStatusBean tableStatusBean10;
    private TableStatusBean tableStatusBean11;
    private TableStatusBean tableStatusBean12;
    private TableStatusBean tableStatusBean13;
    private List listData = new ArrayList<TableStatusBean>();


    public class ChooseSeat implements Runnable {
        private int serialId;
        private int areaId;

        public int getSerialId() {
            return serialId;
        }

        public void setSerialId(int serialId) {
            this.serialId = serialId;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public void run() {

                try {
                    Log.i(WebSiteUrl.Tag,"----------"+"GameType=11&Serialid="+serialId+"&Areaid="+areaId+"&Tbid="+tableId+"&Usid="+afbApp.getUser().getName());
                    String strRes = afbApp.getHttpClient().sendPost(WebSiteUrl.BJL_TABLE_HALL_CHOOSE_SEAT_URL, "GameType=11&Serialid="+serialId+"&Areaid="+areaId+"&Tbid="+tableId+"&Usid="+afbApp.getUser().getName());


                    if(strRes != null && strRes.startsWith("Results=ok")){
                        handler.sendEmptyMessage(HandlerCode.CHOOSE_SEAT_SUCESS);
                        Log.i(WebSiteUrl.Tag,"T----------"+strRes);
                    }else{
                     //   Log.i(WebSiteUrl.Tag,"----------"+tableId+",SerialId="+serialId+",AreaId="+areaId);
                        Log.i(WebSiteUrl.Tag,"F----------"+strRes);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }


        }

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {


            switch(msg.what)
            {
                case HandlerCode.UPDATE_STATUS:
                 //   adapterViewContent.setData(listData);
                    adapterViewContent.notifyDataSetChanged();

                    break;
                case HandlerCode.CHOOSE_SEAT_SUCESS:
                    afbApp.setTableId(tableId);
                    afbApp.setSerialId(serialId);
                    afbApp.setAreaId(areaId);
                    afbApp.setbLobby(false);

                    Bundle bundle=new Bundle();
                    bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA,""+tableId);
                    bundle.putInt("serialId",serialId1);
                    bundle.putInt("areaId",areaId1);
                    if (tableId<=3||tableId==71){
                        bundle.putBoolean("baccaratA",true);
                    }else {
                        bundle.putBoolean("baccaratA",false);
                    }
                    AppTool.activiyJump(mContext,BaccaratActivity.class,bundle);
                    finish();
                    break;

            }
            //

        }
    };
    public void InitAllSeat()
    {
      //  adapterViewContent.getItem(0).getSeatBeans()
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tableId = Integer.parseInt(getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_DATA));
        String tableNumber=getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_STRING);
        if(tableNumber!=null) {
            titleTv.setVisibility(View.VISIBLE);
            if (WebSiteUrl.GameType==3){
                titleTv.setGravity(Gravity.LEFT);
                llCenter.setVisibility(View.VISIBLE);

            }
            titleTv.setText(tableNumber);
            titleTv.setTextColor(getResources().getColor(R.color.white));
        }
        tableStatusBean1 = new TableStatusBean(1,"VIP1");
        tableStatusBean2 = new TableStatusBean(2,"VIP2");
        tableStatusBean3 = new TableStatusBean(3,"VIP3");
        tableStatusBean5 = new TableStatusBean(5,"VIP5");
        tableStatusBean6 = new TableStatusBean(6,"VIP6");
        tableStatusBean7 = new TableStatusBean(7,"VIP7");
        tableStatusBean8 = new TableStatusBean(8,"VIP8");
        tableStatusBean9 = new TableStatusBean(9,"VIP9");
        tableStatusBean10 = new TableStatusBean(10,"VIP10");
        tableStatusBean11 = new TableStatusBean(11,"VIP11");
        tableStatusBean12 = new TableStatusBean(12,"VIP12");
        tableStatusBean13 = new TableStatusBean(13,"VIP13");
        initOrientation();
        adapterViewContent = new AdapterViewContent<>(mContext, gridviewContentGv);
        adapterViewContent.setBaseAdapter(new QuickAdapterImp<TableStatusBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_table_select;
            }
            @Override
            public void convert(final ViewHolder helper, final TableStatusBean item, int position) {
                helper.setText(R.id.table_seat_vip,item.getVip());
                helper.setVisibility(R.id.table_seat_tv1,View.GONE);
                helper.setVisibility(R.id.table_seat_tv2,View.GONE);
                helper.setVisibility(R.id.table_seat_tv3,View.GONE);
                helper.setVisibility(R.id.table_seat_tv5,View.GONE);
                helper.setVisibility(R.id.table_seat_tv6,View.GONE);
                helper.setVisibility(R.id.table_seat_tv7,View.GONE);
                helper.setVisibility(R.id.table_seat_tv8,View.GONE);

                for (int i =0 ;i<item.getSeatBeans().size();i++){
                    SeatBean seatBean = item.getSeatBeans().get(i);
                    switch (seatBean.getAreaId())
                    {
                        case 1:
                            helper.setText(R.id.table_seat_tv1,seatBean.getName());
                            helper.setVisibility(R.id.table_seat_tv1,View.VISIBLE);
                            break;
                        case 2:
                            helper.setText(R.id.table_seat_tv2,seatBean.getName());
                            helper.setVisibility(R.id.table_seat_tv2,View.VISIBLE);
                            break;
                        case 3:
                            helper.setText(R.id.table_seat_tv3,seatBean.getName());
                            helper.setVisibility(R.id.table_seat_tv3,View.VISIBLE);
                            break;
                        case 5:
                            helper.setText(R.id.table_seat_tv5,seatBean.getName());
                            helper.setVisibility(R.id.table_seat_tv5,View.VISIBLE);
                            break;
                        case 6:
                            helper.setText(R.id.table_seat_tv6,seatBean.getName());
                            helper.setVisibility(R.id.table_seat_tv6,View.VISIBLE);
                            break;
                        case 7:
                            helper.setText(R.id.table_seat_tv7,seatBean.getName());
                            helper.setVisibility(R.id.table_seat_tv7,View.VISIBLE);
                            break;
                        case 8:
                            helper.setText(R.id.table_seat_tv8,seatBean.getName());
                            helper.setVisibility(R.id.table_seat_tv8,View.VISIBLE);
                            break;
                    }
                }
                helper.setClickLisenter(R.id.table_fl_1, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chooseUserSeat(item.getAreaId(),1,helper.getView(),R.id.table_seat_tv1);
                        areaId1 = item.getAreaId();
                        serialId1 = 1;
                    }
                });
                helper.setClickLisenter(R.id.table_fl_2, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chooseUserSeat(item.getAreaId(),2,helper.getView(),R.id.table_seat_tv2);
                        areaId1 = item.getAreaId();
                        serialId1 = 2;
                    }
                });
                helper.setClickLisenter(R.id.table_fl_3, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chooseUserSeat(item.getAreaId(),3,helper.getView(),R.id.table_seat_tv3);
                        areaId1 = item.getAreaId();
                        serialId1 = 3;
                    }
                });
                helper.setClickLisenter(R.id.table_fl_5, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chooseUserSeat(item.getAreaId(),5,helper.getView(),R.id.table_seat_tv5);
                        areaId1 = item.getAreaId();
                        serialId1 = 5;
                    }
                });
                helper.setClickLisenter(R.id.table_fl_6, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chooseUserSeat(item.getAreaId(),6,helper.getView(),R.id.table_seat_tv6);
                        areaId1 = item.getAreaId();
                        serialId1 = 6;
                    }
                });
                helper.setClickLisenter(R.id.table_fl_7, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                          chooseUserSeat(item.getAreaId(),7,helper.getView(),R.id.table_seat_tv7);
                        areaId1 = item.getAreaId();
                        serialId1 = 7;
                    }
                });
                helper.setClickLisenter(R.id.table_fl_8, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                          chooseUserSeat(item.getAreaId(),8,helper.getView(),R.id.table_seat_tv8);
                        areaId1 = item.getAreaId();
                        serialId1 = 8;
                    }
                });

            }
        });
      //  adapterViewContent.setData(Collections.nCopies(8,new TableStatusBean(1)));

        listData.add(tableStatusBean1);
        listData.add(tableStatusBean2);
        listData.add(tableStatusBean3);
        listData.add(tableStatusBean5);
        listData.add(tableStatusBean6);
        listData.add(tableStatusBean7);
        listData.add(tableStatusBean8);
        listData.add(tableStatusBean9);
        listData.add(tableStatusBean10);
        listData.add(tableStatusBean11);
        listData.add(tableStatusBean12);
        listData.add(tableStatusBean13);
        adapterViewContent.setData(listData);
        setToolbarNameAndBalance();
//        adapterViewContent.setItemClick(new ItemCLickImp<TableStatusBean>() {
//            @Override
//            public void itemCLick(View view, TableStatusBean tableStatusBean, int position) {
//                afbApp.setTableId(tableId);
//                afbApp.setSerialId(0);
//                afbApp.setAreaId(tableStatusBean.getAreaId());
//                afbApp.setbLobby(false);
//
//                Bundle bundle=new Bundle();
//                bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA,""+tableId);
//                AppTool.activiyJump(mContext,BaccaratActivity.class,bundle);
//                chooseUserSeat(tableStatusBean.getAreaId(),0,view,R.id.table_seat_tv7);
//                AppTool.activiyJump(mContext,BaccaratActivity.class);
//            }
//        });
    }
    private int serialId1;
    private int areaId1;
    public void chooseUserSeat(int areaId,int serialId,View view,int rId)
    {
        if(View.VISIBLE == view.findViewById(rId).getVisibility())
            return;
        this.serialId = serialId;
        this.areaId = areaId;
        chooseSeat  = new ChooseSeat();
        chooseSeat.setSerialId(serialId);
        chooseSeat.setAreaId(areaId);
        threadChooseSeat = new Thread(chooseSeat);
        threadChooseSeat.start();
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_select_table;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initOrientation();
    }
    @Override
    protected void initOrientation() {
        super.initOrientation();
        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
        {
            gridviewContentGv.setNumColumns(2);

        }  else if(this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT)

        {
            gridviewContentGv.setNumColumns(1);

        }
    }
}
