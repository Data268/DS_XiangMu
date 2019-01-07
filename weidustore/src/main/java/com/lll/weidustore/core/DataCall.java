package com.lll.weidustore.core;

import com.lll.weidustore.core.exception.ApiException;

public interface DataCall<T> {
    void success(T data);
    void fail(ApiException e);
}
