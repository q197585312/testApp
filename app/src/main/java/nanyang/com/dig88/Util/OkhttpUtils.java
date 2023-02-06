package nanyang.com.dig88.Util;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/29.
 */

public class OkhttpUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstans() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }

    public static void getRequest(String url, final Result result) {
        Request request = new Request.Builder().url(url).build();
        getInstans().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        result.onFailed(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        result.onSuccess(s);
                    }
                });
            }
        });
    }

    public static void postRequest(String url, RequestBody params, final Result result) {
        final Request request = new Request.Builder().url(url).post(params).build();
        getInstans().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        result.onFailed(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        result.onSuccess(s);
                    }
                });
            }
        });
    }

    public interface Result {
        void onSuccess(String result);

        void onFailed(String result);
    }
}
