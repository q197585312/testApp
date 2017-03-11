package com.nanyang.app.main.center;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/11.
 */

public class StatementFragment extends BaseFragment<StatementContact.Presenter> implements StatementContact.View {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter(new StatementPresenter(this));
    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_statement;
    }

    @Override
    public void initData() {
        super.initData();
        presenter.init("mb", "demoafbai4");
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void onGetData(String data) {
        String result = Html.fromHtml(data).toString();
        StringBuilder sb = new StringBuilder();
        int size = result.length();
        for (int i = 0; i < size; i++) {
            String index = result.charAt(i) + "";
            if (!index.equals("\t") && !TextUtils.isEmpty(index)) {
                sb.append(index);
            }
        }
        sb.toString();
        Log.d("sb", "onGetData: " + sb);
    }

    @Override
    public void onFailed(String error) {

    }
}
