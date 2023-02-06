package nanyang.com.dig88.Base;

import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2019/1/23.
 */

public class NyBaseView<T> extends BaseView<NyBaseResponse<T>> {
    public NyBaseView(IBaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public void onGetData(NyBaseResponse<T> data) {
        super.onGetData(data);
        if (data == null && data.getCode() == null) {
            onFailed(baseActivity.getContextActivity().getString(R.string.net_error));
        } else if (data.getCode().equals("1") && data.getData() != null) {
            onGetDataSuccess(data.getData());
        } else {
            if (data.getMsg() != null)
                onFailed(data.getMsg());
            else
                onFailed(baseActivity.getContextActivity().getString(R.string.server_error) + "  code:" + data.getCode());
        }
    }

    public void onGetDataSuccess(T data) {

    }

}
