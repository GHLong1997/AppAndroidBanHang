package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.DiaChi;
import com.hangoclong.shop1.Model.ThanhToan.DiaChi.ModelDiaChi;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import java.util.List;

/**
 * Created by Admin on 11/25/2017.
 */

public class CustomRecyclerDiaChi extends RecyclerView.Adapter<CustomRecyclerDiaChi.ViewHolderDC> {
    Context context;
    List<DiaChi> diaChis;
    ModelDiaChi modelDiaChi;
    public  CustomRecyclerDiaChi(Context context,List<DiaChi> diaChis){
        this.context = context;
        this.diaChis = diaChis;
        modelDiaChi = new ModelDiaChi();
        modelDiaChi.MoKetNoi(context);
    }
    public class ViewHolderDC extends RecyclerView.ViewHolder {
        TextView txtTen,txtSDT,txtTinh,txtHuyen,txtPhuong,txtDiaChiCuThe,txtMacDinh;
        ImageView imMacDinh;
        LinearLayout lnDiaChi;

        public ViewHolderDC(View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            txtTinh = itemView.findViewById(R.id.txtThanhPho);
            txtHuyen = itemView.findViewById(R.id.txtHuyen);
            txtPhuong = itemView.findViewById(R.id.txtXa);
            txtDiaChiCuThe = itemView.findViewById(R.id.txtDiaChiCuThe);
            txtMacDinh = itemView.findViewById(R.id.txtMatDinh);
            imMacDinh = itemView.findViewById(R.id.imMatDinh);
            lnDiaChi = itemView.findViewById(R.id.lnDiaChi);
        }
    }
    @Override
    public ViewHolderDC onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycler_diachi,parent,false);
        ViewHolderDC viewHolderDC = new ViewHolderDC(view);
        return viewHolderDC;
    }

    @Override
    public void onBindViewHolder(ViewHolderDC holder, final int position) {
      if(diaChis.size()==1){
          holder.txtMacDinh.setVisibility(View.VISIBLE);
          holder.imMacDinh.setVisibility(View.VISIBLE);
          modelDiaChi.CapNhatDiaChiMatDinhTheoIDNguoiDangNhap(TrangChuActivity.HINHANH,diaChis.get(position).getMaDiaChi(),1);

      }
        final DiaChi diaChi = diaChis.get(position);
        holder.txtTen.setText(diaChi.getTenNguoiGui());
        holder.txtSDT.setText(diaChi.getSoDienThoai());
        holder.txtTinh.setText(diaChi.getTinh());
        holder.txtPhuong.setText(diaChi.getPhuong());
        holder.txtHuyen.setText(diaChi.getQuan());
        holder.txtDiaChiCuThe.setText(diaChi.getDiaChiCuThe());

        holder.lnDiaChi.setTag(diaChi.getMaDiaChi());
        holder.lnDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelDiaChi.CapNhatDiaChiMatDinhTheoIDNguoiDangNhap(TrangChuActivity.HINHANH,(int)view.getTag(),1);
                for(int i = 0 ; i < diaChis.size();i++){
                    if(i!=position){
                        diaChis.get(i).setMacDinh(0);
                        Log.d("macdinh",diaChis.get(i).getMacDinh()+"");
                        notifyDataSetChanged();
                    }
                }
                diaChis.get(position).setMacDinh(1);
                notifyDataSetChanged();
            }
        });
        if(diaChi.getMacDinh()==1){
            holder.txtMacDinh.setVisibility(View.VISIBLE);
            holder.imMacDinh.setVisibility(View.VISIBLE);
        }else {
            holder.txtMacDinh.setVisibility(View.GONE);
            holder.imMacDinh.setVisibility(View.GONE);
        }
        Log.d("diachiii",diaChi.getMacDinh()+"" + diaChi.getMaDiaChi());
    }

    @Override
    public int getItemCount() {
        return diaChis.size();
    }


}
