package gaming178.com.mylibrary.base;

import android.content.Context;

public abstract class ThreadHandler<A, B extends RequestBean, S, E> implements ThreadImp<A, S, E> {

    protected Context mContext;


    public ThreadHandler(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public void startThread(A obj) {
        B bean = getRequestBean();
        getHttpData(bean, obj);
    }

    private void getHttpData(B bean, A obj) {
        if (bean == null) {
            return;
        }
        handleHttp(bean, obj);
    }


    protected abstract B getRequestBean();


    protected abstract void handleHttp(B bean, A obj);

}
