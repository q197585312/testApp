package gaming178.com.mylibrary.base;

import android.view.View;

import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;

public interface RefreshEndImp {
    void errorEndView(PullToRefreshBase<View> v);

    void successEndView(PullToRefreshBase<View> v);
}
