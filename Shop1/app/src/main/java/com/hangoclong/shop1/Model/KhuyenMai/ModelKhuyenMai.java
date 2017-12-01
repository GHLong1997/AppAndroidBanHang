package com.hangoclong.shop1.Model.KhuyenMai;

import android.util.Log;

import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
import com.hangoclong.shop1.Model.ObjectClass.ChiTietKhuyenMai;
import com.hangoclong.shop1.Model.ObjectClass.KhuyenMai;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 11/1/2017.
 */

public class ModelKhuyenMai {
    public List<KhuyenMai> LayDanhSachKhuyenMai(String tenham,String tenmangJSON ){
        List<KhuyenMai> khuyenMaiList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String dataJSON ="";
        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();//lien quan ve php
        hsHam.put("ham",tenham);



        attrs.add(hsHam);

        DownLoadJSON downloadJSON = new DownLoadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();//co nguyên 1 đông du lieu trên kia rồi
            Log.d("kkkkk",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachKhuyenMai = jsonObject.getJSONArray(tenmangJSON);
            int dem = jsonArrayDanhSachKhuyenMai.length();
            for(int i = 0 ;i < dem ; i++){//duyet tung phan tu trong jsarray
                JSONObject object = jsonArrayDanhSachKhuyenMai.getJSONObject(i);
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMaKM(object.getInt("MAKM"));
                khuyenMai.setTenKM(object.getString("TENKM"));
                khuyenMai.setTenLoaiSP(object.getString("TENLOAISP"));
                khuyenMai.setHinhKhuyenMai(TrangChuActivity.SERVER + object.getString("HINHKHUYENMAI"));
                Log.d("sssa",khuyenMai.getHinhKhuyenMai());

                List<SanPham> sanPhamList = new ArrayList<>();
                JSONArray arrayDanhSachSanPham = object.getJSONArray("DANHSACHSANPHAM");
                int  demsanpham = arrayDanhSachSanPham.length();
                for(int j = 0 ; j< demsanpham;j++){
                    JSONObject objectSanPham = arrayDanhSachSanPham.getJSONObject(j);

                    SanPham sanPham = new SanPham();
                    sanPham.setMaSP(objectSanPham.getInt("MASP"));
                    sanPham.setTenSP(objectSanPham.getString("TENSP"));
                    sanPham.setGia(objectSanPham.getInt("GIA"));
                    sanPham.setAnhLon(TrangChuActivity.SERVER + objectSanPham.getString("ANHLON"));
                    sanPham.setAnhNho(objectSanPham.getString("ANHNHO"));

                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPhanTramKM(objectSanPham.getInt("PHANTRAMKM"));

                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
                    sanPhamList.add(sanPham);

                }
                khuyenMai.setDanhSachSanPhamKhuyenMai(sanPhamList);
                khuyenMaiList.add(khuyenMai);


            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  khuyenMaiList;// chúng ta đa có đc du liêu parse. tiếp theo qua presenter

    }
}
