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
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hangoclong.shop1.Model.ObjectClass.NhanVien;
import com.hangoclong.shop1.Presenter.DangNhap.PresenterLogicDangNhap;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DangNhap.DangKy.ActivityDangKySMS;
import com.hangoclong.shop1.View.DangNhap.ViewDangNhap;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import java.util.Arrays;

/**
 * Created by Admin on 10/16/2017.
 */

public class Fragment_dangnhap extends Fragment implements View.OnClickListener,ViewDangNhap{
    LinearLayout lndangnhapfb, lndangnhapsms;
    CallbackManager callbackManager;
    EditText edDangNhap,edMatKhau;
    Button btnDangNhap;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    PresenterLogicDangNhap presenterLogicDangNhap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(this.getContext());
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);
        mAuth = FirebaseAuth.getInstance();
        edDangNhap =  view.findViewById(R.id.edDangNhap);
        edMatKhau = view.findViewById(R.id.edMatKhau);
        btnDangNhap =view.findViewById(R.id.btndangnhap);




        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getActivity().getApplicationContext(), TrangChuActivity.class);
                startActivity(intent);
            }
            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//
//
//                } else {
//                    // User is signed out
//
//                }
//                // ...
//            }
//        };


        lndangnhapfb = view.findViewById(R.id.lndangnhapfacebook);
        lndangnhapsms = view.findViewById(R.id.lndangnhapsms);
        lndangnhapfb.setOnClickListener(this);
        lndangnhapsms.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        return view;
    }


//    public  void dangnhap(final String tenDangNhap, String pass){
//        mAuth.signInWithEmailAndPassword(tenDangNhap,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Intent iTrangchu = new Intent(getActivity(), TrangChuActivity.class);
//                    iTrangchu.putExtra("tendangnhap",tenDangNhap);
//                    startActivity(iTrangchu);
//
//
//                }else {
//                    Toast.makeText(getActivity(),"Tên đăng nhập hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lndangnhapfacebook:
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                break;
            case R.id.btndangnhap:
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTenDN(edDangNhap.getText().toString());
                nhanVien.setMatKhau(edMatKhau.getText().toString());
                nhanVien.setEmail(edDangNhap.getText().toString());
                nhanVien.setSodt(edDangNhap.getText().toString());
                presenterLogicDangNhap = new PresenterLogicDangNhap(this.getContext(),this);
                presenterLogicDangNhap.DangNhapTaiKhoan(nhanVien);

//              dangnhap(edDangNhap.getText().toString(),edMatKhau.getText().toString());
                break;
            case R.id.lndangnhapsms:
                Intent idangkysms = new Intent(getActivity(), ActivityDangKySMS.class);
                idangkysms.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                idangkysms.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(idangkysms);
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.fade_out);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }


    @Override
    public void DangNhapThanhCong() {
        Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Intent iTrangchu = new Intent(getActivity(), TrangChuActivity.class);
        startActivity(iTrangchu);
    }

    @Override
    public void DangNhapThatBai() {
        Toast.makeText(getActivity(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
    }
}
