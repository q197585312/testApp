package gaming178.com.mylibrary.base.component;

import android.app.Activity;
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
        Log.d("Life","onCreateView:"+getClass());
        initData(savedInstanceState);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Life","onCreate:"+getClass());
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Life", "onActivityCreated:" + getClass());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Life", "onDestroy:" + getClass());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Life", "onDetach:" + getClass());

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Life", "onPause:" + getClass());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Life", "onResume:" + getClass());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("Life", "onAttach:" + getClass());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Life", "onStart:" + getClass());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Life", "onStop:" + getClass());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Life", "onViewCreated:" + getClass());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("Life", "onViewStateRestored:" + getClass());
    }

    protected void initView(View rootView) {
    }

    protected abstract void initData(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }
}
