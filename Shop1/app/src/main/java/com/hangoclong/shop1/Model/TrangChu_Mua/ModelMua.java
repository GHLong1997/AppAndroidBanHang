package com.hangoclong.shop1.Model.TrangChu_Mua;

import android.util.Log;

import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Model.ObjectClass.ThuongHieu;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 10/30/2017.
 */

public class ModelMua {
    public List<LoaiSanPham> LayDanhSachLoaiSanPhamCha(String ham, String tenmangJSON){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",ham);

        attrs.add(hsHam);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);

        downLoadJSON.execute();
        try {
            String dataJSON = downLoadJSON.get();
            Log.d("kiemtra1",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmangJSON);

            int count = jsonArray.length();
            for(int i = 0 ; i < count; i++){
                LoaiSanPham loaiSanPham = new LoaiSanPham();
                JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                loaiSanPham.setMALOAISP(jsonObject1.getInt("MALOAISP"));
                loaiSanPham.setTENLOAISP(jsonObject1.getString("TENLOAISP"));
                loaiSanPham.setMALOAICHA(jsonObject1.getInt("MALOAICHA"));
                loaiSanPham.setHINHLOAISP(jsonObject1.getString("HINHLOAISP"));

                loaiSanPhamList.add(loaiSanPham);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;
    }

    public List<ThuongHieu> LayDanhSachThuongHieu(String ham, String tenmangJSON){
        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",ham);

        attrs.add(hsHam);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);

        downLoadJSON.execute();
        try {
            String dataJSON = downLoadJSON.get();
            Log.d("kiemtra1",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmangJSON);

            int count = jsonArray.length();
            for(int i = 0 ; i < count; i++){
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                thuongHieu.setMaThuongHieu(jsonObject1.getInt("MATHUONGHIEU"));
                thuongHieu.setTenThuongHieu(jsonObject1.getString("TENTHUONGHIEU"));
                thuongHieu.setHinhThuongHieu(jsonObject1.getString("HINHTHUONGHIEU"));
                thuongHieu.setLuotMua(jsonObject1.getInt("LUOTMUA"));

                thuongHieuList.add(thuongHieu);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return thuongHieuList;
    }

    public List<SanPham> LayDanhSachSanPhamPhoBien(String ham, String tenmangJSON){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",ham);

        attrs.add(hsHam);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);

        downLoadJSON.execute();
        try {
            String dataJSON = downLoadJSON.get();
            Log.d("kiemtra1",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmangJSON);

            int count = jsonArray.length();
            for(int i = 0 ; i < count; i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(jsonObject1.getInt("MASP"));
                sanPham.setTenSP(jsonObject1.getString("TENSP"));
                sanPham.setSoluong(jsonObject1.getInt("SOLUONG"));
                sanPham.setGia(jsonObject1.getInt("GIA"));
                sanPham.setLuotMua(jsonObject1.getInt("LUOTMUA"));
                sanPham.setAnhLon(jsonObject1.getString("ANHLON"));
                sanPhamList.add(sanPham);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }




}
