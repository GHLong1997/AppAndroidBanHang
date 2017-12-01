package com.hangoclong.shop1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hangoclong.shop1.View.TrangChu_Mua.FragmentSliderKhuyenMai;

import java.util.List;

/**
 * Created by Admin on 10/4/2017.
 */

public class ViewPagerAdapterSlide extends FragmentPagerAdapter{
    List<FragmentSliderKhuyenMai> fragmentSliderKhuyenMais;
    public ViewPagerAdapterSlide(FragmentManager fm, List<FragmentSliderKhuyenMai> fragmentSliderKhuyenMais) {
        super(fm);
        this.fragmentSliderKhuyenMais = fragmentSliderKhuyenMais;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentSliderKhuyenMais.get(position);
    }

    @Override
    public int getCount() {
        return fragmentSliderKhuyenMais.size();
    }
}
