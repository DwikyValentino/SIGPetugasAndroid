package com.dwikyv.sigpertanian.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelTb_lahanpertanian {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    Tb_lahanpertanian mTblahanpertanian;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tb_lahanpertanian getTblahanpertanian() {
        return mTblahanpertanian;
    }

    public void setTblahanpertanian(Tb_lahanpertanian Tblahanpertanian) {
        mTblahanpertanian = Tblahanpertanian;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
