package com.tokio.technicaltest.app.commons.utils;

import java.util.List;

public class Response<T> {
    private ApiResponse<T> apiResponse;

    public ApiResponse<T> ofSuccess(List<T> data, String message) {
        this.apiResponse = new ApiResponse<>();
        this.apiResponse.setMessage(message);
        apiResponse.setData(data);
        return apiResponse;
    }

    public ApiResponse<T> ofError(String message) {
        this.apiResponse = new ApiResponse<>();
        this.apiResponse.setMessage(message);
        this.apiResponse.setData(List.of());
        return this.apiResponse;
    }
}