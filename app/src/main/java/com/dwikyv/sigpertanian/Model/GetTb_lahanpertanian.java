package com.dwikyv.sigpertanian.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTb_lahanpertanian {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Tb_lahanpertanian> listDataTblahanpertanian;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tb_lahanpertanian> getListDataTblahanpertanian() {
        return listDataTblahanpertanian;
    }

    public void setListDataTblahanpertanian(List<Tb_lahanpertanian> listDataTblahanpertanian) {
        this.listDataTblahanpertanian = listDataTblahanpertanian;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
