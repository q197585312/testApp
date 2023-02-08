package nanyang.com.dig88.Lottery4D;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import nanyang.com.dig88.Entity.Lottery4DTitleBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/11/16.
 */

public class Lottery4DActivity extends GameBaseActivity {
    public static final String NORMAL = "Normal";
    public static final String FR = "Forward & Reverse";
    public static final String BOX = "Box";
    public static final String IBOX = "iBox";
    public static String Magnum = "214";
    public static String Singapore = "215";
    public static String Toto4D = "216";
    public static String Damacai = "217";
    public static String Sabah = "218";
    public static String Sandakan = "219";
    public static String Sarawak = "220";
    public static String GrandDragon = "221";
    public Map<String, String> poolMap;
    @BindView(R.id.rg_choice_type)
    RadioGroup rg_choice_type;
    @BindView(R.id.tv_animation)
    TextView tvAnimation;
    Lottery4DBaseFragment betFragment;
    Lottery4DBaseFragment betListFragment;
    Lottery4DBaseFragment resultFragment;
    Lottery4DBaseFragment prizeFragment;
    int lastIndex = -1;
    private boolean isAbcAndAType;
    private boolean isBetSingleDate;
    private List<Lottery4DBaseFragment> fragmentList;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_lottery_4d;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setTitle(getString(R.string.lottery4d));
        betFragment = new BetFragment();
        betListFragment = new BetListFragment();
        resultFragment = new ResultFragment();
        prizeFragment = new PrizeFragment();
        fragmentList = Arrays.asList(betFragment, betListFragment, resultFragment, prizeFragment);
        switchFragment(0);
        rg_choice_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int position = 0;
                switch (checkedId) {
                    case R.id.rb_bet:
                        position = 0;
                        break;
                    case R.id.rb_bet_list:
                        position = 1;
                        break;
                    case R.id.rb_result:
                        position = 2;
                        break;
                    case R.id.rb_prize:
                        position = 3;
                        break;
                }
                switchFragment(position);
            }
        });
        initGameType();
        getAnimationData();
    }

    private void initGameType() {
        if (WebSiteUrl.WebId.equals("995") || WebSiteUrl.WebId.equals("48") || WebSiteUrl.WebId.equals("158")|| WebSiteUrl.WebId.equals("2")) {
            isAbcAndAType = true;
        } else {
            isAbcAndAType = false;
        }
        if (WebSiteUrl.WebId.equals("995") || WebSiteUrl.WebId.equals("48") || WebSiteUrl.WebId.equals("158")) {
            isBetSingleDate = true;
        } else {
            isBetSingleDate = false;
        }
    }

    public boolean isAbcAndAType() {
        return isAbcAndAType;
    }

    public boolean isBetSingleDate() {
        return isBetSingleDate;
    }

    public Map<String, String> getPoolMap() {
        if (poolMap == null) {
            poolMap = new HashMap<>();
        }
        return poolMap;
    }

    public void setPoolMap(Map<String, String> poolMap) {
        this.poolMap = poolMap;
    }

    private void switchFragment(int index) {
        Lottery4DBaseFragment baseFragment = fragmentList.get(index);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!baseFragment.isAdded()) {
            fragmentTransaction.add(R.id.fl_content, baseFragment);
        } else {
            fragmentTransaction.show(baseFragment);
        }
        if (lastIndex != -1) {
            fragmentTransaction.hide(fragmentList.get(lastIndex));
        }
        lastIndex = index;
        fragmentTransaction.commit();
    }

    private void getAnimationData() {
        String p = "web_id=" + WebSiteUrl.WebId + "&type=3" + "&lang=" + getLang();
        HttpUtils.httpPost(WebSiteUrl.LotteryTitleUrl, p, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                Lottery4DTitleBean lottery4DTitleBean = new Gson().fromJson(s, Lottery4DTitleBean.class);
                String newContent = UIUtil.delHTMLTag(Html.fromHtml(lottery4DTitleBean.getData()).toString());
                tvAnimation.setText(newContent);
                startAlphaAnimation(tvAnimation);
            }

            @Override
            public void onRequestFailed(String s) {

            }
        });
    }

    public ObjectAnimator startAlphaAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1, (float) 0.5, 1);
        animator.setRepeatCount(Animation.INFINITE);
        animator.setDuration(1500);
        animator.start();
        return animator;
    }

    public String getLang() {
        String lang = "en";
        String localLanguage = getLocalLanguage();
        if (!TextUtils.isEmpty(localLanguage)) {
            switch (localLanguage) {
                case "zh":
                    lang = "cn";
                    break;
                case "en":
                    lang = "en";
                    break;
                case "kh":
                    lang = "kh";
                    break;
                case "kr":
                    lang = "kr";
                    break;
                case "ms":
                    lang = "ma";
                    break;
                case "th":
                    lang = "th";
                    break;
                case "vn":
                    lang = "vn";
                    break;
                case "in":
                    lang = "id";
                    break;
            }
        } else {
            lang = "en";
        }
        return lang;
    }
}
