package com.hangoclong.shop1.View.DanhMucConActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.hangoclong.shop1.Adapter.CustomRecyclerDanhMucCon;
import com.hangoclong.shop1.Adapter.ViewPagerAdapterDanhMucConOfCon;
import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.Presenter.DanhMucConActivity.PresenterLogicDanhMucCon;
import com.hangoclong.shop1.R;

import java.util.List;

public class DanhMucConActivity extends AppCompatActivity implements ViewDanhMucCon  {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PresenterLogicDanhMucCon presenterLogicDanhMucCon;
    RecyclerView recyclerDMCCC;

    public static int MALOAISP=0,MALOAISP2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhmuccon_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        recyclerDMCCC = (RecyclerView) findViewById(R.id.recyclerDMCCC);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPagerAdapterDanhMucConOfCon adapterDanhMucConOfCon = new ViewPagerAdapterDanhMucConOfCon(getSupportFragmentManager());
        viewPager.setAdapter(adapterDanhMucConOfCon);
        tabLayout.setupWithViewPager(viewPager);
        adapterDanhMucConOfCon.notifyDataSetChanged();
        presenterLogicDanhMucCon = new PresenterLogicDanhMucCon(this);

//
//        SharedPreferences sharedPreferences = getSharedPreferences("maloaisp",MODE_PRIVATE);
//        int maloaisp = sharedPreferences.getInt("maloaisp",0);



        if(!String.valueOf(getIntent().getIntExtra("maloai",0)).equals("")){
            MALOAISP = getIntent().getIntExtra("maloai",0);
            Log.d("sssss1",MALOAISP+"");
            //Phan hie nthi button
            presenterLogicDanhMucCon.LayDanhSachLoaiSPCon(MALOAISP);

        }
        if(!String.valueOf(getIntent().getIntExtra("malspham",0)).equals("")){
            MALOAISP2 = getIntent().getIntExtra("malspham",0);//nhan maloai sau khi kich vao cac btn sam sung , iphone de loc san pham
        }



        SharedPreferences sharedPreferences = getSharedPreferences("maloaisp",MODE_PRIVATE);
        int maloaisp = sharedPreferences.getInt("maloaisp",0);// muc dich lam thang nay chu yeu de khong cho no mat may caibutton
        if(maloaisp!=0){//sharepreferences
            Log.d("mamm",maloaisp+"");
            presenterLogicDanhMucCon.LayDanhSachLoaiSPCon(maloaisp);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        }
        return super.onOptionsItemSelected(item);
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
