package com.etiqa.onlinestore.dto;

public class ApiResponse<T> {
    private boolean succeeded;
    private String code;
    private T data;

    public ApiResponse(boolean succeeded, String code, T data) {
        this.succeeded = succeeded;
        this.code = code;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "200", data);
    }

    public static <T> ApiResponse<T> failure(String code, T data) {
        return new ApiResponse<>(false, code, data);
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
