package com.hangoclong.shop1.View.DangNhap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hangoclong.shop1.Model.ObjectClass.NhanVien;
import com.hangoclong.shop1.Presenter.DangNhap.PresenterLogicDangNhap;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DangNhap.DangKy.ActivityDangKy1;
import com.hangoclong.shop1.View.DangNhap.DangKy.ActivityDangkySdt;
import com.hangoclong.shop1.View.DangNhap.DiaLogDangNhapActivity;
import com.hangoclong.shop1.View.DangNhap.ViewDangNhap;

/**
 * Created by Admin on 10/16/2017.
 */

public class Fragment_dangky extends Fragment implements ViewDangNhap{
    LinearLayout lnDangkyEmail;
    Button btnTiepTuc;
    EditText edSdt;
    private static  final  String TAG ="PhoneAuth";
    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationStateChangedCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendingToken;
    private FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky,container,false);
        lnDangkyEmail = view.findViewById(R.id.lnDangkyEmail);
        btnTiepTuc = view.findViewById(R.id.btnTiepTuc);
        edSdt = view.findViewById(R.id.edSdt);
        final PresenterLogicDangNhap presenterLogicDangNhap = new PresenterLogicDangNhap(getActivity(),this);
        lnDangkyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), ActivityDangKy1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.fade_out);
                startActivity(intent);
            }
        });

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NhanVien nhanVien = new NhanVien();
                nhanVien.setSodt(edSdt.getText().toString());
                nhanVien.setMaLoaiNV(2);
                nhanVien.setEmail("");
                nhanVien.setMatKhau("");
                nhanVien.setTenDN("");
                presenterLogicDangNhap.DangNhapTaiKhoan(nhanVien);




            }
        });
        return  view;
    }


    @Override
    public void DangNhapThanhCong() {
        Intent intent = new Intent(getActivity(), DiaLogDangNhapActivity.class);
        startActivity(intent);

    }

    @Override
    public void DangNhapThatBai() {
        if(!edSdt.equals("")){
            Intent inten1 = new Intent(getActivity(), ActivityDangkySdt.class);
            inten1.putExtra("sdt",edSdt.getText().toString());
            inten1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            inten1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(inten1);
            getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.fade_out);
        }
    }

}
