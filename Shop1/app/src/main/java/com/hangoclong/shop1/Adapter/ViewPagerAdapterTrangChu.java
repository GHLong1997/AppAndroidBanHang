package com.hangoclong.shop1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hangoclong.shop1.View.TrangChu.Fragment.FragmenThongBao;
import com.hangoclong.shop1.View.TrangChu.Fragment.FragmentDao;
import com.hangoclong.shop1.View.TrangChu.Fragment.FragmentMua;
import com.hangoclong.shop1.View.TrangChu.Fragment.FragmentToi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/15/2017.
 */

public class ViewPagerAdapterTrangChu extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    public ViewPagerAdapterTrangChu(FragmentManager fm) {
        super(fm);
        fragmentList.add(new FragmentMua());
        fragmentList.add(new FragmentDao());
        fragmentList.add(new FragmenThongBao());
        fragmentList.add(new FragmentToi());

        stringList.add("Mua");
        stringList.add("Dạo");
        stringList.add("Thông báo");
        stringList.add("Tôi");
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
