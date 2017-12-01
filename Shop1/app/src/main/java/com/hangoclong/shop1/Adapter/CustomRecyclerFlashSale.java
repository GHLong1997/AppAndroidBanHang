package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Admin on 11/1/2017.
 */

public class CustomRecyclerFlashSale extends RecyclerView.Adapter<CustomRecyclerFlashSale.ViewHolderFS> {
    Context context;
    List<SanPham> sanPhamList;
    public  CustomRecyclerFlashSale(Context context, List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    public class ViewHolderFS extends RecyclerView.ViewHolder {
        ImageView imKhuyenMai;
        TextView txtPhanTramKhuyenMai,txtGia;
        FrameLayout frLayout;
        public ViewHolderFS(View itemView) {
            super(itemView);
            imKhuyenMai = itemView.findViewById(R.id.imKhuyenMai);
            txtPhanTramKhuyenMai = itemView.findViewById(R.id.txtPhanTramKM);
            txtGia = itemView.findViewById(R.id.txtGia);
            frLayout = itemView.findViewById(R.id.frLayout);
        }
    }

    @Override
    public ViewHolderFS onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycler_flashsale,parent,false);
        ViewHolderFS viewHolderFS = new ViewHolderFS(view);
        return viewHolderFS;
    }

    @Override
    public void onBindViewHolder(ViewHolderFS holder, int position) {


             SanPham sanPham = sanPhamList.get(position);
             Log.d("ss",sanPham.getAnhLon()+"");
             Log.d("tien",sanPham.getChiTietKhuyenMai().getPhanTramKM()+"");

                 holder.txtPhanTramKhuyenMai.setText(sanPham.getChiTietKhuyenMai().getPhanTramKM()+ "%");
                 Picasso.with(context).load(sanPham.getAnhLon()).into(holder.imKhuyenMai);
                 int tong = (sanPham.getGia()*(100-sanPham.getChiTietKhuyenMai().getPhanTramKM()))/100;

                 NumberFormat numberFormat = new DecimalFormat("###,###");
                 String gia = numberFormat.format(tong);
                 holder.txtGia.setText(gia + " VNĐ");
                 holder.frLayout.setTag(sanPham.getMaSP());
                holder.frLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                        Log.d("maspsp",(int)view.getTag()+"");
                        intent.putExtra("masp",(int)view.getTag());
                        context.startActivity(intent);
                        ((AppCompatActivity)context).overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);

                    }
                });




    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
