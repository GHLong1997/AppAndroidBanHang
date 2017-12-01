package com.hangoclong.shop1.View.DangNhap.DangNhap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.hangoclong.shop1.Adapter.ViewPagerApdaterDangNhap;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.TrangChu.Fragment.FragmentToi;

/**
 * Created by Admin on 10/16/2017.
 */

public class DangNhapActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    AccessToken accessToken;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);


        ViewPagerApdaterDangNhap apdaterDangNhap = new ViewPagerApdaterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(apdaterDangNhap);
        viewPager.setCurrentItem(FragmentToi.SELECT_PAGE);
        tabLayout.setupWithViewPager(viewPager);

        apdaterDangNhap.notifyDataSetChanged();





    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }



}
