package com.hangoclong.shop1.Model.DanhMucActivity;

import android.util.Log;

import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
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
 * Created by Admin on 10/30/2017.
 */

public class ModelDanhMuc {
    public List<LoaiSanPham> LayDanhSachLoaiSanPhamCon(String ham, String tenmangJSON,int maloaisp){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",ham);

        HashMap<String,String> hsMaLoaiSP = new HashMap<>();
        hsMaLoaiSP.put("maloaisp",String.valueOf(maloaisp));

        attrs.add(hsHam);
        attrs.add(hsMaLoaiSP);

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

    public List<SanPham> LayKhoangGiaTienTopDienThoaiBanChay(String ham, String tenmangJSON){
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
            Log.d("kiemtra12",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmangJSON);

            int count = jsonArray.length();
            for(int i = 0 ; i < count; i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
               SanPham sanPham = new SanPham();
                sanPham.setMaSP(jsonObject1.getInt("MASP"));
                sanPham.setTenSP(jsonObject1.getString("TENSP"));
                sanPham.setGia(jsonObject1.getInt("GIA"));
                sanPham.setAnhLon(jsonObject1.getString("ANHLON"));
                sanPham.setLuotMua(jsonObject1.getInt("LUOTMUA"));
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
