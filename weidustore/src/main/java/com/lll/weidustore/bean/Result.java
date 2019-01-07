package com.lll.weidustore.bean;

public class Result<T> {

    /**
     * result : {"headPic":"http://172.17.8.100/images/small/default/user.jpg","nickName":"DY_3994m","phone":"18811212759","sessionId":"1546088674768827","sex":1,"userId":827}
     * message : 登录成功
     * status : 0000
     */

    private T result;
    private String message;
    private String status;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
