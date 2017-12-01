package com.hangoclong.shop1.View.DanhGia.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hangoclong.shop1.Adapter.CustomRecyclerDanhGia;
import com.hangoclong.shop1.Model.ObjectClass.DanhGia;
import com.hangoclong.shop1.Presenter.DanhGia.PresenterLogicDanhGia;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DanhGia.ViewDanhGia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/14/2017.
 */

public class FragmentTatCa extends Fragment implements ViewDanhGia{
    RecyclerView recyclerDanhGia;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> danhGiaList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_danhgia_tatca,container,false);
        recyclerDanhGia = view.findViewById(R.id.recycler_danhgia);
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        Bundle bundle = getArguments();
        int masp = bundle.getInt("masp");
        if(masp!=0){
            presenterLogicDanhGia.LayDanhSachDanhDanh(masp);
        }
        return view;
    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGias) {

        CustomRecyclerDanhGia customRecyclerDanhGia = new CustomRecyclerDanhGia(getActivity(),danhGias);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerDanhGia.setLayoutManager(layoutManager);
        recyclerDanhGia.setAdapter(customRecyclerDanhGia);
        customRecyclerDanhGia.notifyDataSetChanged();
    }

    @Override
    public void HienThiTongDanhGia(List<DanhGia> danhGiaList) {

    }
}
