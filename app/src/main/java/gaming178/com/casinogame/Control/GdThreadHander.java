package gaming178.com.casinogame.Control;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import gaming178.com.casinogame.Util.HttpClient;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.mylibrary.allinone.util.ThreadPoolUtils;
import gaming178.com.mylibrary.base.RequestBean;
import gaming178.com.mylibrary.base.ThreadHandler;

/**
 * Created by Administrator on 2016/8/10.
 */
public abstract class GdThreadHander extends ThreadHandler<String, RequestBean<String>, String, String> {
    private static final int ErrorCode=-1;
    private static final int SuccessCode=1;
    private static final int NetErrorCode=400;
    Handler  handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ErrorCode:
                    errorEnd((String) msg.obj);
                    break;
                case SuccessCode:
                    successEnd((String) msg.obj);
                    break;
                case NetErrorCode:
                    errorEnd("Net Error");
                    break;

            }
        }
    };
    private HttpClient client;


    public GdThreadHander(Context context) {
        super(context);
        client=new HttpClient();
    }

    /*  String loginParams = "txtLang=0&txtAcctid="+mAppViewModel.getUser().getName()+"&txtPwd="+mAppViewModel.getUser().getPassword()+"&OsType=Android&OsVersion="+localVersion;*/
    @Override
    protected void handleHttp(final RequestBean<String> bean, String obj) {

        Runnable thread=new Runnable() {
            @Override
            public void run() {
                try {
                    String strRes = client.sendPost(bean.getUrl(), bean.getParams());

                    Log.i(WebSiteUrl.Tag, "UpdateWonMoney params= " + bean.getParams());
                    Log.i(WebSiteUrl.Tag, "UpdateWonMoney = " + strRes);

                    if (strRes.startsWith("Results=ok")) {
                        handler.obtainMessage(SuccessCode, strRes).sendToTarget();
                    } else if(strRes.startsWith("Results=error")){
                        handler.obtainMessage(ErrorCode, strRes).sendToTarget();
                    }
                }catch (Exception e){
                    handler.obtainMessage(NetErrorCode, e.getMessage()).sendToTarget();
                }
            }
        };
        ThreadPoolUtils.execute(thread);

    }
}
