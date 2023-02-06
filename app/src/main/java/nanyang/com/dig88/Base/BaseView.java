package nanyang.com.dig88.Base;

import android.widget.Toast;

/**
 * Created by Administrator on 2019/1/21.
 */

public class BaseView<T> implements IBaseView<T> {
    IBaseActivity baseActivity;

    public BaseView(IBaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public IBaseActivity getBaseActivity() {
        return baseActivity;
    }

    @Override
    public void onGetData(T data) {
        baseActivity.hideLoadingDialog();
    }

    @Override
    public void onFailed(String error) {
        baseActivity.hideLoadingDialog();
        Toast.makeText(baseActivity.getContextActivity(),error,Toast.LENGTH_SHORT).show();

    }

    public void onThreadStart() {
//        baseActivity.showLoadingDialog();
    }
}
