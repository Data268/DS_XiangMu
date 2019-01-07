package com.lll.weidustore.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidustore.R;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyHolder> {

    private List<String> mList = new ArrayList<>();

    public void addAll(List<String> list){
        mList.addAll(list);
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.circle_image_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.image.setImageURI(Uri.parse(mList.get(i)));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void clear() {
        mList.clear();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView image;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circle_image01);
        }
    }
}
