package com.gevyo.android.eduquer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gevyo.android.RuangBacaFragment.RuangBacaHomeFragment;
import com.gevyo.android.RuangBacaFragment.RuangBacaKonsepFragment;
import com.gevyo.android.RuangBacaFragment.RuangBacaSbmptnFragment;
import com.gevyo.android.RuangBacaFragment.RuangBacaUjianFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new RuangBacaHomeFragment();
            case 1: return new RuangBacaKonsepFragment();
            case 2: return new RuangBacaUjianFragment();
            case 3: return new RuangBacaSbmptnFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
