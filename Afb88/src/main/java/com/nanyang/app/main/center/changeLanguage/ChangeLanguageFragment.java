package com.nanyang.app.main.center.changeLanguage;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.center.model.TransferMoneyBean;
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


    @Override
    public void initData() {
        super.initData();
        initRc();
        createPresenter(new LanguagePresenter(this));
    }

    private void initRc() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        rc.setLayoutManager(mLayoutManager);
        List<MenuItemInfo<String>> dataList = new ArrayList<>();
        dataList.add(new MenuItemInfo<>(R.mipmap.lang_zh_flag,getString(R.string.chinese),"zh", "ZH-CN"));
        dataList.add(new MenuItemInfo<>(R.mipmap.lang_en_flag,getString(R.string.english),"en", "EN-US"));
        dataList.add(new MenuItemInfo<>(R.mipmap.lang_th_flag,getString(R.string.thai),"th","TH-TH"));
        dataList.add(new MenuItemInfo<>(R.mipmap.lang_ko_flag,getString(R.string.Korea),"ko","EN-TT"));
        dataList.add(new MenuItemInfo<>(R.mipmap.lang_vi_flag,getString(R.string.Vietnam),"vi","EN-IE"));
        dataList.add(new MenuItemInfo<>(R.mipmap.lang_tr_flag,getString(R.string.Turkish),"tr","UR-PK"));
        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<MenuItemInfo<String>>(mContext, dataList, R.layout.item_change_language) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                String lag = AfbUtils.getLanguage(getActivity());
                TextView tv = holder.getView(R.id.selectable_text_content_tv);
                tv.setText(item.getText());
                if(lag.equals(item.getType())){
                    tv.setCompoundDrawablesWithIntrinsicBounds(item.getRes(), 0, R.mipmap.oval_red_hook_red_choice, 0);
                }else{
                    tv.setCompoundDrawablesWithIntrinsicBounds(item.getRes(), 0, 0, 0);
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo<String>>() {
            @Override
            public void onItemClick(View view, MenuItemInfo<String> item, int position) {
                AfbUtils.switchLanguage(item.getType(), getActivity());
                presenter.switchLanguage(item.getParent());
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

    @Override
    public void getMoneyMsg(TransferMoneyBean transferMoneyBean, String data) {

    }

    @Override
    public void onGetTransferMoneyData(int type, String getBackStr, String data) {

    }
}
