package nanyang.com.dig88.Lottery4D;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/11/16.
 */

public class PrizeFragment extends Lottery4DBaseFragment {
    String url;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_lottery_4d_prize;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        webView = (WebView) rootView.findViewById(R.id.web_wv);
        initWebView();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        url = "http://m.855kg.com/index.php?page=4d_lott_prize&from=app&" +
                "web_id=" + WebSiteUrl.WebId + "&user_id=" + userId + "&session_id=" + sessionId + "&lang=" + getLang();
        webView.loadUrl(url);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            webView.loadUrl(url);
        }
    }
}
