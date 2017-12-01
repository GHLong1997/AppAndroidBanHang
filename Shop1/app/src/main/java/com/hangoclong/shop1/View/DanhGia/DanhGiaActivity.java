package com.hangoclong.shop1.View.DanhGia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.DanhGia;
import com.hangoclong.shop1.Presenter.DanhGia.PresenterLogicDanhGia;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment1Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment2Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment3Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment4Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.Fragment5Sao;
import com.hangoclong.shop1.View.DanhGia.Fragment.FragmentBL;
import com.hangoclong.shop1.View.DanhGia.Fragment.FragmentHA;
import com.hangoclong.shop1.View.DanhGia.Fragment.FragmentTatCa;

import java.util.List;

public class DanhGiaActivity extends AppCompatActivity implements  ViewDanhGia,View.OnClickListener{
    Toolbar toolbar;
    PresenterLogicDanhGia presenterLogicDanhGia;
    LinearLayout lnDanhGiaTatCa,lnDanhGiaBL,lnDanhGiaHA,lnDanhGia1Sao,lnDanhGia2Sao,lnDanhGia3Sao,lnDanhGia4Sao,lnDanhGia5Sao,lnDanhGia;
    TextView txt1Sao,txt2Sao,txt3Sao,txt4Sao,txt5Sao,txtTatCa,txtLuotBL,txtHA,txtTatCaText,txtCoBinhLuanText,txtCoHinhAnhText;
    android.support.v4.app.FragmentManager fragmentManager;
    Fragment fragment;
    int masp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lnDanhGia = (LinearLayout) findViewById(R.id.lnDanhGia);
        lnDanhGiaTatCa = (LinearLayout) findViewById(R.id.lnDanhGiaTatCa);
        lnDanhGiaBL = (LinearLayout) findViewById(R.id.lntDanhGiaBL);
        lnDanhGiaHA = (LinearLayout) findViewById(R.id.lnDanhGiaHA);
        lnDanhGia1Sao = (LinearLayout) findViewById(R.id.lnDanhGia1Sao);
        lnDanhGia2Sao = (LinearLayout) findViewById(R.id.lnDanhGia2Sao);
        lnDanhGia3Sao = (LinearLayout) findViewById(R.id.lnDanhGia3Sao);
        lnDanhGia4Sao = (LinearLayout) findViewById(R.id.lnDanhGia4Sao);
        lnDanhGia5Sao = (LinearLayout) findViewById(R.id.lnDanhGia5Sao);
        txt1Sao = (TextView) findViewById(R.id.txt1Sao);
        txt2Sao = (TextView) findViewById(R.id.txt2Sao);
        txt3Sao = (TextView) findViewById(R.id.txt3Sao);
        txt4Sao = (TextView) findViewById(R.id.txt4Sao);
        txt5Sao = (TextView) findViewById(R.id.txt5Sao);
        txtTatCa = (TextView) findViewById(R.id.txtTatCa);
        txtLuotBL = (TextView) findViewById(R.id.txtLuotBL);
        txtHA = (TextView) findViewById(R.id.txtHA);
        txtTatCaText = (TextView) findViewById(R.id.txtTatCatext);
        txtCoBinhLuanText = (TextView) findViewById(R.id.txtCoBinhLuantext);
        txtCoHinhAnhText = (TextView) findViewById(R.id.txtCoHinhAnhtext);
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        lnDanhGiaTatCa.setOnClickListener(this);
        lnDanhGiaBL.setOnClickListener(this);
        lnDanhGiaHA.setOnClickListener(this);
        lnDanhGia1Sao.setOnClickListener(this);
        lnDanhGia2Sao.setOnClickListener(this);
        lnDanhGia3Sao.setOnClickListener(this);
        lnDanhGia4Sao.setOnClickListener(this);
        lnDanhGia5Sao.setOnClickListener(this);

        toolbar.setTitle("Đánh giá sản phẩm");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        masp = getIntent().getIntExtra("masp",0);
        if(masp!=0){

            presenterLogicDanhGia.LayDanhSachDanhDanh(masp);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGias) {
//    mặc định cho thằng fragment này load trước
        lnDanhGiaTatCa.setBackgroundResource(R.drawable.custom_btn_danhgiaselected);
        txtTatCa.setTextColor(getResources().getColor(R.color.bgToolbar));
        txtTatCaText.setTextColor(getResources().getColor(R.color.bgToolbar));
        fragment = new FragmentTatCa();
        Bundle bundle = new Bundle();
        bundle.putInt("masp",masp);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lnDanhGia,fragment);
        fragmentTransaction.commit();


    }

    @Override
    public void HienThiTongDanhGia(List<DanhGia> danhGiaList) {
        int one=0,two=0,three=0,four=0,five=0;
        int tongBL = 0;
        for(int i = 0 ; i < danhGiaList.size();i++){

            if(danhGiaList.get(i).getSOSAO()==1){
                one++;
            }
            if(danhGiaList.get(i).getSOSAO()==2){
                two++;
            }
            if(danhGiaList.get(i).getSOSAO()==3){
                three++;
            }
            if(danhGiaList.get(i).getSOSAO()==4){
                four++;
            }
            if(danhGiaList.get(i).getSOSAO()==5){
                five++;
            }
            if(!danhGiaList.get(i).getNOIDUNG().equals("")){
                tongBL+=1;
            }
        }

        txtTatCa.setText("("+danhGiaList.size()+")");
        txtLuotBL.setText("("+tongBL+")");
        txt1Sao.setText("("+one+")");
        txt2Sao.setText("("+two+")");
        txt3Sao.setText("("+three+")");
        txt4Sao.setText("("+four+")");
        txt5Sao.setText("("+five+")");

    }

    public  void Selected(int id ){
       if(lnDanhGiaTatCa.getId() == id){
           lnDanhGiaTatCa.setBackgroundResource(R.drawable.custom_btn_danhgiaselected);
           lnDanhGiaBL.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaHA.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia1Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia2Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia3Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia4Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia5Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           txtTatCa.setTextColor(getResources().getColor(R.color.bgToolbar));
           txtLuotBL.setTextColor(getResources().getColor(R.color.xam));
           txtHA.setTextColor(getResources().getColor(R.color.xam));
           txt1Sao.setTextColor(getResources().getColor(R.color.xam));
           txt2Sao.setTextColor(getResources().getColor(R.color.xam));
           txt3Sao.setTextColor(getResources().getColor(R.color.xam));
           txt4Sao.setTextColor(getResources().getColor(R.color.xam));
           txt5Sao.setTextColor(getResources().getColor(R.color.xam));
           txtTatCaText.setTextColor(getResources().getColor(R.color.bgToolbar));
           txtCoHinhAnhText.setTextColor(getResources().getColor(R.color.xam));
           txtCoBinhLuanText.setTextColor(getResources().getColor(R.color.xam));
       }else  if(lnDanhGiaBL.getId() == id){

           lnDanhGiaBL.setBackgroundResource(R.drawable.custom_btn_danhgiaselected);
           lnDanhGiaTatCa.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaHA.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia1Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia2Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia3Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia4Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia5Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           txtLuotBL.setTextColor(getResources().getColor(R.color.bgToolbar));
           txtTatCa.setTextColor(getResources().getColor(R.color.xam));
           txtHA.setTextColor(getResources().getColor(R.color.xam));
           txt1Sao.setTextColor(getResources().getColor(R.color.xam));
           txt2Sao.setTextColor(getResources().getColor(R.color.xam));
           txt3Sao.setTextColor(getResources().getColor(R.color.xam));
           txt4Sao.setTextColor(getResources().getColor(R.color.xam));
           txt5Sao.setTextColor(getResources().getColor(R.color.xam));
           txtCoBinhLuanText.setTextColor(getResources().getColor(R.color.bgToolbar));
           txtCoHinhAnhText.setTextColor(getResources().getColor(R.color.xam));
           txtTatCaText.setTextColor(getResources().getColor(R.color.xam));
       }else if(lnDanhGiaHA.getId() == id) {

            lnDanhGiaHA.setBackgroundResource(R.drawable.custom_btn_danhgiaselected);
            lnDanhGiaTatCa.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGiaBL.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGia1Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGia2Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGia3Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGia4Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGia5Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            txtHA.setTextColor(getResources().getColor(R.color.bgToolbar));
            txtTatCa.setTextColor(getResources().getColor(R.color.xam));
            txtLuotBL.setTextColor(getResources().getColor(R.color.xam));
            txt1Sao.setTextColor(getResources().getColor(R.color.xam));
            txt2Sao.setTextColor(getResources().getColor(R.color.xam));
            txt3Sao.setTextColor(getResources().getColor(R.color.xam));
            txt4Sao.setTextColor(getResources().getColor(R.color.xam));
            txt5Sao.setTextColor(getResources().getColor(R.color.xam));
           txtCoHinhAnhText.setTextColor(getResources().getColor(R.color.bgToolbar));
           txtTatCaText.setTextColor(getResources().getColor(R.color.xam));
           txtCoBinhLuanText.setTextColor(getResources().getColor(R.color.xam));
        }else  if(lnDanhGia1Sao.getId() == id) {
           lnDanhGia1Sao.setBackgroundResource(R.drawable.custom_btn_danhgiaselected);
           lnDanhGiaTatCa.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaHA.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaBL.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia2Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia3Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia4Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia5Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           txt1Sao.setTextColor(getResources().getColor(R.color.bgToolbar));
           txtTatCa.setTextColor(getResources().getColor(R.color.xam));
           txtHA.setTextColor(getResources().getColor(R.color.xam));
           txtLuotBL.setTextColor(getResources().getColor(R.color.xam));
           txt2Sao.setTextColor(getResources().getColor(R.color.xam));
           txt3Sao.setTextColor(getResources().getColor(R.color.xam));
           txt4Sao.setTextColor(getResources().getColor(R.color.xam));
           txt5Sao.setTextColor(getResources().getColor(R.color.xam));
           txtCoHinhAnhText.setTextColor(getResources().getColor(R.color.xam));
           txtTatCaText.setTextColor(getResources().getColor(R.color.xam));
           txtCoBinhLuanText.setTextColor(getResources().getColor(R.color.xam));
       }else  if(lnDanhGia2Sao.getId() == id) {
           lnDanhGia2Sao.setBackgroundResource(R.drawable.custom_btn_danhgiaselected);
           lnDanhGiaTatCa.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaHA.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaBL.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia1Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia3Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia4Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia5Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           txt2Sao.setTextColor(getResources().getColor(R.color.bgToolbar));
           txtTatCa.setTextColor(getResources().getColor(R.color.xam));
           txtHA.setTextColor(getResources().getColor(R.color.xam));
           txt1Sao.setTextColor(getResources().getColor(R.color.xam));
           txtLuotBL.setTextColor(getResources().getColor(R.color.xam));
           txt3Sao.setTextColor(getResources().getColor(R.color.xam));
           txt4Sao.setTextColor(getResources().getColor(R.color.xam));
           txt5Sao.setTextColor(getResources().getColor(R.color.xam));
           txtCoHinhAnhText.setTextColor(getResources().getColor(R.color.xam));
           txtTatCaText.setTextColor(getResources().getColor(R.color.xam));
           txtCoBinhLuanText.setTextColor(getResources().getColor(R.color.xam));
       }else  if(lnDanhGia3Sao.getId() == id) {
            lnDanhGia3Sao.setBackgroundResource(R.drawable.custom_btn_danhgiaselected);
            lnDanhGiaTatCa.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGiaHA.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGiaBL.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGia1Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGia2Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGia4Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            lnDanhGia5Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
            txt3Sao.setTextColor(getResources().getColor(R.color.bgToolbar));
            txtTatCa.setTextColor(getResources().getColor(R.color.xam));
            txtHA.setTextColor(getResources().getColor(R.color.xam));
            txt1Sao.setTextColor(getResources().getColor(R.color.xam));
            txt2Sao.setTextColor(getResources().getColor(R.color.xam));
            txtLuotBL.setTextColor(getResources().getColor(R.color.xam));
            txt4Sao.setTextColor(getResources().getColor(R.color.xam));
            txt5Sao.setTextColor(getResources().getColor(R.color.xam));
           txtCoHinhAnhText.setTextColor(getResources().getColor(R.color.xam));
           txtTatCaText.setTextColor(getResources().getColor(R.color.xam));
           txtCoBinhLuanText.setTextColor(getResources().getColor(R.color.xam));
        }else  if(lnDanhGia4Sao.getId() == id) {
           lnDanhGia4Sao.setBackgroundResource(R.drawable.custom_btn_danhgiaselected);
           lnDanhGiaTatCa.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaHA.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaBL.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia1Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia3Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia2Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia5Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           txt4Sao.setTextColor(getResources().getColor(R.color.bgToolbar));
           txtTatCa.setTextColor(getResources().getColor(R.color.xam));
           txtHA.setTextColor(getResources().getColor(R.color.xam));
           txt1Sao.setTextColor(getResources().getColor(R.color.xam));
           txt2Sao.setTextColor(getResources().getColor(R.color.xam));
           txt3Sao.setTextColor(getResources().getColor(R.color.xam));
           txtLuotBL.setTextColor(getResources().getColor(R.color.xam));
           txt5Sao.setTextColor(getResources().getColor(R.color.xam));
           txtCoHinhAnhText.setTextColor(getResources().getColor(R.color.xam));
           txtTatCaText.setTextColor(getResources().getColor(R.color.xam));
           txtCoBinhLuanText.setTextColor(getResources().getColor(R.color.xam));
       }else  if(lnDanhGia5Sao.getId() == id) {
           lnDanhGia5Sao.setBackgroundResource(R.drawable.custom_btn_danhgiaselected);
           lnDanhGiaTatCa.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaHA.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGiaBL.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia1Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia3Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia4Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           lnDanhGia2Sao.setBackgroundResource(R.drawable.custom_btndanhgia_notselect);
           txt5Sao.setTextColor(getResources().getColor(R.color.bgToolbar));
           txtTatCa.setTextColor(getResources().getColor(R.color.xam));
           txtHA.setTextColor(getResources().getColor(R.color.xam));
           txt1Sao.setTextColor(getResources().getColor(R.color.xam));
           txt2Sao.setTextColor(getResources().getColor(R.color.xam));
           txt3Sao.setTextColor(getResources().getColor(R.color.xam));
           txt4Sao.setTextColor(getResources().getColor(R.color.xam));
           txtLuotBL.setTextColor(getResources().getColor(R.color.xam));
           txtCoHinhAnhText.setTextColor(getResources().getColor(R.color.xam));
           txtTatCaText.setTextColor(getResources().getColor(R.color.xam));
           txtCoBinhLuanText.setTextColor(getResources().getColor(R.color.xam));
       }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lnDanhGiaTatCa:
                Selected(R.id.lnDanhGiaTatCa);
                fragment = new FragmentTatCa();



                break;
            case R.id.lntDanhGiaBL:
                Selected(R.id.lntDanhGiaBL);
                fragment = new FragmentBL();

                break;
            case R.id.lnDanhGiaHA:
                Selected(R.id.lnDanhGiaHA);
                fragment = new FragmentHA();

                break;
            case R.id.lnDanhGia1Sao:
                Selected(R.id.lnDanhGia1Sao);
                fragment = new Fragment1Sao();

                break;
            case R.id.lnDanhGia2Sao:
                Selected(R.id.lnDanhGia2Sao);
                fragment = new Fragment2Sao();

                break;
            case R.id.lnDanhGia3Sao:
                Selected(R.id.lnDanhGia3Sao);
                fragment = new Fragment3Sao();

                break;
            case R.id.lnDanhGia4Sao:
                Selected(R.id.lnDanhGia4Sao);
                fragment = new Fragment4Sao();

                break;
            case R.id.lnDanhGia5Sao:
                Selected(R.id.lnDanhGia5Sao);
                fragment = new Fragment5Sao();
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("masp",masp);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lnDanhGia,fragment);
        fragmentTransaction.commit();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }
}
