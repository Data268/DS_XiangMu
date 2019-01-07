package com.lll.weidustore.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lll.weidustore.R;
import com.lll.weidustore.app.MyApp;
import com.lll.weidustore.bean.GoodsList;

import java.util.ArrayList;
import java.util.List;

public class FinAdapter extends RecyclerView.Adapter<FinAdapter.MyView> {
    private List<GoodsList.MlssBean.CommodityListBeanXX> list = new ArrayList<>();
    public void rxaddList(List<GoodsList.MlssBean.CommodityListBeanXX> user){
        if(user!=null){
            list.addAll(user);
        }
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fin_layout, viewGroup, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView myView, int i) {
        GoodsList.MlssBean.CommodityListBeanXX commodityListBeanXX = list.get(i);
        myView.fin_title.setText(commodityListBeanXX.getCommodityName());
        myView.fin_price.setText("￥"+commodityListBeanXX.getPrice());
        Glide.with(MyApp.getContext()).load(commodityListBeanXX.getMasterPic()).into(myView.fin_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        private final ImageView fin_image;
        private final TextView fin_title;
        private final TextView fin_price;

        public MyView(@NonNull View itemView) {
            super(itemView);
            fin_image = itemView.findViewById(R.id.fin_image);
            fin_title = itemView.findViewById(R.id.fin_title);
            fin_price = itemView.findViewById(R.id.fin_price);
        }
    }
}
