package com.dwikyv.sigpertanian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.dwikyv.sigpertanian.API.APIRequestData;
import com.dwikyv.sigpertanian.API.RetroServer;
import com.dwikyv.sigpertanian.Model.ResponseModel;
import com.dwikyv.sigpertanian.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahKomoditasActivity extends AppCompatActivity {
    private EditText etNamakomoditas, etJumlah, etDesa, etKecamatan;

    EditText etDateAwal;
    EditText etDateAkhir;
    DatePickerDialog.OnDateSetListener setListener;

    private Button btnSimpan;

    private String namakomoditas, jumlah, awal, akhir, desa, kecamatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_komoditas);

        etNamakomoditas = findViewById(R.id.et_namakomoditas);
        etJumlah = findViewById(R.id.et_jumlah);
        etDateAwal = findViewById(R.id.et_dateawal);
        etDateAkhir = findViewById(R.id.et_dateakhir);
        etDesa = findViewById(R.id.et_desa);
        etKecamatan = findViewById(R.id.et_kecamatan);
        btnSimpan = findViewById(R.id.btn_simpan);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDateAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TambahKomoditasActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = year + "/" + month + "/" + day;
                        etDateAwal.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        etDateAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TambahKomoditasActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = year + "/" + month + "/" + day;
                        etDateAkhir.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namakomoditas = etNamakomoditas.getText().toString();
                jumlah = etJumlah.getText().toString();
                awal = etDateAwal.getText().toString();
                akhir = etDateAkhir.getText().toString();
                desa = etDesa.getText().toString();
                kecamatan = etKecamatan.getText().toString();

                if(namakomoditas.trim().equals("")){
                    etNamakomoditas.setError("Nama Komoditas Harus di isi");
                }
                else if (jumlah.trim().equals("")){
                    etJumlah.setError("Jumlah Harus di isi");
                }
                else if (awal.trim().equals("")){
                    etDateAwal.setError("Tanggal Mulai Harus di pilih");
                }
                else if (akhir.trim().equals("")){
                    etDateAkhir.setError("Tanggal Terakhir harus di pilih");
                }
                else if (desa.trim().equals("")){
                    etDesa.setError("Desa Harus di isi");
                }
                else if (kecamatan.trim().equals("")){
                    etKecamatan.setError("Kecamatan Harus di isi");
                }
                else{
                    createData();
                }
            }
        });
    }

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(namakomoditas, jumlah, awal, akhir, desa, kecamatan);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahKomoditasActivity.this, "Kode : "+kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahKomoditasActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}