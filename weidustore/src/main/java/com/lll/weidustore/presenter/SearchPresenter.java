package com.lll.weidustore.presenter;

import com.lll.weidustore.bean.CircleBean;
import com.lll.weidustore.bean.Result;
import com.lll.weidustore.core.BasePresenter;
import com.lll.weidustore.core.DataCall;
import com.lll.weidustore.core.ILogin;
import com.lll.weidustore.http.NetWork;

import java.util.List;

import io.reactivex.Observable;

public class SearchPresenter extends BasePresenter {
    private int page=1;
    private boolean isRefresh=true;
    public SearchPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ILogin iLogin = NetWork.netWork().create(ILogin.class);
        isRefresh = (boolean)args[0];
        if (isRefresh){
            page = 1;
        }else{
            page++;
        }
        return iLogin.search((String) args[1],page,(int)args[2]);
    }

        public boolean isRefresh() {
        return isRefresh;
    }
}
