package com.nanyang.app.main.home.sport.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import android.view.ViewGroup;

import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<? extends BaseFragment> fragmentsList;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

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