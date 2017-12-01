package com.hangoclong.shop1.View.DanhMucActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hangoclong.shop1.Adapter.CustomRecyclerDanhMuc;
import com.hangoclong.shop1.Adapter.CustomRecyclerKhoangGiaTienTopSanPham;
import com.hangoclong.shop1.Adapter.CustomRecyclerThuongHieu;
import com.hangoclong.shop1.Adapter.ViewPagerAdapterSlide;
import com.hangoclong.shop1.Model.ObjectClass.KhuyenMai;
import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Model.ObjectClass.ThuongHieu;
import com.hangoclong.shop1.Presenter.DanhMucActivity.PresenterLogicDanhMuc;
import com.hangoclong.shop1.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.hangoclong.shop1.Presenter.TrangChu_Mua.PresenterLogicMua;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.TrangChu_Mua.FragmentSliderKhuyenMai;
import com.hangoclong.shop1.View.TrangChu_Mua.ViewKhuyenMai;
import com.hangoclong.shop1.View.ViewMua;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 10/30/2017.
 */

public class DanhMucActivity extends AppCompatActivity implements ViewMua,ViewDanhMuc,ViewKhuyenMai,ViewPager.OnPageChangeListener{
    Toolbar toolbar;
    RecyclerView recyclerDanhMucCon,recyclerThuongHieu,recyclerTopDienThoaiBanChay;
    int maloaisp;
    List<FragmentSliderKhuyenMai> fragmentSliderKhuyenMaiList;
    LinearLayout lnDot;
    TextView[] txtDots; // chúng ta sẽ tiến hành add cai txtDot này vao linear;
    android.os.Handler handler;
    Timer timer;
    int page =0;
    PresenterLogicDanhMuc presenterLogicMua2;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;
    PresenterLogicMua presenterLogicMua;
    ViewPager viewPagerSlider;
    LinearLayout lnTopDT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhmuc_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerDanhMucCon = (RecyclerView) findViewById(R.id.recyclerDanhMucCon);
        viewPagerSlider = (ViewPager) findViewById(R.id.viewPagerSlide);
        recyclerThuongHieu = (RecyclerView) findViewById(R.id.recyclerThuongHieu);
        recyclerTopDienThoaiBanChay = (RecyclerView) findViewById(R.id.recyclerBanChayNhat);
        lnTopDT = (LinearLayout) findViewById(R.id.lnTopDT);
        lnDot = (LinearLayout) findViewById(R.id.layoutDot);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenterLogicMua2 = new PresenterLogicDanhMuc(this,this);
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();
        presenterLogicMua = new PresenterLogicMua(this);
        presenterLogicMua.LayDanhSachThuongHieu();
        if(!String.valueOf(getIntent().getIntExtra("maloai",0)).equals("")){
            maloaisp = getIntent().getIntExtra("maloai",0);
            if(maloaisp==12){
                lnTopDT.setVisibility(View.VISIBLE);
                presenterLogicMua2.LayKhoangGiaTienTopDienThoai();
            }
            Log.d("sssss",maloaisp+"");
            presenterLogicMua2.LayDanhSachLoaiSP(maloaisp);

        }

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                viewPagerSlider.setCurrentItem(page);
                page++;
                if(page==fragmentSliderKhuyenMaiList.size()){
                    page=0;
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        },3000,3000);


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
    public void LayDanhSachLoaiSPThanhCong(List<LoaiSanPham> loaiSanPhamList) {
        CustomRecyclerDanhMuc danhMucCha = new CustomRecyclerDanhMuc(this,loaiSanPhamList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.HORIZONTAL,false);
        recyclerDanhMucCon.setLayoutManager(layoutManager);
        recyclerDanhMucCon.setAdapter(danhMucCha);
        danhMucCha.notifyDataSetChanged();
    }

    @Override
    public void LayDanhSachLoaiSPThatBai() {

    }

    @Override
    public void LayDanhSachThuongHieuThanhCong(List<ThuongHieu> thuongHieuList) {
        CustomRecyclerThuongHieu customRecyclerThuongHieu = new CustomRecyclerThuongHieu(this,thuongHieuList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.HORIZONTAL,false);
        recyclerThuongHieu.setLayoutManager(layoutManager);
        recyclerThuongHieu.setAdapter(customRecyclerThuongHieu);
        customRecyclerThuongHieu.notifyDataSetChanged();
    }

    @Override
    public void LayDanhSachThuongHieuThatBai() {

    }

    @Override
    public void HienThiDanhSachSanPhamPhoBien(List<SanPham> sanPhamList) {

    }

    @Override
    public void HienThiDanhSachSanPhamMoiVe(List<SanPham> sanPhamList) {

    }


    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {

    }

    @Override
    public void HienThiSliderKhuyenMai(List<KhuyenMai> khuyenMaiList) {
        fragmentSliderKhuyenMaiList = new ArrayList<>();

        for(int i = 0 ;i < khuyenMaiList.size();i++){
            KhuyenMai khuyenMai = khuyenMaiList.get(i);
            FragmentSliderKhuyenMai fragmentSliderKhuyenMai = new FragmentSliderKhuyenMai();
            Bundle bundle = new Bundle();
            Log.d("liii", khuyenMai.getHinhKhuyenMai() +"");
            bundle.putString("linkhinh", khuyenMai.getHinhKhuyenMai() );
            fragmentSliderKhuyenMai.setArguments(bundle);//phahi setArgument cho thang nay thi moi getArgument cho thang kai dc
            fragmentSliderKhuyenMaiList.add(fragmentSliderKhuyenMai);//bước tiếp theo tạo 1 viewpager đe load thăng list này //b4
        }

        ViewPagerAdapterSlide viewPagerAdapterSlide = new ViewPagerAdapterSlide(getSupportFragmentManager(),fragmentSliderKhuyenMaiList);
        viewPagerSlider.setAdapter(viewPagerAdapterSlide);
        viewPagerAdapterSlide.notifyDataSetChanged();
        ThemDotSlider(0);
        viewPagerSlider.addOnPageChangeListener(this);//phai imple thang nay , phai imple,


    }

    private void ThemDotSlider(int vitrihientai){//vitri ban đầu dậu chám màu đỏ(vitrihientai), cai function nay làm sau ViewPager
        lnDot.removeAllViews();//tức là khi  qua 1 fragment mới thi no se go bo những view cũ
        txtDots = new TextView[fragmentSliderKhuyenMaiList.size()];

        for(int i = 0 ;i< fragmentSliderKhuyenMaiList.size(); i++){
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));// &#8226: chính la dâu chấm,ki tu dac biet cua html , Html nay no biêt may cai nhu br vv
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));//vi ko the set color truc tiep dc nen phai tao

            lnDot.addView(txtDots[i]);
        }

        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));
    }

    private  int getIdColor(int IdColor){
        int color = 0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,IdColor);
        }else {
            color = getResources().getColor(IdColor);
        }
        return  color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);//chính la vị trí hiện tại của thăng fragment
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void HienThiKhoangGiaTienTopDienThoaiBanChay(List<SanPham> sanPhamList) {
        CustomRecyclerKhoangGiaTienTopSanPham customRecyclerKhoangGiaTienTopSanPham = new CustomRecyclerKhoangGiaTienTopSanPham(this,sanPhamList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerTopDienThoaiBanChay.setLayoutManager(layoutManager);
        recyclerTopDienThoaiBanChay.setAdapter(customRecyclerKhoangGiaTienTopSanPham);
        customRecyclerKhoangGiaTienTopSanPham.notifyDataSetChanged();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }
}
