package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.ChiTietSanPham;
import com.hangoclong.shop1.R;

import java.util.List;

/**
 * Created by Admin on 11/9/2017.
 */

public class CustomRecyclerChiTietSanPham extends RecyclerView.Adapter<CustomRecyclerChiTietSanPham.ViewHolder> {
    Context context;
    List<ChiTietSanPham> chiTietSanPhamList;
    public  CustomRecyclerChiTietSanPham(Context context,List<ChiTietSanPham> chiTietSanPhams){
        this.context = context;
        this.chiTietSanPhamList = chiTietSanPhams;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenChiTiet,txtGiaTri;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTenChiTiet = itemView.findViewById(R.id.txtTenChiTiet);
            txtGiaTri = itemView.findViewById(R.id.txtGiaTri);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycler_chitietsanpham,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamList.get(position);
        holder.txtTenChiTiet.setText(chiTietSanPham.getTenChiTiet());
        holder.txtGiaTri.setText(chiTietSanPham.getGiaTri());
    }

    @Override
    public int getItemCount() {
        return chiTietSanPhamList.size();
    }


}
