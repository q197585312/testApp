package com.nanyang.app.main.center.changeLanguage;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.main.MainActivity;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/28.
 */

public class ChangeLanguageFragment extends BaseFragment<LanguagePresenter> implements ILanguageView<String> {
    @Bind(R.id.rc)
    RecyclerView rc;

    @Override
    public int onSetLayoutId() {
        return R.layout.frgment_change_language;
    }

    @Override
    public void initView() {
        super.initView();
    }

    String language;

    @Override
    public void initData() {
        super.initData();
        String lag = AfbUtils.getLanguage(getActivity());
        if (lag.equals("zh")) {
            language = getString(R.string.chinese);
        } else if (lag.equals("en")) {
            language = getString(R.string.english);
        }
        initRc();
        createPresenter(new LanguagePresenter(this));
    }

    private void initRc() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        rc.setLayoutManager(mLayoutManager);
        List<String> dataList = new ArrayList<>();
        dataList.add(getString(R.string.chinese));
        dataList.add(getString(R.string.english));

        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<String>(mContext, dataList, R.layout.item_change_language) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv = holder.getView(R.id.selectable_text_content_tv);
                tv.setText(item);
                if (item.equals(getString(R.string.chinese))) {
                    if (language.equals(item)) {
                        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lang_zh_flag, 0, R.mipmap.oval_red_hook_red_choice, 0);
                    } else {
                        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lang_zh_flag, 0, 0, 0);
                    }
                } else if (item.equals(getString(R.string.english))) {
                    if (language.equals(item)) {
                        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lang_en_flag, 0, R.mipmap.oval_red_hook_red_choice, 0);
                    } else {
                        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lang_en_flag, 0, 0, 0);
                    }
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                if (item.equals(getString(R.string.chinese))) {
                    AfbUtils.switchLanguage("zh", getActivity());
                    String lang = "ZH-CN";
                    presenter.switchLanguage(lang);

                } else if (item.equals(getString(R.string.english))) {
                    AfbUtils.switchLanguage("en", getActivity());
                    String lang = "EN-US";
                    presenter.switchLanguage(lang);

                }
            }
        });
        rc.setAdapter(adapter);
    }

    @Override
    public void onGetData(String data) {

    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onLanguageSwitchSucceed(String str) {
        Intent intent = new Intent(getActivity(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
