package com.hangoclong.shop1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment1Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment2Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment3Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment4Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment5Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.FragmentBL;
import com.hangoclong.shop1.View.DanhGia.Fragment.FragmentHA;
import com.hangoclong.shop1.View.DanhGia.Fragment.FragmentTatCa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/14/2017.
 */

public class ViewPagerAdapterDanhGia extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
    public ViewPagerAdapterDanhGia(FragmentManager fm) {
        super(fm);
        fragmentList.add(new FragmentTatCa());
        fragmentList.add(new FragmentBL());
        fragmentList.add(new FragmentHA());
        fragmentList.add(new Fragment1Sao());
        fragmentList.add(new Fragment2Sao());
        fragmentList.add(new Fragment3Sao());
        fragmentList.add(new Fragment4Sao());
        fragmentList.add(new Fragment5Sao());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
