package com.app.nearbyhotel.model;



public class Api_Response<T> {

    private Boolean isSuccess;
    private T data;
    private String message;
    private String isBlock;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        this.isSuccess = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
