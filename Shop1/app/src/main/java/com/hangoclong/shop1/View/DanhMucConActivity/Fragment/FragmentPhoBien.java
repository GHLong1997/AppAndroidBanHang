package com.hangoclong.shop1.View.DanhMucConActivity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hangoclong.shop1.Adapter.CustomRecyclerTopDienThoaiPhoBien;
import com.hangoclong.shop1.Model.ObjectClass.ILoadMore;
import com.hangoclong.shop1.Model.ObjectClass.LoadMoreScroll;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Presenter.DanhMucConActivity.DanhMucCon_PhoBien.PresenterLogicDanhMucConPhoBien;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DanhMucConActivity.DanhMucConActivity;
import com.hangoclong.shop1.View.DanhMucConActivity.DanhMucCon_PhoBien.ViewDanhMucConPhoBien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/31/2017.
 */

public class FragmentPhoBien extends Fragment implements ViewDanhMucConPhoBien,ILoadMore{
    RecyclerView recyclerTopDienThoai;
    PresenterLogicDanhMucConPhoBien presenterLogicDanhMucConPhoBien;
    List<SanPham> sanPhamList1 = new ArrayList<>();
    CustomRecyclerTopDienThoaiPhoBien customRecyclerTopDienThoaiPhoBien;
    RecyclerView.LayoutManager layoutManager;
    int maloai;
    public  static   int DEM =0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_phobien,container,false);
        recyclerTopDienThoai =  view.findViewById(R.id.recyclerTopDienThoaiPhoBien);
        presenterLogicDanhMucConPhoBien = new PresenterLogicDanhMucConPhoBien(this);

        if(DanhMucConActivity.MALOAISP2!=0){
            Log.d("toilaai",DanhMucConActivity.MALOAISP2+"");
            presenterLogicDanhMucConPhoBien.LaySanPhamTheoDanhMucDienThoai(DanhMucConActivity.MALOAISP2);
        }else
        if (DanhMucConActivity.MALOAISP!=0){
            presenterLogicDanhMucConPhoBien.LayDanhSachTopDienThoaiPhoBien(getActivity(),DanhMucConActivity.MALOAISP);
        }

//        }
////Note thằng if sau no thực hiện sau nên nó chen sau nên giao diên layout là của nó, nhưng khi kich vào 1 button nó quay trơ
//        lại trang này và khi đó nó sẽ lọc những sản phẩm theo button và đồng thời thằng if sau nó hết giatri(null) do đó no se
//         lây thằng if đầu để làm layout

        return  view;
    }

    @Override
    public void HienThiDanhSachTopDienThoaiPhoBien(List<SanPham> sanPhamList) {
        //vi Adapter thằng này cũng tương tự thằng AdapterTopDienThoaiDieNTu cung chung phần hiển thị, chi khác nhau cai grid thôi nen dung cung dc
            sanPhamList1 = sanPhamList; //khi ud chay lên thì splist thăng này = 20;(loadmor1)
           customRecyclerTopDienThoaiPhoBien = new CustomRecyclerTopDienThoaiPhoBien(getActivity(),sanPhamList1);
           layoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
            recyclerTopDienThoai.setLayoutManager(layoutManager);
            recyclerTopDienThoai.setAdapter(customRecyclerTopDienThoaiPhoBien);
              recyclerTopDienThoai.addOnScrollListener(new LoadMoreScroll(layoutManager,this));//set su kien scroll loadmor cho thằng này, vì nay này scroll dc
            Log.d("hhhhhh","sadad");

            customRecyclerTopDienThoaiPhoBien.notifyDataSetChanged();


    }

    @Override
    public void HienThiSanPhamTheoDanhMucDienThoai(List<SanPham> sanPhamList) {

       CustomRecyclerTopDienThoaiPhoBien customRecyclerTopDienThoaiPhoBien1 = new CustomRecyclerTopDienThoaiPhoBien(getActivity(),sanPhamList);
        layoutManager = new GridLayoutManager(getActivity(),2, LinearLayoutManager.VERTICAL,false);
        recyclerTopDienThoai.setLayoutManager(layoutManager);
        recyclerTopDienThoai.setAdapter(customRecyclerTopDienThoaiPhoBien1);;
        customRecyclerTopDienThoaiPhoBien1.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongitem) {
        //tổng item nhận vào này chính là cái limit chúng ta truyên lên server , mà từ đó chúng tá sẽ lấy được 20 sp tiếp theo
       List<SanPham> listSanPhamLoadMore = presenterLogicDanhMucConPhoBien.LayDanhSachTopDienThoaiPhoBienLoadMore(DanhMucConActivity.MALOAISP,tongitem);
        sanPhamList1.addAll(listSanPhamLoadMore);
        customRecyclerTopDienThoaiPhoBien.notifyDataSetChanged();
    }
}
