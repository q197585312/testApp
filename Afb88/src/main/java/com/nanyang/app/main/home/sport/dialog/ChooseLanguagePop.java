package com.nanyang.app.main.home.sport.dialog;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2015/10/27.
 */
public class ChooseLanguagePop extends BasePopupWindow {


    private final LanguagePresenter presenter;
    @Bind(R.id.base_rv)
    RecyclerView baseRv;

    SportActivity context;

    public ChooseLanguagePop(SportActivity context, View v, LanguagePresenter presenter) {
        this(context, v, 400, LinearLayout.LayoutParams.WRAP_CONTENT,presenter);
    }

    public ChooseLanguagePop(SportActivity mContext, View v, int width, int height,LanguagePresenter presenter) {
        super(mContext, v, width, height);
        this.context = mContext;
        this.v = v;
        this.presenter=presenter;
        initData();

    }

    private void initData() {
        List<MenuItemInfo<String>> menuItemInfos = new ArrayList<MenuItemInfo<String>>(Arrays.asList(new MenuItemInfo<String>(R.mipmap.lang_zh_flag, "中文", "zh","ZH-CN"), new MenuItemInfo<String>(R.mipmap.lang_en_flag, "English", "en","EN-US"),
                new MenuItemInfo<String>(R.mipmap.lang_th_flag, "ไทย", "th","TH-TH"),
                new MenuItemInfo<String>(R.mipmap.lang_ko_flag, "한국의", "ko","EN-TT"),
                new MenuItemInfo<String>(R.mipmap.lang_vi_flag, "tiếng việt", "vi","EN-IE"),
                new MenuItemInfo<String>(R.mipmap.lang_tr_flag, "Türk dili", "tr","UR-PK")
        )
        );

        BaseRecyclerAdapter<MenuItemInfo<String>> adapter = new BaseRecyclerAdapter<MenuItemInfo<String>>(context, menuItemInfos, R.layout.selected_text_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                TextView contentTv = holder.getView(R.id.selectable_text_content_tv);
                View  line = holder.getView(R.id.v_row_line1);
                line.setVisibility(View.GONE);
                contentTv.setText(item.getText());
                contentTv.setTextColor(context.getResources().getColor(R.color.white));
                contentTv.setPadding(10, 40, 30, 40);
                contentTv.setTextSize(12);
                String language = AfbUtils.getLanguage(mContext);
                if (language == null || language.equals("")) {
                    language = "zh";
                }
                if (language.equals(item.getType())) {
                    contentTv.setCompoundDrawablesWithIntrinsicBounds(item.getRes(), 0, R.mipmap.menu_right_hover, 0);
                } else {
                    contentTv.setCompoundDrawablesWithIntrinsicBounds(item.getRes(), 0, 0, 0);
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo<String>>() {
            @Override
            public void onItemClick(View view, MenuItemInfo<String> item, int position) {
                String lag = item.getType();

                if(!lag.equals(AfbUtils.getLanguage(context))){
                    AfbUtils.switchLanguage(lag,context);
                    if (tv!=null){
                        tv.setBackgroundResource(item.getRes());
                    }
                    switchLanguage(item.getParent());
                }
            }
        });
        baseRv.setLayoutManager(new LinearLayoutManager(context));
        baseRv.setAdapter(adapter);
    }


    @Override
    protected int onSetLayoutRes() {
        return R.layout.popupwindow_list_language_select;
    }


    @Override
    protected void onClose() {
        super.onClose();
        context.hideLoadingDialog();
    }

    public void switchLanguage(String lang) {
        presenter.switchLanguage(lang);
    }
    TextView tv;
    public void setShowTv(TextView tv){
        this.tv = tv;
    }

}
