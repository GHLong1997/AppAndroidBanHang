package com.hangoclong.shop1.View.ChiTietSanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hangoclong.shop1.Adapter.CustomRecyclerChiTietSanPham;
import com.hangoclong.shop1.Adapter.CustomRecyclerDanhGia;
import com.hangoclong.shop1.Adapter.ViewPagerAdapterSlide;
import com.hangoclong.shop1.Model.ObjectClass.ChiTietSanPham;
import com.hangoclong.shop1.Model.ObjectClass.DanhGia;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.hangoclong.shop1.Presenter.DanhGia.PresenterLogicDanhGia;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DanhGia.DanhGiaActivity;
import com.hangoclong.shop1.View.DanhGia.ThemDanhGiaActivity;
import com.hangoclong.shop1.View.DanhGia.ViewDanhGia;
import com.hangoclong.shop1.View.GioHang.GioHangActivity;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;
import com.hangoclong.shop1.View.TrangChu_Mua.FragmentSliderKhuyenMai;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements  ViewChiTietSanPham,ViewPager.OnPageChangeListener,View.OnClickListener,ViewDanhGia{
    ViewPager viewPagerSlider;
    ViewPagerAdapterSlide viewPagerAdapterSlide;
    List<FragmentSliderKhuyenMai> fragmentSliderKhuyenMaiList = new ArrayList<>();
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham ;
    LinearLayout lnDot;
    TextView[] txtDot;
    TextView txtTenSP,txtGia,txtTenChiTiet,txtGiaTri,txtThongTin,txtTenShop,txtVietDanhGia,
            txtChuaDanhGia,txtChuaDG,txtTongDanhGia,txtTongBL,txtTongDanhGia1,txtXemTatCa,txtGioHang;
    RecyclerView recyclerChiTietSanPham;
    RecyclerView recyclerDanhGia;
    FrameLayout frDanhGia,frDanhGia1;
    RatingBar rdTongDanhGia,rdTongDanhGia1;
    Toolbar toolbar;
    ImageButton imThemGioHang;
    int masp;
    SanPham sanPhamGioHang;
    public  static  int LINE=1;
    int soluongtonkho=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        viewPagerSlider = (ViewPager) findViewById(R.id.viewPagerSlide);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lnDot = (LinearLayout) findViewById(R.id.lnDot);
        txtTenSP = (TextView) findViewById(R.id.txtTenSP);
        txtGia = (TextView) findViewById(R.id.txtGia);
        txtTenChiTiet = (TextView) findViewById(R.id.txtTenChiTiet);
        txtGiaTri = (TextView) findViewById(R.id.txtGiaTri);
        txtThongTin = (TextView) findViewById(R.id.txtThongTinSP);
        txtTenShop = (TextView) findViewById(R.id.txtTenShop);
        txtVietDanhGia = (TextView) findViewById(R.id.txtVietDanhGia);
        recyclerChiTietSanPham = (RecyclerView) findViewById(R.id.recyclerChiTietSanPham);
        recyclerDanhGia = (RecyclerView) findViewById(R.id.recycler_danhgia);
        frDanhGia = (FrameLayout) findViewById(R.id.frDanhGia);
        txtChuaDanhGia = (TextView) findViewById(R.id.txtChuaDanhGia);
        txtChuaDG = (TextView) findViewById(R.id.txtCDG);
        rdTongDanhGia = (RatingBar) findViewById(R.id.rdTongDanhGia);
        txtTongDanhGia = (TextView) findViewById(R.id.txtTongRating);
        txtTongBL = (TextView) findViewById(R.id.txtTongBL);
        rdTongDanhGia1 = (RatingBar) findViewById(R.id.rdTongDanhGia1);
        txtTongDanhGia1 = (TextView) findViewById(R.id.txtTongRating1);
        frDanhGia1 = (FrameLayout) findViewById(R.id.frDanhGia1);
        txtXemTatCa = (TextView) findViewById(R.id.txtXemTatCa);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        viewPagerSlider.addOnPageChangeListener(this);
        txtXemTatCa.setOnClickListener(this);
        imThemGioHang = (ImageButton) findViewById(R.id.imThemGiohang);
        imThemGioHang.setOnClickListener(this);


        masp = getIntent().getIntExtra("masp",0);
        Log.d("mamm",masp+"");
        if(masp!=0){
            presenterLogicChiTietSanPham.LayChiTietSanPham(masp);
        }
        PresenterLogicDanhGia presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhDanh(masp);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chitiet,menu);
        MenuItem itemGioHang = menu.findItem(R.id.giohang);
        View giaoDienCustomGioHang = itemGioHang.getActionView();
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TrangChuActivity.TENDANGNHAP ==""){
                    Toast.makeText(ChiTietSanPhamActivity.this,"Bạn chưa đăng nhập",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
                }

            }
        });

        return true;
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
    public void HienThiChiTietSanPham(SanPham sanPham) {
        soluongtonkho = sanPham.getSoluong();
        Log.d("soluongt",soluongtonkho+"");
        sanPhamGioHang = sanPham;
        toolbar.setTitle(sanPham.getTenSP());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtTenSP.setText(sanPham.getTenSP());


        NumberFormat numberFormat = new DecimalFormat("###,###");
        Log.d("chitietsaaaa",sanPham.getChiTietSanPhams()+"");
        txtGia.setText(numberFormat.format(sanPham.getGia()) + " VNĐ");
        txtThongTin.setText(sanPham.getThongTin());
        txtTenShop.setText(sanPham.getTenShop());
        List<ChiTietSanPham> chiTietSanPhamList = sanPham.getChiTietSanPhams();
        CustomRecyclerChiTietSanPham customRecyclerChiTietSanPham = new CustomRecyclerChiTietSanPham(this,chiTietSanPhamList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerChiTietSanPham.setLayoutManager(layoutManager);
        recyclerChiTietSanPham.setAdapter(customRecyclerChiTietSanPham);
        customRecyclerChiTietSanPham.notifyDataSetChanged();

        txtVietDanhGia.setOnClickListener(this);



    }

    @Override
    public void HienThiSliderChiTietSanPham(SanPham sanPham) {


        String linkhinh[] = sanPham.getAnhNho().split(",");
        ;
        for (int i = 0; i < linkhinh.length; i++) {

            Bundle bundle = new Bundle();
            bundle.putString("linkhinh", TrangChuActivity.SERVER+linkhinh[i]);
            FragmentSliderKhuyenMai fragmentSliderKhuyenMai = new FragmentSliderKhuyenMai();
            fragmentSliderKhuyenMai.setArguments(bundle);
            fragmentSliderKhuyenMaiList.add(fragmentSliderKhuyenMai);
        }

        ViewPagerAdapterSlide adapterSlide = new ViewPagerAdapterSlide(getSupportFragmentManager(),fragmentSliderKhuyenMaiList);
        viewPagerSlider.setAdapter(adapterSlide);
        adapterSlide.notifyDataSetChanged();
        ThemDotSlider(0);

    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this,"Thêm giỏ hàng thành công",Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));//khi them thanh cong thi nothem
    }

    @Override
    public void ThemGioHangThatBai() {
        //tức là đã có sản phẩm đó trong giỏ hang nên chi việc cập nhật lại
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));//khi them thanh cong thi nothem
    }



    public  void ThemDotSlider(int vitrihientai){
        lnDot.removeAllViews();
        txtDot = new TextView[fragmentSliderKhuyenMaiList.size()];
        for(int i = 0;i< txtDot.length;i++){
           txtDot[i] = new TextView(this);
           txtDot[i].setText(Html.fromHtml("&#8226;"));
           txtDot[i].setTextSize(40);
           txtDot[i].setTextColor(GetIdColor(R.color.ganGray));
            lnDot.addView(txtDot[i]);
        }

        txtDot[vitrihientai].setTextColor(GetIdColor(R.color.bgToolbar));



    }

    public  int  GetIdColor(int idColor){
        int color;
        if(Build.VERSION.SDK_INT>21){

         color =   ContextCompat.getColor(this,idColor);
        }else {
          color =   getResources().getColor(idColor);
        }
        return  color;
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtVietDanhGia:
                Intent intent = new Intent(ChiTietSanPhamActivity.this, ThemDanhGiaActivity.class);
                intent.putExtra("masp",masp);
                startActivity(intent);
                break;
            case  R.id.txtXemTatCa:
                Intent itXemTatCa = new Intent(ChiTietSanPhamActivity.this, DanhGiaActivity.class);
                itXemTatCa.putExtra("masp",masp);
                startActivity(itXemTatCa);
                overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
                break;
            case R.id.imThemGiohang:
                if(TrangChuActivity.TENDANGNHAP ==""){
                    Toast.makeText(ChiTietSanPhamActivity.this,"Bạn chưa đăng nhập",Toast.LENGTH_SHORT).show();
                }else {
                    Fragment fragment = fragmentSliderKhuyenMaiList.get(0);//mục đích chỉ là lấy cái hình sản phầm đâu tiên mà đã tải
                    View view1 = fragment.getView();//đã lấy đc cai layout tổng cửa thăng fragment;
                    ImageView imageView = view1.findViewById(R.id.imSlider);
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();//phải ép kiểu

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();
                    sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang);
                    sanPhamGioHang.setSoluong(1);//tuc la khi ng dung kich vao gio hang thi so luong luc dau luon luon la 1
                    sanPhamGioHang.setSoLuongTonKho(soluongtonkho);
                    presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang,this);
                }



        }

    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGias) {
        Log.d("duroidonha",danhGias.size()+"");
        CustomRecyclerDanhGia customRecyclerDanhGia = new CustomRecyclerDanhGia(this,danhGias);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerDanhGia.setLayoutManager(layoutManager);
        recyclerDanhGia.setAdapter(customRecyclerDanhGia);
        customRecyclerDanhGia.notifyDataSetChanged();
    }

    @Override
    public void HienThiTongDanhGia(List<DanhGia> danhGiaList) {
        txtXemTatCa.setVisibility(View.VISIBLE);
        frDanhGia.setVisibility(View.VISIBLE);
        frDanhGia1.setVisibility(View.VISIBLE);
        txtChuaDG.setVisibility(View.GONE);
        txtChuaDanhGia.setVisibility(View.GONE);

        float tongDG=0.0f;
        int tongBL = 0;
        for(int i = 0 ; i < danhGiaList.size();i++){

            tongDG += danhGiaList.get(i).getSOSAO();
            if(!danhGiaList.get(i).getNOIDUNG().equals("")){
                tongBL+=1;
            }
        }
        float average = (float) tongDG/danhGiaList.size();
        txtTongDanhGia.setText(average+"/5");
        txtTongDanhGia1.setText(average+"/5");
        rdTongDanhGia.setRating(average);
        rdTongDanhGia1.setRating(average);
        txtTongBL.setText(" ("+tongBL+")");


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }
}
