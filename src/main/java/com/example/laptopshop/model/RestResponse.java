package com.example.laptopshop.model;


// format data 
public class RestResponse<T> {
    private int statusCode; // mã HTTP: 200,201,400...
    private String status; // trạng thái success hoặc error
    private Object message;
    private String error;
    private T data;

    public RestResponse() {
    }



    public int getStatusCode() {
        return statusCode;
    }

    public RestResponse(int statusCode, String status, Object message, String error, T data) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.error = error;
        this.data = data;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
