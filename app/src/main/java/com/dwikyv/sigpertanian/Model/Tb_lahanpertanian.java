package com.dwikyv.sigpertanian.Model;

import com.google.gson.annotations.SerializedName;

public class Tb_lahanpertanian {

    @SerializedName("id_lahan")
    private String id_lahan;
    @SerializedName("namapemilik")
    private String namapemilik;
    @SerializedName("luas")
    private String luas;
    @SerializedName("meter")
    private String meter;
    @SerializedName("desa")
    private String desa;
    @SerializedName("kecamatan")
    private String kecamatan;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longtitude")
    private String longtitude;
    @SerializedName("foto")
    private String foto;

    private Tb_lahanpertanian(){}

    public Tb_lahanpertanian( String id_lahan, String namapemilik, String luas, String meter, String desa, String kecamatan, String latitude, String longtitude, String foto)
    {
        this.id_lahan=id_lahan;
        this.namapemilik=namapemilik;
        this.luas=luas;
        this.meter=meter;
        this.desa=desa;
        this.kecamatan=kecamatan;
        this.latitude=latitude;
        this.longtitude=longtitude;
        this.foto=foto;
    }

    public String getId_lahan() {
        return id_lahan;
    }

    public String getNamapemilik() {
        return namapemilik;
    }

    public String getLuas() {
        return luas;
    }

    public String getMeter() {
        return meter;
    }

    public String getDesa() {
        return desa;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public String getFoto() {
        return foto;
    }
}
