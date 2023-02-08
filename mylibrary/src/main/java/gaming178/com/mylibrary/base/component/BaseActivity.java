package gaming178.com.mylibrary.base.component;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;
import gaming178.com.mylibrary.R;
import gaming178.com.mylibrary.allinone.RequestUtils;
import gaming178.com.mylibrary.allinone.util.AppTool;


public abstract class BaseActivity extends com.unkonw.testapp.libs.base.BaseActivity {
    public Toolbar toolbar;
    public TextView titleTv;
    public Context mContext;
    public LinearLayout searchLl;
    public AutoCompleteTextView searchEdt;
    public Button searchSubmitBtn;
    public TextView rightTv;
    public TextView msgTv;
    public TextView leftImg;
    public TextView rightTv2;
    public boolean isAttached;
    public FrameLayout msgLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        if (getLayoutRes() == 0)
            setContentView(getLayoutView());
        else
            setContentView(getLayoutRes());
        mContext = this;
        ButterKnife.bind(this);
        initView();
        RequestUtils.init(mContext);
        initData(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        RequestUtils.cancelAll(mContext);
    }

    protected View getLayoutView() {
        return null;
    }

    protected void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.title_bg_color));
        msgLayout = (FrameLayout) findViewById(R.id.msg_layout);
        msgTv = (TextView) findViewById(R.id.msg_tv);
        titleTv = (TextView) findViewById(R.id.toolbar_title);
        rightTv = (TextView) findViewById(R.id.toolbar_right_tv);
        rightTv2 = (TextView) findViewById(R.id.toolbar_right_tv2);
        leftImg = (TextView) findViewById(R.id.toolbar_left_img);
        if (titleTv != null)
            titleTv.setText("");

        searchLl = (LinearLayout) findViewById(R.id.search_parent_ll);
        searchEdt = (AutoCompleteTextView) findViewById(R.id.search_content_edt);
        searchSubmitBtn = (Button) findViewById(R.id.search_submit_btn);
        if (searchSubmitBtn != null)
            searchSubmitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSearch(v);
                }
            });
        if (toolbar != null) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    leftClick();
                }
            });
        }
    }

    protected void clickSearch(View v) {
        searchEdt.setText("");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        AppTool.hideSoftInput(this, ev);
        return super.dispatchTouchEvent(ev);
    }

    protected void leftClick() {
        finish();
    }

    public void initView() {
        initToolBar();
    }

    protected abstract void initData(Bundle savedInstanceState);


    protected abstract int getLayoutRes();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttached = true;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isAttached = false;
    }
}
