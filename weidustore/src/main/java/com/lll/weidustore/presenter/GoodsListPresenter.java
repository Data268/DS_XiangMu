package com.lll.weidustore.presenter;

import com.lll.weidustore.bean.CircleBean;
import com.lll.weidustore.bean.Result;
import com.lll.weidustore.core.BasePresenter;
import com.lll.weidustore.core.DataCall;
import com.lll.weidustore.core.ILogin;
import com.lll.weidustore.http.NetWork;

import java.util.List;

import io.reactivex.Observable;

public class GoodsListPresenter extends BasePresenter {
    public GoodsListPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ILogin iLogin = NetWork.netWork().create(ILogin.class);
        return iLogin.getlist();
    }
}
