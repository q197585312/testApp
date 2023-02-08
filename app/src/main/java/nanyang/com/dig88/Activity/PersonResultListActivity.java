package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Adapter.PersonGameResultListAdapter;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.DigGameResultBean;
import nanyang.com.dig88.Entity.LiveGameBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2016/1/4.
 */
public class PersonResultListActivity extends BaseActivity{
    public static final int SHOW_RESULTS = 3;

  /*  NyVolleyJsonThreadHandler<List<DigGameResultBean>> listgamedata;
    public  List<DigGameResultBean> diggamelist=new ArrayList<DigGameResultBean>();
    String regularEx = ",";
    String[] str = null; //游戏名字
    PersonGameResultListAdapter digGameResultAdapter;
    SharedPreferences sharedPreferences1;
    public static int personbiaozhi;*/
    public  List<DigGameResultBean> gameResultsList=new ArrayList<DigGameResultBean>();
    public PersonGameResultListAdapter gameResultsAdapter;
    public String gameResults="";
    @BindView(R.id.list_result)
    ListView lv_result;
    @BindView(R.id.tv_nodata)
    TextView tv_nodata;
    boolean bTimerResults = true;
    boolean bGetResults = true;
    //////////lanjian fixed
    private String gameName = "";
    private LiveGameBean digGameBean = null;
    private String titleName = "";
    private String NumberGameResultsParams = "";
    private String NumberGameResultsMessage ="";
    private String gameNumber="";
    //线程，拿结果
    private UpdateResults updateResults = null;
    private Thread threadResults = null;
    private Handler handlerTimer = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if(!isAttached)
                return;
            switch(msg.what)
            {

                case SHOW_RESULTS:
                    if (gameResultsAdapter == null ){
                        gameResultsAdapter=new PersonGameResultListAdapter(PersonResultListActivity.this,gameResultsList);
                        lv_result.setAdapter(gameResultsAdapter);
                        gameResultsAdapter.setGameName(gameName);
                    //    android.util.Log.i("LanjianTest", "列表=====" + gameResultsList.get(0).getResult());
                    }else{
                        gameResultsAdapter.notifyDataSetChanged();
                    }
                    if (gameResultsList.size()==0){
                        lv_result.setVisibility(View.GONE);
                        tv_nodata.setVisibility(View.VISIBLE);
                    }
                    break;

            }
            //

        }
    };

    public void StartUpdateGameStatus()
    {

        bTimerResults = true;
        if(updateResults == null)
        {
            updateResults = new UpdateResults();
            threadResults = new Thread(updateResults);
            threadResults.start();
        }


    }
    public void StopUpdateGameStatus()
    {

        bTimerResults = false;
        if(updateResults != null)
            updateResults = null;
        if(threadResults != null)
            threadResults = null;

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(mContext, "");
        tv_nodata.setText(getString(R.string.nodata));
        //数字游戏标志

        gameName = getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_DATA);

        switch(gameName)
        {
            case "42_Balls":

                digGameBean = getApp().getLivegame_42balls();
                titleName = 42+" "+getString(R.string.qiu)+" "+getString(R.string.jieguolist);
                break;
            case "36_Balls":

                digGameBean = getApp().getLivegame_36balls();
                titleName = 36+" "+getString(R.string.qiu)+" "+getString(R.string.jieguolist);
                break;
            case "48_Balls":

                digGameBean = getApp().getLivegame_48balls();
                titleName = 48+" "+getString(R.string.qiu)+" "+getString(R.string.jieguolist);
                break;
            case "12_Balls":

                digGameBean = getApp().getLivegame_12balls();
                titleName = 12+" "+getString(R.string.qiu)+" "+getString(R.string.jieguolist);
                break;
            case "Sicbo_Balls":

                digGameBean = getApp().getLivegame_Sicoballs();
                titleName = "Sicbo "+getString(R.string.qiu)+" "+getString(R.string.jieguolist);
                break;
            case "Roulette_Balls":

                digGameBean = getApp().getLivegame_Scollballs();
                titleName = "Roulette "+getString(R.string.qiu)+" "+getString(R.string.jieguolist);
                break;
            case "Multiple_36Balls":

                digGameBean = getApp().getLivegame_MULTIPLEballs();
                titleName = "Multiple 36"+getString(R.string.qiu)+" "+getString(R.string.jieguolist);
                break;
        }
        setTitle(titleName);
        NumberGameResultsParams = "web_id="+0 + "&user_id="+getApp().getUserInfo().getUser_id()+"&session_id="+getApp().getUserInfo().getSession_id()
                +"&type1="+7+"&type2=" + digGameBean.getGame_type();
        StartUpdateGameStatus();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "onPause()");
        StopUpdateGameStatus();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "onRestart()");
        getApp().setHttpClient(null);
        StartUpdateGameStatus();
        //初始化界面所有数据

        super.onRestart();
    }

    @Override
    protected int getLayoutRes()         {
        return R.layout.diggame_result_list_activity;
    }

    public void getResultsData()
    {
        try {
            HttpClient httpClient = getApp().getHttpClient();
            if(httpClient == null)
                return ;
            Log.i("LanjianTest","++"+NumberGameResultsParams);
            NumberGameResultsMessage = httpClient.sendPost(WebSiteUrl.GetNumberGameResults,NumberGameResultsParams);
            Log.i("LanjianTest","--"+NumberGameResultsMessage);
            Gson gson = new Gson();
            NyBaseResponse<List<DigGameResultBean>> orgData ;
            orgData = gson.fromJson(NumberGameResultsMessage, new TypeToken<NyBaseResponse<List<DigGameResultBean>>>() {
            }.getType());
            List<DigGameResultBean> data = orgData.getData();
            /////////
            if(!gameResults.equals(NumberGameResultsMessage)){
                gameResults = NumberGameResultsMessage;
                gameResultsList.clear();
                for (int i=0;i<data.size();i++){
                    gameResultsList.addAll(data);
                }
                handlerTimer.sendEmptyMessage(SHOW_RESULTS);
            }


            data = null;
            orgData = null;
            gson =  null;
            bGetResults = false;

            //  Log.i("LanjianTest",NumberGameStatusMessage);
        } catch (Exception e) {
            Log.i("LanjianTest",e.toString());
            e.printStackTrace();
        }
    }

    public class UpdateResults implements Runnable {
        public void run() {
            while (bTimerResults)
            {
                try {
                    Log.i("LanjianTest","UpdateResult="+digGameBean.getCount_down());
                    //局数不同，并且开奖的时候才能去拿结果

                    getResultsData();//拿结果


                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }


}
