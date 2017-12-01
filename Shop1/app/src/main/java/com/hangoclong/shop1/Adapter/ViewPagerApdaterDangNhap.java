package com.hangoclong.shop1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hangoclong.shop1.View.DangNhap.Fragment.Fragment_dangky;
import com.hangoclong.shop1.View.DangNhap.Fragment.Fragment_dangnhap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/16/2017.
 */

public class ViewPagerApdaterDangNhap extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    public ViewPagerApdaterDangNhap(FragmentManager fm) {
        super(fm);
        fragmentList.add(new Fragment_dangky());
        fragmentList.add(new Fragment_dangnhap());



        stringList.add("Đăng ký");
        stringList.add("Đăng nhập");
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
