package gaming178.com.mylibrary.base.component;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import gaming178.com.mylibrary.R;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.BlockDialog;


public abstract class BaseActivity extends AutoLayoutActivity {
    protected Toolbar toolbar;
    protected TextView titleTv;
    protected TextView tvCenterTitle;
    protected Context mContext;

    //    protected LinearLayout searchLl;
//    protected AutoCompleteTextView searchEdt;
    protected LinearLayout setLayout;
    protected Button searchSubmitBtn;


    protected TextView backTv;

    protected BlockDialog dialog;
    protected boolean isAttached;
    protected View baseContentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            // Activity was brought to front and not created,
            // Thus finishing this will get us to the last viewed activity
            finish();
            return;
        }
        if (getLayoutRes() == 0)
            setContentView(getLayoutView());
        else
            setContentView(getLayoutRes());
        mContext = this;
        ButterKnife.bind(this);
        setDialog(new BlockDialog(mContext, getString(R.string.loading)));
        initView();
        initData(savedInstanceState);

    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        this.baseContentView = view;
    }

    @Override
    public void setContentView(int res) {
        View view = LayoutInflater.from(this).inflate(res, null);
        setContentView(view);
    }

    protected View getLayoutView() {
        return null;
    }

    protected void initToolBar() {
        toolbar =findViewById(R.id.toolbar);

        setLayout = (LinearLayout) findViewById(R.id.layout_set);
        titleTv = (TextView) findViewById(R.id.toolbar_title);
        tvCenterTitle = (TextView) findViewById(R.id.tv_center_title);
        backTv = (TextView) findViewById(R.id.toolbar_back_tv);
/*
        searchLl = (LinearLayout) findViewById(R.id.search_parent_ll);
        searchEdt = (AutoCompleteTextView) findViewById(R.id.search_content_edt);
        searchSubmitBtn = (Button) findViewById(R.id.search_submit_btn);
        if (searchSubmitBtn != null)
            searchSubmitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSearch(v);
                }
            });*/
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

/*    protected void clickSearch(View v) {
        searchEdt.setText("");
    }*/

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        AppTool.hideSoftInput(this, ev);
        return super.dispatchTouchEvent(ev);
    }

    protected void leftClick() {
        finish();
    }

    protected void initView() {
        initToolBar();
    }

    protected abstract void initData(Bundle savedInstanceState);


    protected abstract int getLayoutRes();

    public void setDialog(BlockDialog dialog) {
        this.dialog = dialog;
    }

    public void showBlockDialog() {
        if (getWindow().isActive() && !isFinishing() && isAttached && dialog != null) {
            try {
                dialog.dismiss();
                dialog.show();
            } catch (Exception e) {

            }
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissBlockDialog();

    }

    public void dismissBlockDialog() {
        if (!isFinishing() && dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
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
