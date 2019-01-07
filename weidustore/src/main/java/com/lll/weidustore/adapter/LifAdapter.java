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

public class LifAdapter extends RecyclerView.Adapter<LifAdapter.MyView> {
    private List<GoodsList.PzshBean.CommodityListBeanX> list = new ArrayList<>();

    public void rxaddList(List<GoodsList.PzshBean.CommodityListBeanX> user) {
        if (user != null) {
            list.addAll(user);
        }
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lif_layout, viewGroup, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView myView, int i) {
        GoodsList.PzshBean.CommodityListBeanX commodityListBeanX = list.get(i);
        myView.lif_title.setText(commodityListBeanX.getCommodityName());
        myView.lif_price.setText(""+commodityListBeanX.getPrice());
        Glide.with(MyApp.getContext()).load(commodityListBeanX.getMasterPic()).into(myView.lif_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        private final ImageView lif_image;
        private final TextView lif_title;
        private final TextView lif_price;

        public MyView(@NonNull View itemView) {
            super(itemView);
            lif_image = itemView.findViewById(R.id.lif_image);
            lif_title = itemView.findViewById(R.id.lif_title);
            lif_price = itemView.findViewById(R.id.lif_price);
        }
    }
}
