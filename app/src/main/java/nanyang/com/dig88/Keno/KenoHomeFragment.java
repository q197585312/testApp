package nanyang.com.dig88.Keno;

import android.os.Bundle;
import gaming178.com.mylibrary.base.ViewHolder;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2016/6/7.
 */
public class KenoHomeFragment extends KenoBaseFragment {




    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_home_keno;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getAct().setRightViewClickable(true);
    }

    @Override
    protected void convertHeader(ViewHolder helper, String item, int position) {

    }

    @Override
    protected void convertResult(ViewHolder helper, String item, int position) {

    }

}