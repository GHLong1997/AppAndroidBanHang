package com.hangoclong.shop1.View.DanhGia;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.hangoclong.shop1.Model.ObjectClass.DanhGia;
import com.hangoclong.shop1.Presenter.DanhGia.PresenterLogicDanhGia;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import java.util.List;

/**
 * Created by Admin on 11/11/2017.
 */

public class ThemDanhGiaActivity extends AppCompatActivity implements  RatingBar.OnRatingBarChangeListener,View.OnClickListener,ViewDanhGia{
    TextInputLayout  input_edNoiDung;
    EditText edTieuDe,edNoiDung;
    RatingBar ratingBar;
    int masp=0;
    double sosao = 0;
    Button btnDongYDanhGia;
    PresenterLogicDanhGia presenterLogicDanhGia;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themdanhgia);

        input_edNoiDung = (TextInputLayout) findViewById(R.id.input_edNoiDungDanhGia);
        edNoiDung = (EditText) findViewById(R.id.edNoiDung);
        ratingBar = (RatingBar) findViewById(R.id.rbDanhGia);
        btnDongYDanhGia = (Button) findViewById(R.id.btnDongYDanhGia);


        masp = getIntent().getIntExtra("masp",0);
        Log.d("Sad",masp+" "+ TrangChuActivity.TENDANGNHAP);
        ratingBar.setOnRatingBarChangeListener(this);

        btnDongYDanhGia.setOnClickListener(this);
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);



    }


    // android:theme="@style/Animation.AppCompat.Dialog" them vao , hien thi popup
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {

        sosao = (double) rating;
    }


    @Override
    public void onClick(View view) {
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        String madg = telephonyManager.getDeviceId();//lây dc IDthiết bi
        String madg = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);

        String tenthietbi = Build.MODEL;
        Log.d("mmmmmm",madg +" " + tenthietbi);
        String noidung = edNoiDung.getText().toString();


        if(noidung.trim().length() >0){
            input_edNoiDung.setErrorEnabled(false);
            input_edNoiDung.setError(null);
        }else{

            input_edNoiDung.setErrorEnabled(true);
            input_edNoiDung.setError("Bạn chưa nhập nội dung");
        }

        if(!input_edNoiDung.isErrorEnabled() ){
            DanhGia danhGia = new DanhGia();
            danhGia.setMADG(madg);
            danhGia.setMASP(masp);
            danhGia.setTENDANGNHAP(TrangChuActivity.TENDANGNHAP);
            danhGia.setHINHDANGNHAP(TrangChuActivity.HINHANH);
            danhGia.setSOSAO(sosao);
            danhGia.setNOIDUNG(edNoiDung.getText().toString());
            danhGia.setTENTHIETBI(tenthietbi);
            presenterLogicDanhGia.ThemDanhGia(danhGia);

            finish();// đóng khung đánh giá này lại
            //qua ben layout dung theme set mau cho ngôi sao
        }

    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this,"Đánh giá thành công!",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this,"Bạn đã đánh giá sản phẩm này!!!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGias) {

    }

    @Override
    public void HienThiTongDanhGia(List<DanhGia> danhGiaList) {

    }
}
