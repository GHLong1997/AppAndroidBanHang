package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.DiaChi;
import com.hangoclong.shop1.Model.ThanhToan.DiaChi.ModelDiaChi;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.ThemDiaChi.ThemDiaChiActivity;

import java.util.List;

/**
 * Created by Admin on 11/25/2017.
 */

public class CustomRecyclerCapNhatDiaChi extends RecyclerView.Adapter<CustomRecyclerCapNhatDiaChi.ViewHolderDC> {
    Context context;
    List<DiaChi> diaChis;
    ModelDiaChi modelDiaChi;

    public CustomRecyclerCapNhatDiaChi(Context context, List<DiaChi> diaChis){
        this.context = context;
        this.diaChis = diaChis;
        modelDiaChi = new ModelDiaChi();
        modelDiaChi.MoKetNoi(context);
    }
    public class ViewHolderDC extends RecyclerView.ViewHolder {
        TextView txtTen,txtSDT,txtTinh,txtHuyen,txtPhuong,txtDiaChiCuThe,txtMacDinh;
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
        final DiaChi diaChi = diaChis.get(position);
        holder.txtTen.setText(diaChi.getTenNguoiGui());
        holder.txtSDT.setText(diaChi.getSoDienThoai());
        holder.txtTinh.setText(diaChi.getTinh());
        holder.txtPhuong.setText(diaChi.getPhuong());
        holder.txtHuyen.setText(diaChi.getQuan());
        holder.txtDiaChiCuThe.setText(diaChi.getDiaChiCuThe());

        holder.lnDiaChi.setTag(diaChi.getMaDiaChi());
        holder.txtMacDinh.setTextColor(context.getResources().getColor(R.color.bgToolbar));
        holder.lnDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThemDiaChiActivity.class);
                intent.putExtra("xoa",1);
                intent.putExtra("object",diaChi);
                context.startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
            }
        });
        if(diaChi.getMacDinh()==1){
            holder.txtMacDinh.setVisibility(View.VISIBLE);
        }else {
            holder.txtMacDinh.setVisibility(View.GONE);


        }

    }

    @Override
    public int getItemCount() {
        return diaChis.size();
    }


}
