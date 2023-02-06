package nanyang.com.dig88.Table.utils;

import android.content.Context;
import android.view.View;

import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.BettingInfoBean;
import nanyang.com.dig88.Table.entity.BettingPromptBean;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/11/30.
 */
public class BettingDataHelper {
    Context mContext;
    ThreadEndT mEndt;
    private TableHttpHelper helper;

    /*oid	全场id
    isRun	是否滚球
    odds	赔率
    Oid_fh	半场id
    isFH	是否半场
    http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?gt=s&g=5&b=1&oId=9073205&odds=2.02
    */
    public BettingDataHelper(Context mContext, View v, ThreadEndT mEndt) {
        this.mContext = mContext;
        this.mEndt = mEndt;
        /*http://mobilesport.dig88api.com/_view/MoreBet_App.aspx?oId=9112330&home=Sydney FC&away=Newcastle Jets&moduleTitle=AUSTRALIA HYUNDAI A LEAGUE&date=04/12 04:40PM&isRun=False&t=1448874349831*/

        helper=new TableHttpHelper<BettingPromptBean>(mContext, v, mEndt);
    }
    public void getData(BettingInfoBean info){
       StringBuilder builder=new StringBuilder();
        //"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?"
        builder.append(WebSiteUrl.SporSoccerGameBet);
        if(info.getGt()!=null&&!info.getGt().equals(""))
            builder.append("gt="+info.getGt());
        if(info.getB().equals("1")||info.getB().equals("X")||info.getB().equals("2")||info.getB().equals("odd")||info.getB().equals("even"))
            builder.append("&g=5");
        else if(info.getB().equals("X_par")||info.getB().equals("2_par")||info.getB().equals("1_par")||info.getB().equals("under_par")||info.getB().equals("over_par")||info.getB().equals("home_par")||
                info.getB().equals("away_par")||info.getB().equals("odd_par")||info.getB().equals("even_par"))
            builder.append("&g=2");
        builder.append("&b="+info.getB());
        if(info.getSc()!=null&&!info.getSc().equals(""))
            builder.append("&sc="+info.getSc());
        builder.append("&oId="+info.getSocOddsId());
        builder.append("&odds="+info.getOdds());
        if(info.isRunning())
            builder.append("&isRun=true");
        if(info.getIsFH()==1&&info.getSocOddsId_FH()!=null&& !info.getSocOddsId_FH().equals(""))
            builder.append("&isFH=true&oId_fh="+info.getSocOddsId_FH());
        helper.getData(builder.toString(), "", TableDataHelper.ModelType.Default);
    }
    public void updateData(String s,String params){
        helper.updateData(s, params,true,0);
    }

}
