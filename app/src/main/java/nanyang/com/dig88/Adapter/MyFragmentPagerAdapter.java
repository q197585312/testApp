package nanyang.com.dig88.Adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import nanyang.com.dig88.Fragment.BaseFragment;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<? extends BaseFragment> fragmentsList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<? extends BaseFragment> fragments) {
        super(fm);
        this.fragmentsList = fragments;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseFragment f = (BaseFragment) super.instantiateItem(container, position);
        f.setTitle(getPageTitle(position).toString());
        return f;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragmentsList.get(arg0);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsList.get(position).getTitle();
    }

}
