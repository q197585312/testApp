package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2017/4/25.
 */

public class ChangePasswordCenterFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.re_changepassword)
    RelativeLayout re_changepassword;

    @BindView(R.id.re_scr_changepasswrod)
    RelativeLayout re_scr_changepasswrod;
    @BindView(R.id.back)
    ImageView back;


    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_change_password_center;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(mContext, "");
        getAct().setTitle(getString(R.string.xiumima));
        getAct().setleftViewEnable(true);
        re_changepassword.setOnClickListener(this);
        re_scr_changepasswrod.setOnClickListener(this);
        back.setOnClickListener(this);
        getAct().setleftViewEnable(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_changepassword: //转账
//                ((MainTabActivity) mContext).changeFragment(new ChangePasswordFragment(), true);

                break;
            case R.id.re_scr_changepasswrod: //转账列表
//                ((MainTabActivity) mContext).changeFragment(new ScrChangePasswrodFragment(), true);

                break;
            case R.id.back:
//                ((MainTabActivity) mContext).changeFragment(new PersonFragment(), true);
                break;
        }
    }
}
