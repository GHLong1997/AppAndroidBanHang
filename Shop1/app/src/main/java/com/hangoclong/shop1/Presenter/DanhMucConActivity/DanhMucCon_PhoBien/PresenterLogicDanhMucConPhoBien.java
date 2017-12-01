package com.hangoclong.shop1.Presenter.DanhMucConActivity.DanhMucCon_PhoBien;

import android.content.Context;
import android.util.Log;

import com.hangoclong.shop1.Model.DanhMucConActivity.DanhMucCon_PhoBien.ModelDanhMucConPhoBien;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.View.DanhMucConActivity.DanhMucCon_PhoBien.ViewDanhMucConPhoBien;

import java.util.List;

/**
 * Created by Admin on 11/6/2017.
 */

public class PresenterLogicDanhMucConPhoBien  implements IPresenterDanhMucConPhoBien{
    ViewDanhMucConPhoBien viewDanhMucConPhoBien;
    ModelDanhMucConPhoBien modelDanhMucConPhoBien;
    public  PresenterLogicDanhMucConPhoBien(ViewDanhMucConPhoBien viewDanhMucConPhoBien){
        this. viewDanhMucConPhoBien = viewDanhMucConPhoBien;
        modelDanhMucConPhoBien = new ModelDanhMucConPhoBien();
    }




    @Override
    public void LayDanhSachTopDienThoaiPhoBien(Context context,int maloaisp) {
        List<SanPham> sanPhamList = modelDanhMucConPhoBien.LayDanhSachTopDienThoaiPhoBien("LayTopDienThoaiPhoBien","DANHSACHTOPDIENTHOAIPHOBIEN",maloaisp,0);
        if(sanPhamList.size()>=1){
            viewDanhMucConPhoBien.HienThiDanhSachTopDienThoaiPhoBien(sanPhamList);
        }
    }

    @Override
    public void LaySanPhamTheoDanhMucDienThoai(int maloaisp) {
       List<SanPham> sanPhamList = modelDanhMucConPhoBien.LayDanhSachTopDienThoaiPhoBien("LaySanPhamTheoDanhMucDienThoai","DANHSACHSANPHAMTHEODANHMUCDIENTHOAI",maloaisp,0);
        if(sanPhamList.size()>0){
            Log.d("talalong",sanPhamList.size()+"");
            viewDanhMucConPhoBien.HienThiSanPhamTheoDanhMucDienThoai(sanPhamList);
        }
    }

    public List<SanPham> LayDanhSachTopDienThoaiPhoBienLoadMore(int maloaisp, int limit) {
        List<SanPham> sanPhamList = modelDanhMucConPhoBien.LayDanhSachTopDienThoaiPhoBien("LayTopDienThoaiPhoBien","DANHSACHTOPDIENTHOAIPHOBIEN",maloaisp,limit);

        return  sanPhamList;
    }
}
