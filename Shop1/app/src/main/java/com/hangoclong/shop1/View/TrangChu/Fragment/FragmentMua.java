package com.hangoclong.shop1.View.TrangChu.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hangoclong.shop1.Adapter.CustomRecyclerDanhMucTong;
import com.hangoclong.shop1.Adapter.CustomRecyclerFlashSale;
import com.hangoclong.shop1.Adapter.CustomRecyclerThuongHieu;
import com.hangoclong.shop1.Adapter.CustomReyclerSanPhamPhoBien;
import com.hangoclong.shop1.Adapter.ViewPagerAdapterSlide;
import com.hangoclong.shop1.Model.ObjectClass.KhuyenMai;
import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Model.ObjectClass.ThuongHieu;
import com.hangoclong.shop1.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.hangoclong.shop1.Presenter.TrangChu_Mua.PresenterLogicMua;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.TrangChu_Mua.FragmentSliderKhuyenMai;
import com.hangoclong.shop1.View.TrangChu_Mua.ViewKhuyenMai;
import com.hangoclong.shop1.View.ViewMua;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 10/15/2017.
 */

public class FragmentMua extends Fragment implements ViewMua,ViewKhuyenMai,ViewPager.OnPageChangeListener{
    RecyclerView recyclerDanhMuc,recyclerThuongHieu,recyclerFlashSale,recyclerSanPhamPhoBien,reyclcerSanPhamMoiVe;
    PresenterLogicMua presenterLogicMua;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;
    ViewPager viewPagerSlider;
    Thread myThread = null;
    TextView txtTime;
    int h,p,s;
    List<FragmentSliderKhuyenMai> fragmentSliderKhuyenMaiList;
    LinearLayout lnDot;
    TextView[] txtDots; // chúng ta sẽ tiến hành add cai txtDot này vao linear;
    android.os.Handler handler;
    Timer timer;
    int page =0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_mua,container,false);
        recyclerDanhMuc = view.findViewById(R.id.recyclerDanhMuc);
        recyclerThuongHieu = view.findViewById(R.id.recyclerThuongHieu);
        recyclerFlashSale = view.findViewById(R.id.recyclerFlashSale);
        recyclerSanPhamPhoBien = view.findViewById(R.id.recyclerSanPhamPhoBien);
        reyclcerSanPhamMoiVe = view.findViewById(R.id.recyclerSanPhamMoiVe);
        viewPagerSlider = view.findViewById(R.id.viewPagerSlide);
        lnDot = view.findViewById(R.id.layoutDot);
        txtTime = view.findViewById(R.id.txtTime);
        presenterLogicMua = new PresenterLogicMua(this);
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicMua.LayDanhSachLoaiSP();
        presenterLogicMua.LayDanhSachThuongHieu();
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();
        presenterLogicMua.LayDanhSachSanPhamPhoBien();
        presenterLogicMua.LayDanhSachSanPhamMoiVe();


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

        int tong = 1000;
         h = tong/3600;
         p = tong%3600/60;
         s = tong%3600%60;
        CountDownTimer newtimer = new CountDownTimer((tong+2)*1000, 1000) {

            public void onTick(long millisUntilFinished) {


                Calendar c = Calendar.getInstance();
                txtTime.setText(h+":"+p+":"+s);
                s--;
                if(s==0&&p>0) {
                    s = 59;
                    p--;
                    if (p == 0&&h>0) {
                        p = 59;
                        h--;

                    }
                }


            }
            public void onFinish() {

            }
        };
        newtimer.start();
        return  view;
    }






    @Override
    public void LayDanhSachLoaiSPThanhCong(List<LoaiSanPham> loaiSanPhamList) {
        CustomRecyclerDanhMucTong recyclerDanhMucCha = new CustomRecyclerDanhMucTong(getActivity(),loaiSanPhamList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2, LinearLayoutManager.HORIZONTAL,false);
        recyclerDanhMuc.setLayoutManager(layoutManager);
        recyclerDanhMuc.setAdapter(recyclerDanhMucCha);
        recyclerDanhMucCha.notifyDataSetChanged();
    }

    @Override
    public void LayDanhSachLoaiSPThatBai() {

    }

    @Override
    public void LayDanhSachThuongHieuThanhCong(List<ThuongHieu> thuongHieuList) {
        CustomRecyclerThuongHieu customRecyclerThuongHieu = new CustomRecyclerThuongHieu(getContext(),thuongHieuList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2, LinearLayoutManager.HORIZONTAL,false);
        recyclerThuongHieu.setLayoutManager(layoutManager);
        recyclerThuongHieu.setAdapter(customRecyclerThuongHieu);
        customRecyclerThuongHieu.notifyDataSetChanged();
    }

    @Override
    public void LayDanhSachThuongHieuThatBai() {

    }

    @Override
    public void HienThiDanhSachSanPhamPhoBien(List<SanPham> sanPhamList) {
        CustomReyclerSanPhamPhoBien customReyclerSanPhamPhoBien = new CustomReyclerSanPhamPhoBien(getActivity(),sanPhamList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerSanPhamPhoBien.setLayoutManager(layoutManager);
        recyclerSanPhamPhoBien.setAdapter(customReyclerSanPhamPhoBien);
        customReyclerSanPhamPhoBien.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachSanPhamMoiVe(List<SanPham> sanPhamList) {
        CustomReyclerSanPhamPhoBien customReyclerSanPhamPhoBien = new CustomReyclerSanPhamPhoBien(getActivity(),sanPhamList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        reyclcerSanPhamMoiVe.setLayoutManager(layoutManager);
        reyclcerSanPhamMoiVe.setAdapter(customReyclerSanPhamPhoBien);
        customReyclerSanPhamPhoBien.notifyDataSetChanged();
    }


    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {
        List<SanPham> tongSanPhamList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        for(int i = 0;i<khuyenMaiList.size();i++){
            List<SanPham> sanPhamList= khuyenMaiList.get(i).getDanhSachSanPhamKhuyenMai();
            if(sanPhamList.size()>0){
                for(int j = 0;j<sanPhamList.size();j++){
                    SanPham sanPham = sanPhamList.get(j);
                    tongSanPhamList.add(sanPham);
                }
            }

        }

        CustomRecyclerFlashSale customRecyclerFlashSale = new CustomRecyclerFlashSale(getContext(),tongSanPhamList);
        recyclerFlashSale.setLayoutManager(layoutManager);
        recyclerFlashSale.setAdapter(customRecyclerFlashSale);
        customRecyclerFlashSale.notifyDataSetChanged();
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

        ViewPagerAdapterSlide viewPagerAdapterSlide = new ViewPagerAdapterSlide(getActivity().getSupportFragmentManager(),fragmentSliderKhuyenMaiList);
        viewPagerSlider.setAdapter(viewPagerAdapterSlide);
        viewPagerAdapterSlide.notifyDataSetChanged();
        ThemDotSlider(0);
        viewPagerSlider.addOnPageChangeListener(this);//phai imple thang nay , phai imple,


    }

    private void ThemDotSlider(int vitrihientai){//vitri ban đầu dậu chám màu đỏ(vitrihientai), cai function nay làm sau ViewPager
        lnDot.removeAllViews();//tức là khi  qua 1 fragment mới thi no se go bo những view cũ
        txtDots = new TextView[fragmentSliderKhuyenMaiList.size()];

        for(int i = 0 ;i< fragmentSliderKhuyenMaiList.size(); i++){
            txtDots[i] = new TextView(getActivity());
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
            color = ContextCompat.getColor(getActivity(),IdColor);
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


}
