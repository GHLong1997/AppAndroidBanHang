package com.hangoclong.shop1.Model.DangNhap;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 10/19/2017.
 */

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;


    public  AccessToken LayTokenFacebookHienTai(){
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        accessToken =AccessToken.getCurrentAccessToken();//nếu như token cũ chưa mất thì nọ chạy cái này,
        return  accessToken;

    }

    public  String LayCacheDangNhap(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("tendangnhap",context.MODE_PRIVATE);// co thi no lay cai nay , ko thi dang ten moi
        String tendangnhap = cachedDangNhap.getString("tendangnhap","");//trong cach co tennv thi tra ra tennv , con ko thi mac dinh al rong
        Log.d("sad",tendangnhap);
        return tendangnhap;
    }

    public  void CapNhatCachedDangNhap(Context context,String tendangnhap){
        SharedPreferences cacheDangNhap = context.getSharedPreferences("tendangnhap",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cacheDangNhap.edit();
        editor.putString("tendangnhap",tendangnhap);
        editor.commit();
    }

    public boolean DangNhapTaiKhoan(Context context,String tendangnhap ,String matkhau , String email, String sodt){
        boolean kiemtra = false;
        String duongdan  = TrangChuActivity.SERVER_NAME;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        Log.d("aaaa",tendangnhap  + " " + matkhau + " " + sodt + " " + email );
        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","DangNhapTaiKhoan");

        HashMap<String,String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap",tendangnhap);

        HashMap<String,String> hsEmail = new HashMap<>();
        hsEmail.put("email",email);

        HashMap<String,String> hsSodt = new HashMap<>();
        hsSodt.put("sodt",sodt);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",matkhau);

        attrs.add(hsHam);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);
        attrs.add(hsEmail);
        attrs.add(hsSodt);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        downLoadJSON.execute();
        try {
            String dataJSON = downLoadJSON.get();
            Log.d("kiemtra",dataJSON.toString());
            JSONObject jsonObject = new JSONObject(dataJSON);
            String ketqua = jsonObject.getString("ketqua");

            if(ketqua.equals("true")){

                CapNhatCachedDangNhap(context,jsonObject.getString("tendangnhap"));
                kiemtra = true;
            }else {
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  kiemtra;
    }


}
