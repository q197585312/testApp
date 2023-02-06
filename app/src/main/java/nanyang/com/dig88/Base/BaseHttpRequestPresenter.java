package nanyang.com.dig88.Base;

import java.util.HashMap;

import gaming178.com.mylibrary.base.quick.QuickRequestBean;


public class BaseHttpRequestPresenter<A extends IBaseActivity> {
    private final String TAG = "BaseHttpRequestPresenter";
    protected A baseActivity;


    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */

    public BaseHttpRequestPresenter(A baseActivity) {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        this.baseActivity = baseActivity;
    }

    public void unSubscribe() {
    }

    public <T> void getRequest(final QuickRequestBean requestBean, final BaseView<T> baseView) {

        SportJsonThreadHandler<T> thread = new SportJsonThreadHandler<T>(baseActivity.getContextActivity()) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {

                return requestBean;
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                if (baseActivity.hasAttachedToWindow())
                    baseView.onFailed(obj);
            }

            @Override
            public void successEnd(T obj) {
                super.successEnd(obj);
                if (baseActivity.hasAttachedToWindow())
                    baseView.onGetData(obj);
            }

            @Override
            public void startThread(Integer obj) {
                super.startThread(obj);
                baseView.onThreadStart();
            }
        };
        thread.startThread(null);


    }



}