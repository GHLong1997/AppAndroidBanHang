package com.hangoclong.shop1.View.TimKiem;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.hangoclong.shop1.Adapter.CustomRecyclerDanhMucCon;
import com.hangoclong.shop1.Adapter.ViewPagerAdapterDanhMucConOfCon;
import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.Presenter.DanhMucConActivity.PresenterLogicDanhMucCon;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DanhMucConActivity.ViewDanhMucCon;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewDanhMucCon {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PresenterLogicDanhMucCon presenterLogicDanhMucCon;
    RecyclerView recyclerDMCCC;

    public static int MALOAISP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        recyclerDMCCC = (RecyclerView) findViewById(R.id.recyclerDMCCC);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ViewPagerAdapterDanhMucConOfCon adapterDanhMucConOfCon = new ViewPagerAdapterDanhMucConOfCon(getSupportFragmentManager());
        viewPager.setAdapter(adapterDanhMucConOfCon);
        tabLayout.setupWithViewPager(viewPager);
        adapterDanhMucConOfCon.notifyDataSetChanged();
        presenterLogicDanhMucCon = new PresenterLogicDanhMucCon(this);


        SharedPreferences sharedPreferences = getSharedPreferences("maloaisp",MODE_PRIVATE);
        int maloaisp = sharedPreferences.getInt("maloaisp",0);

        if(!String.valueOf(getIntent().getIntExtra("maloai",0)).equals("")){
            MALOAISP = getIntent().getIntExtra("maloai",0);
            Log.d("sssss1",MALOAISP+"");

            presenterLogicDanhMucCon.LayDanhSachLoaiSPCon(MALOAISP);


        }
        if(maloaisp!=0){//sharepreferences
            Log.d("mamm",maloaisp+"");
            presenterLogicDanhMucCon.LayDanhSachLoaiSPCon(maloaisp);
        }

    }



    @Override
    public void HienThiDanhMucCon(List<LoaiSanPham> loaiSanPhamList) {
        CustomRecyclerDanhMucCon recyclerDanhMucConCuaCon = new CustomRecyclerDanhMucCon(this,loaiSanPhamList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerDMCCC.setLayoutManager(layoutManager);
        recyclerDMCCC.setAdapter(recyclerDanhMucConCuaCon);
        recyclerDanhMucConCuaCon.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }
}
