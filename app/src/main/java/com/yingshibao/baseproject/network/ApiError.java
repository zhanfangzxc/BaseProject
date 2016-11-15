package com.yingshibao.baseproject.network;

import com.google.gson.annotations.SerializedName;

/**
 * @author malinkang
 */

public class ApiError extends RuntimeException {
    @SerializedName("status")
    private Integer code;
    @SerializedName("msg")
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiError(String resultMessage) {
        super(resultMessage);
    }

    public boolean isError() {
        return code > 1;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
