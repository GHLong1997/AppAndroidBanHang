package com.hangoclong.shop1.Model.ThanhToan.ThanhToan;

import android.util.Log;

import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
import com.hangoclong.shop1.Model.ObjectClass.ChiTietHoaDon;
import com.hangoclong.shop1.Model.ObjectClass.HoaDon;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 11/26/2017.
 */

public class ModelThanhToan {
    public boolean ThemHoaDon(HoaDon hoaDon){
        //ddau tieen ở thang object HoaDon chúng ta se duyet thang list<ChiTietHoaDOn> và chuyen no thanh 1 chuoi json
        boolean kiemtra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        String dataJSON ="";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();//lien quan ve php
        hsHam.put("ham","ThemHoaDon");

        //ko the put 1 mang len server dc nen ta phai chuyen ve chuoijson
        List<ChiTietHoaDon> chiTietHoaDonList  = hoaDon.getChiTietHoaDonList();//(đơn gian nhất la qua beautijson parse ra chuoi đẹp rroif lam lại như rk, nen vao php code vidu đã rôi mơi đem qua beautijson)
        String chuoijson = "{\"DANHSACHSANPHAM\" : [ ";
        for(int i = 0 ; i< chiTietHoaDonList.size();i++){

            chuoijson += "{";
            chuoijson += "\"masp\" : " +chiTietHoaDonList.get(i).getMaSP() + ","; //(maso chung ta tu dinh)
            chuoijson += "\"soluong\" : " + chiTietHoaDonList.get(i).getSoLuong();

            if(i == chiTietHoaDonList.size()-1){
                chuoijson += "}";//tương ung vs 1 jsonobject
            }else {
                chuoijson += "},";//tương ung vs 1 jsonobject, boi vi 1 thang json object cach 1 thang jonbjec la 1 dau phay
                //nhung mà khi kết thưc thăng object cuôi cung thi ko có dấu phảy nên ta phải set dk;
            }


        }
        chuoijson += "]}";
        Log.d("chuojson",chuoijson);

        HashMap<String,String> hsDanhSachSanPham = new HashMap<>();//lien quan ve php
        hsDanhSachSanPham.put("danhsachsanpham",chuoijson);

        HashMap<String,String> hsTenNguoiNhan = new HashMap<>();//lien quan ve php
        hsTenNguoiNhan.put("tennguoinhan",hoaDon.getTenNguoiNhan());

        HashMap<String,String> hsSoDT = new HashMap<>();//lien quan ve php
        hsSoDT.put("sodt",String.valueOf(hoaDon.getSoDT()));

        HashMap<String,String> hsDiaChi = new HashMap<>();//lien quan ve php
        hsDiaChi.put("diachi",hoaDon.getDiaChi());



        attrs.add(hsHam);
        attrs.add(hsDanhSachSanPham);
        attrs.add(hsTenNguoiNhan);
        attrs.add(hsSoDT);
        attrs.add(hsDiaChi);

        DownLoadJSON downloadJSON = new DownLoadJSON(duongdan,attrs);
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            Log.d("dulieu",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            String jsketqua = jsonObject.getString("ketqua");
            if(jsketqua.equals("true")){
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
