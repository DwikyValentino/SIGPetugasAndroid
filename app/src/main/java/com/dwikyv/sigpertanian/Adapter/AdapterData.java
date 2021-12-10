package com.dwikyv.sigpertanian.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.dwikyv.sigpertanian.API.APIRequestData;
import com.dwikyv.sigpertanian.API.RetroServer;
import com.dwikyv.sigpertanian.DataKomoditas;
import com.dwikyv.sigpertanian.Model.DataModel;
import com.dwikyv.sigpertanian.Model.ResponseModel;
import com.dwikyv.sigpertanian.R;
import com.dwikyv.sigpertanian.UbahKomoditasActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;
    private List<DataModel> listKomoditas;
    private int idKomoditas;

    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);

        holder.tvID.setText(String.valueOf(dm.getId_komoditas()));
        holder.tvNamakomoditas.setText(dm.getNamakomoditas());
        holder.tvJumlah.setText(dm.getJumlah());
        holder.tvAwal.setText(dm.getAwal());
        holder.tvAkhir.setText(dm.getAkhir());
        holder.tvDesa.setText(dm.getDesa());
        holder.tvKecamatan.setText(dm.getKecamatan());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvID, tvNamakomoditas, tvJumlah, tvAwal, tvAkhir, tvDesa, tvKecamatan;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tv_id_komoditas);
            tvNamakomoditas = itemView.findViewById(R.id.tv_namakomoditas);
            tvJumlah = itemView.findViewById(R.id.tv_jumlah);
            tvAwal = itemView.findViewById(R.id.tv_awal);
            tvAkhir = itemView.findViewById(R.id.tv_akhir);
            tvDesa = itemView.findViewById(R.id.tv_desa);
            tvKecamatan = itemView.findViewById(R.id.tv_kecamatan);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih Operasi Yang Akan Dilakukan");
                    dialogPesan.setTitle("Perhatian");
                    dialogPesan.setIcon(R.drawable.webserver);
                    dialogPesan.setCancelable(true);

                    idKomoditas = Integer.parseInt(tvID.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            deleteData();
                            dialogInterface.dismiss();
                            Handler hand = new Handler();
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((DataKomoditas) ctx).retrieveData();
                                }
                            }, 1000);
                        }
                    });

                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            getData();
                            dialogInterface.dismiss();
                        }
                    });

                    dialogPesan.show();

                    return false;
                }
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteData(idKomoditas);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> ambilData = ardData.ardGetData(idKomoditas);

            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listKomoditas = response.body().getData();

                    int varIdKomoditas = listKomoditas.get(0).getId_komoditas();
                    String varNamaKomoditas = listKomoditas.get(0).getNamakomoditas();
                    String varJumlahKomoditas = listKomoditas.get(0).getJumlah();
                    String varAwalKomoditas = listKomoditas.get(0).getAwal();
                    String varAkhirKomoditas = listKomoditas.get(0).getAkhir();
                    String varDesaKomoditas = listKomoditas.get(0).getDesa();
                    String varKecamatanKomoditas = listKomoditas.get(0).getKecamatan();

                    //Toast.makeText(ctx, "Kode : "+kode+" | Pesan : "+pesan+" | LoginData : "+varIdKomoditas+" | "+varNamaKomoditas+" | "+varJumlahKomoditas+" | "+varAwalKomoditas+" | "+varAkhirKomoditas+" | "+varDesaKomoditas+" | "+varKecamatanKomoditas, Toast.LENGTH_SHORT).show();

                    Intent kirim = new Intent(ctx, UbahKomoditasActivity.class);
                    kirim.putExtra("xIdKomoditas", varIdKomoditas);
                    kirim.putExtra("xNamaKomoditas", varNamaKomoditas);
                    kirim.putExtra("xJumlah", varJumlahKomoditas);
                    kirim.putExtra("xAwal", varAwalKomoditas);
                    kirim.putExtra("xAkhir", varAkhirKomoditas);
                    kirim.putExtra("xDesa", varDesaKomoditas);
                    kirim.putExtra("xKecamatan", varKecamatanKomoditas);
                    ctx.startActivity(kirim);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
