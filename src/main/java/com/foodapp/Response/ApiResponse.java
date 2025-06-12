package com.foodapp.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.ZonedDateTime;

@JsonPropertyOrder({"success", "status", "timestamp", "data"})
public class ApiResponse<T> {
    private ZonedDateTime timestamp;
    private int status;
    private boolean success;
    private T data;

    public ApiResponse(int status, boolean success, T data) {
        this.timestamp = ZonedDateTime.now();
        this.status = status;
        this.success = success;
        this.data = data;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}