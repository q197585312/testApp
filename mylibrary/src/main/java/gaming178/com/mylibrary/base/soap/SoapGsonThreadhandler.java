package gaming178.com.mylibrary.base.soap;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import gaming178.com.mylibrary.base.PageThreadhandler;

/**
 * Created by Administrator on 2015/9/2.
 */
public abstract class SoapGsonThreadhandler<T> extends PageThreadhandler<SoapRequestBean, T, String> {
    Context mContext;
    AsyncTask<SoapRequestBean, Void, WebServiceBean> thread;

    public SoapGsonThreadhandler(Context context) {
        super(context);
        this.mContext = context;
    }



    @Override
    protected void handlePageHttp(final SoapRequestBean bean) {

        thread = new AsyncTask<SoapRequestBean, Void, WebServiceBean>() {
            @Override
            protected WebServiceBean doInBackground(SoapRequestBean... params) {
                SoapRequestBean param = params[0];
                StringBuilder builder = new StringBuilder();
                builder.append("MethodName:" + param.getMethodName() + "&");
                builder.append("Params:[");
                for (SoapParamsBean item : param.getParams()) {
                    builder.append(item.getKey() + ":" + item.getValue() + ",");
                }
                builder.append("]");
                Log.v("SoapHttp", builder.toString());
                return WebServiceUtil.getSoapData(param);

            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(WebServiceBean wb) {
                super.onPostExecute(wb);
                Log.v("SoapHttp","code:"+wb.getCode() + "," +"message:"+ wb.getMessage());
                if (wb.getCode() == 1) {
                    Type t = bean.getResponeType();
                    T respone = (new Gson()).fromJson(wb.getMessage(), t);
                    successEnd(respone);
                } else {
                    errorEnd(wb.getMessage());
                }
            }
        };
        thread.execute(bean);

    }

    public void cancle() {
        WebServiceUtil.cancleCall();
        if (thread != null)
            thread.cancel(true);
    }

    @Override
    public void errorEnd(String obj) {
        super.errorEnd(obj);
        if (obj != null)
            Toast.makeText(mContext, obj, Toast.LENGTH_LONG);
    }

}
