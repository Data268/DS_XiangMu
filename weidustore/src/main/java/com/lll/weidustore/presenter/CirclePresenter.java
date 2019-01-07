package com.lll.weidustore.presenter;

import com.lll.weidustore.bean.CircleBean;
import com.lll.weidustore.bean.Result;
import com.lll.weidustore.core.BasePresenter;
import com.lll.weidustore.core.DataCall;
import com.lll.weidustore.core.ILogin;
import com.lll.weidustore.http.NetWork;

import java.util.List;

import io.reactivex.Observable;

public class CirclePresenter extends BasePresenter {
    private int page=1;
    public CirclePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable<Result<List<CircleBean>>> observable(Object... args) {
        ILogin iLogin = NetWork.netWork().create(ILogin.class);
        boolean refresh = (boolean)args[0];
        if (refresh){
            page = 1;
        }else{
            page++;
        }
        return iLogin.circle((long)args[1],(String)args[2],page,10);
    }
}
