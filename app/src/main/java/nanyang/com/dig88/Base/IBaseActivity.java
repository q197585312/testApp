package nanyang.com.dig88.Base;

import nanyang.com.dig88.Activity.BaseActivity;

/**
 * Created by Administrator on 2019/1/21.
 */

public interface IBaseActivity {
    void hideLoadingDialog();

    void showLoadingDialog();

    BaseActivity getContextActivity();

    boolean hasAttachedToWindow();
}
