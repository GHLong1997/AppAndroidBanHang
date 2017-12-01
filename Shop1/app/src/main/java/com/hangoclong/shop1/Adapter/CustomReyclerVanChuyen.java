package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Admin on 11/26/2017.
 */

public class CustomReyclerVanChuyen extends RecyclerView.Adapter<CustomReyclerVanChuyen.ViewHolderVC>{
    Context context;
    List<SanPham> sanPhamList;
    public  CustomReyclerVanChuyen(Context context,List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    public class ViewHolderVC extends RecyclerView.ViewHolder {
        ImageView imSP;
        TextView txtTen,txtGia,txtSoLuongSP,txtThanhTien;
        public ViewHolderVC(View itemView) {
            super(itemView);
            imSP = itemView.findViewById(R.id.imSP);
            txtTen = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGia);
            txtSoLuongSP = itemView.findViewById(R.id.txtSoLuongTheoTungSanPham);
            txtThanhTien = itemView.findViewById(R.id.txtThanhTien);
        }
    }
    @Override
    public ViewHolderVC onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_reycycler_vanchuyen,parent,false);
        ViewHolderVC viewHolderVC= new ViewHolderVC(view);
        return viewHolderVC;
    }

    @Override
    public void onBindViewHolder(ViewHolderVC holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        byte[] hinhsanphamgiohang = sanPham.getHinhgiohang();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhsanphamgiohang,0,hinhsanphamgiohang.length);
        holder.imSP.setImageBitmap(bitmap);
        TextView txt = new TextView(context);
        txt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(28)});
        txt.setText(sanPham.getTenSP());
        holder.txtTen.setText(txt.getText().toString()+"...");

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String underline = "<u>đ</u>";
        String gia = numberFormat.format(sanPham.getGia());
        holder.txtGia.setText(Html.fromHtml(String.format(underline) +" " + gia));
        holder.txtSoLuongSP.setText("x"+sanPham.getSoluong());
        String thanhtien = numberFormat.format(sanPham.getGia()*sanPham.getSoluong());
        holder.txtThanhTien.setText(Html.fromHtml(String.format(underline)) + " " + thanhtien);

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

}
