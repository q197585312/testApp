package nanyang.com.dig88.NewKeno;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import nanyang.com.dig88.Fragment.BaseFragment;

/**
 * Created by Administrator on 2018/9/13.
 */

public abstract class NewKenoBaseFragment<P extends BaseRetrofitPresenter> extends BaseFragment<P> {
    public final int refreshAnimationNumber = 10;
    public final int refreshAnimationNumberBg = 11;
    public final int showTotalNumber = 12;
    public final int drawingFinish = 14;
    public int screenWith;
    public boolean isFistEnter = true;
    public NewKenoActivity newKenoActivity;
    private Gson gson;
    private boolean isCanBet = true;

    public Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        newKenoActivity = (NewKenoActivity) getActivity();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWith = dm.widthPixels;         // 屏幕宽度（像素）
    }

    public String getRule() {
        return "";
    }

    public boolean getIsCanBet() {
        return isCanBet;
    }

    public void setIsCanBet(boolean isCanBet) {
        this.isCanBet = isCanBet;
    }

    public int getMaxSecond() {
        return -1;
    }
}
