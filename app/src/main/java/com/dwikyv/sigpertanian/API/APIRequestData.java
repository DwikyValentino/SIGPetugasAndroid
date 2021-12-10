package com.dwikyv.sigpertanian.API;

import com.dwikyv.sigpertanian.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData  {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
            @Field("namakomoditas") String namakomoditas,
            @Field("jumlah") String jumlah,
            @Field("awal") String awal,
            @Field("akhir") String akhir,
            @Field("desa") String desa,
            @Field("kecamatan") String kecamatan
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id_komoditas") int id_komoditas
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("id_komoditas") int id_komoditas
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("id_komoditas") int id_komoditas,
            @Field("namakomoditas") String namakomoditas,
            @Field("jumlah") String jumlah,
            @Field("awal") String awal,
            @Field("akhir") String akhir,
            @Field("desa") String desa,
            @Field("kecamatan") String kecamatan
    );
}
