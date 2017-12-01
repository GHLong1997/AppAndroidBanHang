package com.hangoclong.shop1.View.TrangChu_Mua;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hangoclong.shop1.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 11/1/2017.
 */

public class FragmentSliderKhuyenMai extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_slider_khuyenmai,container,false);

        Bundle bundle = getArguments();
        String linkhinh = bundle.getString("linkhinh");

        ImageView imageView = view.findViewById(R.id.imSlider);
        Picasso.with(getContext()).load(linkhinh).into(imageView);
        return  view;
    }
}
