package com.lll.weidustore.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lll.weidustore.R;
import com.lll.weidustore.SpacingItemDecoration;
import com.lll.weidustore.adapter.CircleAdapter;
import com.lll.weidustore.bean.CircleBean;
import com.lll.weidustore.bean.Result;
import com.lll.weidustore.core.DataCall;
import com.lll.weidustore.core.WDFragment;
import com.lll.weidustore.core.exception.ApiException;
import com.lll.weidustore.presenter.CirclePresenter;

import java.util.List;

import butterknife.BindView;

public class Frag_02 extends WDFragment implements XRecyclerView.LoadingListener{

    @BindView(R.id.circle_list)
    XRecyclerView circleList;
    private CirclePresenter circlePresenter;
    private CircleAdapter circleAdapter;

    @Override
    public String getPageName() {
        return "圈子Fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_02;
    }

    @Override
    protected void initView() {
        circleAdapter = new CircleAdapter(getContext());

        circleList.setLayoutManager(new GridLayoutManager(getContext(),1));

        int space = getResources().getDimensionPixelSize(R.dimen.dip_20);

        circleList.addItemDecoration(new SpacingItemDecoration(space));
        circleList.setAdapter(circleAdapter);
        circlePresenter = new CirclePresenter(new CircleCall());
        circleList.setLoadingListener(this);
        circleList.refresh();
    }

    @Override
    public void onRefresh() {
        if (circlePresenter.isRunning()){
            circleList.refreshComplete();
            return;
        }
        circlePresenter.reqeust(true,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    @Override
    public void onLoadMore() {
        if (circlePresenter.isRunning()){
            circleList.loadMoreComplete();
            return;
        }
        circlePresenter.reqeust(false,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    private class CircleCall implements DataCall<Result<List<CircleBean>>> {
        @Override
        public void success(Result<List<CircleBean>> data) {
            circleList.refreshComplete();
            circleList.loadMoreComplete();
            if (data.getStatus().equals("0000")){
                //添加列表并刷新

                int size = data.getResult().size();
                Toast.makeText(getContext(), ""+size, Toast.LENGTH_SHORT).show();
                circleAdapter.addAll(data.getResult());
                circleAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            circleList.refreshComplete();
            circleList.loadMoreComplete();
            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
        }
    }
}
