package com.nanyang.app.main.home.sport.dialog;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2015/10/27.
 */
public class ChooseLanguagePop extends BasePopupWindow {


    @Bind(R.id.base_rv)
    RecyclerView baseRv;

    SportActivity context;

    public ChooseLanguagePop(SportActivity context, View v) {
        this(context, v, 400, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public ChooseLanguagePop(SportActivity mContext, View v, int width, int height) {
        super(mContext, v, width, height);
        this.context = mContext;
        this.v = v;
        initData();

    }

    private void initData() {
        List<MenuItemInfo> menuItemInfos = Arrays.asList(new MenuItemInfo(R.mipmap.lang_zh_flag, "中文", "zh"), new MenuItemInfo(R.mipmap.lang_en_flag, "English", "en"));

        BaseRecyclerAdapter<MenuItemInfo> adapter = new BaseRecyclerAdapter<MenuItemInfo>(context, menuItemInfos, R.layout.selected_text_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView contentTv = holder.getView(R.id.selectable_text_content_tv);
                View  line = holder.getView(R.id.v_row_line1);
                line.setVisibility(View.GONE);
                contentTv.setText(item.getText());
                contentTv.setTextColor(context.getColor(R.color.white));
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
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                String lag = item.getType();
                AfbUtils.switchLanguage(lag, context);
                if (lag.equals("zh")) {
                    switchLanguage("ZH-CN");
                } else if (lag.equals("en")) {
                    switchLanguage("EN-US");
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
        Disposable d = Api.getService(ApiService.class).switchLanguage(AppConstant.URL_CHANGE_LANGUAGE, lang).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        context.hideLoadingDialog();
                        context.recreate();
                        closePopupWindow();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        context.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        context.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        context.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);

                    }
                });

    }

}
