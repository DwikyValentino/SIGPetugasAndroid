package com.dwikyv.sigpertanian.Rest;

import com.dwikyv.sigpertanian.Model.GetTb_lahanpertanian;
import com.dwikyv.sigpertanian.Model.PostPutDelTb_lahanpertanian;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("tb_lahanpertanian")
    Call<GetTb_lahanpertanian> getTblahanpertanian();
    @Multipart
    @POST("tb_lahanpertanian")
    Call<PostPutDelTb_lahanpertanian> postTblahanpertanian(@Part MultipartBody.Part image,
                                                           @Part("namapemilik")RequestBody namapemilik,
                                                           @Part("luas")RequestBody luas,
                                                           @Part("meter")RequestBody meter,
                                                           @Part("desa")RequestBody desa,
                                                           @Part("kecamatan")RequestBody kecamatan,
                                                           @Part("latitude")RequestBody latitude,
                                                           @Part("longtitude")RequestBody longtitude,
                                                           @Part("flag")RequestBody flag); //flag digunakan untuk flagging atau mengecek apakah termasuk dalam fungsi edit atau update
    @Multipart
    @POST("tb_lahanpertanian")
    Call<PostPutDelTb_lahanpertanian> postUpdateTblahanpertanian(@Part MultipartBody.Part image,
                                                                 @Part("id_lahan")RequestBody id_lahan,
                                                                 @Part("namapemilik")RequestBody namapemilik,
                                                                 @Part("luas")RequestBody luas,
                                                                 @Part("meter")RequestBody meter,
                                                                 @Part("desa")RequestBody desa,
                                                                 @Part("kecamatan")RequestBody kecamatan,
                                                                 @Part("latitude")RequestBody latitude,
                                                                 @Part("longtitude")RequestBody longtitude,
                                                                 @Part("flag")RequestBody flag); //flag digunakan untuk flagging atau mengecek apakah termasuk dalam fungsi edit atau update
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "tb_lahanpertanian", hasBody = true)
    Call<PostPutDelTb_lahanpertanian> deleteTblahanpertanian(@Field("id_lahan") String id_lahan);




}
