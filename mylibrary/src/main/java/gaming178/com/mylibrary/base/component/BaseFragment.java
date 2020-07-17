package gaming178.com.mylibrary.base.component;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;


/**
 * @author xs
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View rootView;
    protected abstract int getFragmentLayoutRes();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater
                .inflate(getFragmentLayoutRes(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        Log.d("Life","onCreate:"+getTag());
        initData(savedInstanceState);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    protected void initView(View rootView) {
    }

    protected abstract void initData(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
