package com.lll.weidustore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lll.weidustore.adapter.SearchAdapter;
import com.lll.weidustore.bean.Result;
import com.lll.weidustore.bean.SearchBean;
import com.lll.weidustore.core.DataCall;
import com.lll.weidustore.core.WDActivity;
import com.lll.weidustore.core.exception.ApiException;
import com.lll.weidustore.presenter.SearchPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends WDActivity implements XRecyclerView.LoadingListener, View.OnClickListener{

    @BindView(R.id.search_menu)
    ImageView searchMenu;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_shop)
    TextView searchshop;
    @BindView(R.id.search_none)
    LinearLayout linearLayout;
    @BindView(R.id.search_xrecy)
    XRecyclerView searchXrecy;

    private SearchAdapter searchAdapter;
    private int page;
    private String trim;
    private SearchPresenter searchPresenter;

    @Override
    protected void initView() {
//        Intent Intentntent = getIntent();
//        String search = intent.getStringExtra("search");
        //设置内容
//        searchEdit.setText(search);
        //xrecycler
        searchMenu.setOnClickListener(this);
        searchshop.setOnClickListener(this);
        searchXrecy.setLayoutManager(new GridLayoutManager(this, 2));
        searchPresenter = new SearchPresenter(new SearchCall());
        searchAdapter = new SearchAdapter();
        searchXrecy.setAdapter(searchAdapter);
        searchXrecy.setLoadingMoreEnabled(true);//加载更多
        searchXrecy.setLoadingListener(this);//点击加载
    }

    @Override
    protected int getLayoutId() {
            return R.layout.activity_search;
    }

    @Override
    protected void destoryData() {

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.search_shop:
               searchXrecy.refresh();
               break;
           case R.id.search_menu:
               break;
       }
    }

    @Override
    public void onRefresh() {
        if (searchPresenter.isRunning()){
            searchXrecy.refreshComplete();
            return;
        }
        page=1;
        trim = searchEdit.getText().toString().trim();
        Log.e("-----",trim);
        searchPresenter.reqeust(true,trim,5);
    }

    @Override
    public void onLoadMore() {

        if (searchPresenter.isRunning()){
            searchXrecy.loadMoreComplete();//隐藏加载圈
            return;
        }
        page++;
        trim = searchEdit.getText().toString().trim();
        Log.e("-----",trim);
        searchPresenter.reqeust(false,trim,5);
    }

    private class SearchCall implements DataCall<Result<List<SearchBean>>> {


        @Override
        public void success(Result<List<SearchBean>> data) {
            searchXrecy.loadMoreComplete();
            searchXrecy.refreshComplete();
            searchAdapter.deleteAll();
            if (data.getStatus().equals("0000")) {
                if (data.getResult().size() == 0) {//查询的list长度为0 表示未查询到
                    searchXrecy.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                    searchXrecy.setVisibility(View.VISIBLE);
                    searchAdapter.addList(data.getResult());
                    searchAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void fail(ApiException e) {
            searchXrecy.loadMoreComplete();
            searchXrecy.refreshComplete();
            Toast.makeText(getBaseContext(), "没有合适的商品!!!"+e.getCode(), Toast.LENGTH_LONG).show();
        }
    }
}
