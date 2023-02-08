package nanyang.com.dig88.load;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.R;

/**
 * Created by xToney on 2015/12/24.
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> {
    private static final int[] drawableIds = new int[]{R.mipmap.welcome1, R.mipmap.welcome2, R.mipmap.welcome3};
    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    @BindView(R.id.viewPagerContainer)
    RelativeLayout viewPagerContainer;
    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;
    @BindView(R.id.img_bg)
    ImageView imgBg;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new WelcomePresenter(this));//声明本页面架构 MVP中的P 逻辑处理放P  页面只处理 控件的展示
        presenter.getNetIp();
        presenter.handleAffiliateId();
        presenter.handleNewVersion();
        imgBg.setVisibility(View.GONE);
        viewPagerContainer.setVisibility(View.VISIBLE);
        presenter.initViewPager(viewPagerContainer, vpGuide, drawableIds);
    }

}
