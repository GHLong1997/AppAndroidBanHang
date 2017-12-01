package com.hangoclong.shop1.View.DangNhap.DangKy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hangoclong.shop1.R;

import java.util.concurrent.TimeUnit;

public class ActivityDangkySdt extends AppCompatActivity implements View.OnClickListener{
    Button btnTiepTuc,btnGuiLai;

    EditText edMaXacMinh;
    Toolbar toolbar;
    String sdt;
    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationStateChangedCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendingToken;

    private FirebaseAuth firebaseAuth;
    private static  final  String TAG ="PhoneAuth";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky_sdt);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnTiepTuc = (Button) findViewById(R.id.btnTieptuc);
        btnGuiLai = (Button) findViewById(R.id.btnGuiLai);
        edMaXacMinh = (EditText) findViewById(R.id.edMaXacMinh);

        toolbar.setTitle("Mã xác minh");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
        if(!getIntent().getStringExtra("sdt").equals("")){
            sdt = getIntent().getStringExtra("sdt");
        }
        sendCode();


        btnGuiLai.setOnClickListener(this);
        btnTiepTuc.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnTieptuc:
//                Intent iDangky2 = new Intent(this, ActivityDangky2.class);
//                iDangky2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                iDangky2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                startActivity(iDangky2);

                verifyCode();
                break;
            case R.id.btnGuiLai:
                resendCode();
                break;
        }
    }


    public   void  sendCode(){
        //Gửi mã xác minh đến điện thoại của người dùng
        //nếu đã xác minh thì nó iteens hnah dang nhâp , con chưa thi no gui code xac minh
        setUpVerificationCallbacks();// ta gọi hàm này đâu là để xem t đa đăng ký sdt hay chưa(muôn đăng ký thì cho vẻificationStaeChangeCallBback vào thằng dưới
        //nếu đăng ký rồi thì nó login luôn khi bấm sendCode
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                sdt,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationStateChangedCallbacks);        // OnVerificationStateChangedCallbacks,
    }

    private  void setUpVerificationCallbacks(){
        //Khi bạn gọi PhoneAuthProvider.verifyPhoneNumber, bạn cũng phải cung cấp một instance of của OnVerificationStateChangedCallbacks,
        // chứa các cài đặt các hàm gọi lại để xử lý các kết quả của yêu cầu
        verificationStateChangedCallbacks =  new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {


                edMaXacMinh.setText("");
                signInWithPhoneAuthCredential(phoneAuthCredential);
                Intent intent = new Intent(ActivityDangkySdt.this,ActivityDangky2.class);
                intent.putExtra("sdt",sdt);
                startActivity(intent);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if(e instanceof FirebaseAuthInvalidCredentialsException){
                    Log.d(TAG, "Invalid credential " + e.getLocalizedMessage());
                }else if(e instanceof FirebaseTooManyRequestsException){
                    //SMS quote exceeded
                    Log.d(TAG, "SMS quote exceeded " );
                }
            }



            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                super.onCodeSent(verificationId, forceResendingToken);

                phoneVerificationId =  verificationId;//id xac thuc sdt
                resendingToken = forceResendingToken;//1 cai token gui lai

            }
        };

    }

    public  void verifyCode(){
        String code = edMaXacMinh.getText().toString();

        //Sau khi người dùng nhập mã xác minh mà Firebase đã gửi đến điện thoại của người dùng,
        // hãy tạo một PhoneAuthCredentialđối tượng, sử dụng mã xác minh và ID xác minh đã được chuyển đến cuộc gọi lại onCodeSent hoặc
        // onCodeAutoRetrievalTimeOutgọi lại.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId,code);
        signInWithPhoneAuthCredential(credential);

    }


    //Sau khi bạn nhận được một PhoneAuthCredentialđối tượng, dù là trong onVerificationCompleted callback  hay bằng cách gọi PhoneAuthProvider.getCredential,
    // hãy hoàn thành luồng đăng nhập bằng cách truyền đối tượng PhoneAuthCredential tới FirebaseAuth.signInWithCredential:
    private  void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
//                    edMaXacMinh.setText("");
//                    FirebaseUser user= task.getResult().getUser();

                }else {
                    if(task.getException() instanceof  FirebaseAuthInvalidCredentialsException){
                        //The verification code entered invalid
                    }
                }
            }
        });

    }

    public  void resendCode(){

        setUpVerificationCallbacks();
        //Gửi lại mã xác minh đến điện thoại của người dùng
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                sdt,        // Phone number to verify
                30,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationStateChangedCallbacks,resendingToken);
    }

    public  void signOut(View view){
        firebaseAuth.signOut();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }


}
