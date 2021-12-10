package com.dwikyv.sigpertanian.Model;

public class DataModel {
    private int id_komoditas;
    private String namakomoditas, jumlah, awal, akhir, desa, kecamatan;

    public int getId_komoditas() {
        return id_komoditas;
    }

    public void setId_komoditas(int id_komoditas) {
        this.id_komoditas = id_komoditas;
    }

    public String getNamakomoditas() {
        return namakomoditas;
    }

    public void setNamakomoditas(String namakomoditas) {
        this.namakomoditas = namakomoditas;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getAwal() {
        return awal;
    }

    public void setAwal(String awal) {
        this.awal = awal;
    }

    public String getAkhir() {
        return akhir;
    }

    public void setAkhir(String akhir) {
        this.akhir = akhir;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }
}
