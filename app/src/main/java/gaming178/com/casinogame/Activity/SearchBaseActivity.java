package gaming178.com.casinogame.Activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.base.BaseActivity;

public abstract class SearchBaseActivity extends BaseActivity {

    EditText edtSearch;
    ImageView imgClear;
    LinearLayout ll_parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return layout();
    }

    abstract int layout();

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initSearch();
    }

    abstract void input(Editable s);

    private void initSearch() {
        edtSearch = findViewById(R.id.edt_search);
        if (BuildConfig.FLAVOR.equals("hokicasino88") || BuildConfig.FLAVOR.equals("doacasino") || BuildConfig.FLAVOR.equals("ularnaga") ||
                BuildConfig.FLAVOR.equals("ratucasino88") || BuildConfig.FLAVOR.equals("depocasino")) {
            toolbar.setNavigationIcon(R.mipmap.search_back);
            toolbar.setBackgroundResource(R.mipmap.bg_search);
            ll_parent = findViewById(R.id.gd__ll_parent);
            if (BuildConfig.FLAVOR.equals("hokicasino88")) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ll_parent.setBackgroundResource(R.mipmap.gd_home_bottom_land);
                } else {
                    ll_parent.setBackgroundResource(R.mipmap.gd_home_bottom);
                }
            } else if (BuildConfig.FLAVOR.equals("ularnaga")) {
                toolbar.setBackgroundResource(R.drawable.ularnaga_top_bg);
                ll_parent.setBackgroundColor(Color.parseColor("#CD1012"));
                edtSearch.setHintTextColor(Color.parseColor("#707070"));
                edtSearch.setTextColor(Color.BLACK);
            } else if (BuildConfig.FLAVOR.equals("ratucasino88")) {
                toolbar.setBackgroundColor(Color.parseColor("#B40A08"));
                ll_parent.setBackgroundResource(R.mipmap.gd_login_bg);
                edtSearch.setHintTextColor(Color.parseColor("#707070"));
                edtSearch.setTextColor(Color.BLACK);
            } else if (BuildConfig.FLAVOR.equals("depocasino")) {
                toolbar.setBackgroundResource(R.drawable.ularnaga_top_bg);
                edtSearch.setHintTextColor(Color.parseColor("#707070"));
                edtSearch.setTextColor(Color.BLACK);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ll_parent.setBackgroundResource(R.mipmap.gd_home_bottom_land);
                } else {
                    ll_parent.setBackgroundResource(R.mipmap.gd_home_bottom);
                }
            }
        }
        imgClear = findViewById(R.id.img_clear);
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("");
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                input(s);
            }
        });
    }
    @Override
    protected void leftClick() {
        skipAct(LobbyActivity.class);
        finish();
    }
}
