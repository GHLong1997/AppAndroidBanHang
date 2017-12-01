package com.hangoclong.shop1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hangoclong.shop1.View.DanhMucConActivity.Fragment.FragmentBanChay;
import com.hangoclong.shop1.View.DanhMucConActivity.Fragment.FragmentGia;
import com.hangoclong.shop1.View.DanhMucConActivity.Fragment.FragmentMoiNhat;
import com.hangoclong.shop1.View.DanhMucConActivity.Fragment.FragmentPhoBien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/31/2017.
 */

public class ViewPagerAdapterDanhMucConOfCon extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    public ViewPagerAdapterDanhMucConOfCon(FragmentManager fm) {
        super(fm);
        fragmentList.add(new FragmentPhoBien());
        fragmentList.add(new FragmentMoiNhat());
        fragmentList.add(new FragmentBanChay());
        fragmentList.add(new FragmentGia());

        stringList.add("Phổ biến");
        stringList.add("Mới nhất");
        stringList.add("Bán chạy");
        stringList.add("Gía");
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }
}
