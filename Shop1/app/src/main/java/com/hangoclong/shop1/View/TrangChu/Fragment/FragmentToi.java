package com.hangoclong.shop1.View.TrangChu.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hangoclong.shop1.Model.DangNhap.ModelDangNhap;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DangNhap.DangNhap.DangNhapActivity;
import com.hangoclong.shop1.View.ThietLap.ThietLapActivity;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 10/15/2017.
 */

public class FragmentToi extends Fragment implements View.OnClickListener{
    public  static  int SELECT_PAGE =1;
    Button btnDangNhap,btnDangKy;
    TextView txtUser,txtNguoitheo,txtDangtheo;
    CircleImageView imDangNhap;
    ImageButton imSetup;
    String name,email;
    int[] lnlayout = {R.id.lnButton,R.id.lnButton1,R.id.lnButton2,R.id.lnButton4,R.id.lnButton5,R.id.lnButton6,R.id.lnButton7,R.id.lnButton8};
    int[] txtTieude = {R.id.txtTieude,R.id.txtTieude1,R.id.txtTieude2,R.id.txtTieude4,R.id.txtTieude5,R.id.txtTieude6,R.id.txtTieude7,R.id.txtTieude8};
    int[] txtEnd = {R.id.txtEnd,R.id.txtEnd1,R.id.txtEnd2,R.id.txtEnd4,R.id.txtEnd5,R.id.txtEnd6,R.id.txtEnd7,R.id.txtEnd8};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_toi,container,false);
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        btnDangKy = (Button) view.findViewById(R.id.btnDangky);
        imSetup = view.findViewById(R.id.imSetup);
        imDangNhap = view.findViewById(R.id.profile_image);
        txtUser = (TextView) view.findViewById(R.id.username);
        txtNguoitheo = (TextView) view.findViewById(R.id.txtnguoitheo);
        txtDangtheo = (TextView) view.findViewById(R.id.txtdangtheo);

        Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Regular.ttf");
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Regular.ttf");
        for(int i = 0 ;i<lnlayout.length;i++){
            TextView first = view.findViewById(txtTieude[i]);
            TextView second = view.findViewById(txtEnd[i]);
            first.setTypeface(font1);
            second.setTypeface(font2);
            LinearLayout btnLayout = view.findViewById(lnlayout[i]);
            btnLayout.setOnClickListener(this);
        }

        btnDangNhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
        imSetup.setOnClickListener(this);
        ModelDangNhap modelDangNhap = new ModelDangNhap();





        if(!TrangChuActivity.TENDANGNHAP.equals("")||!modelDangNhap.LayCacheDangNhap(getContext()).equals("")){
            txtUser.setVisibility(View.VISIBLE);
            txtNguoitheo.setVisibility(View.VISIBLE);
            txtDangtheo.setVisibility(View.VISIBLE);


            if(!TrangChuActivity.TENDANGNHAP.equals("")){
                txtUser.setText(TrangChuActivity.TENDANGNHAP);
                Picasso.with(getContext()).load("https://graph.facebook.com/"+TrangChuActivity.HINHANH +"/picture?type=large").resize(55,55).centerInside().into(imDangNhap);

            }else    if(!modelDangNhap.LayCacheDangNhap(getContext()).equals("")){
                String tendangnhap = modelDangNhap.LayCacheDangNhap(getContext());
                Log.d("ssss",tendangnhap);
                txtUser.setText(tendangnhap);
                Picasso.with(getContext()).load(R.drawable.acc).resize(55,55).centerInside().into(imDangNhap);
            }

            btnDangNhap.setVisibility(View.GONE);
            btnDangKy.setVisibility(View.GONE);
            imSetup.setVisibility(View.VISIBLE);
        }else {
            txtUser.setVisibility(View.GONE);
            txtNguoitheo.setVisibility(View.GONE);
            txtDangtheo.setVisibility(View.GONE);
            txtUser.setText(TrangChuActivity.TENDANGNHAP);
            Picasso.with(getContext()).load(R.drawable.acc).resize(55,55).centerInside().into(imDangNhap);
            imSetup.setVisibility(View.GONE);
            btnDangNhap.setVisibility(View.VISIBLE);
            btnDangKy.setVisibility(View.VISIBLE);



        }





        return  view;
    }



        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnDangNhap:
                    SELECT_PAGE=1;
                    Intent itDangNhap = new Intent(getActivity(), DangNhapActivity.class);
                    itDangNhap.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    itDangNhap.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(itDangNhap);
                    getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.fade_out);

                    break;
                case R.id.btnDangky:
                    SELECT_PAGE=0;
                    Intent itDangKy = new Intent(getActivity(), DangNhapActivity.class);
                    itDangKy.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    itDangKy.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(itDangKy);
                    getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.fade_out);

                    break;
                case R.id.imSetup:
                    Intent iThietLap = new Intent(getActivity(), ThietLapActivity.class);
                    startActivity(iThietLap);

                    getActivity().overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                    break;
            }
        }



}
