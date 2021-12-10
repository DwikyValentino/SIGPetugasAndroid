package com.dwikyv.sigpertanian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
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

public class UbahKomoditasActivity extends AppCompatActivity {
    private int xIdKomoditas;
    private String xNamaKomoditas, xJumlah, xAwal, xAkhir, xDesa, xKecamatan;

    private EditText etNamaKomoditas, etJumlah, etDesa, etKecamatan;

    EditText etDateAwal;
    EditText etDateAkhir;
    DatePickerDialog.OnDateSetListener setListener;

    private Button btnUbah;

    private String yNamaKomoditas, yJumlah, yAwal, yAkhir, yDesa, yKecamatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_komoditas);

        Intent terima = getIntent();
        xIdKomoditas = terima.getIntExtra("xIdKomoditas", -1);
        xNamaKomoditas = terima.getStringExtra("xNamaKomoditas");
        xJumlah = terima.getStringExtra("xJumlah");
        xAwal = terima.getStringExtra("xAwal");
        xAkhir = terima.getStringExtra("xAkhir");
        xDesa = terima.getStringExtra("xDesa");
        xKecamatan = terima.getStringExtra("xKecamatan");

        etNamaKomoditas = findViewById(R.id.et_namakomoditas);
        etJumlah = findViewById(R.id.et_jumlah);
        etDateAwal = findViewById(R.id.et_dateawal);
        etDateAkhir = findViewById(R.id.et_dateakhir);
        etDesa = findViewById(R.id.et_desa);
        etKecamatan = findViewById(R.id.et_kecamatan);

        btnUbah = findViewById(R.id.btn_ubah);

        etNamaKomoditas.setText(xNamaKomoditas);
        etJumlah.setText(xJumlah);
        etDateAwal.setText(xAwal);
        etDateAkhir.setText(xAkhir);
        etDesa.setText(xDesa);
        etKecamatan.setText(xKecamatan);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDateAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UbahKomoditasActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                        UbahKomoditasActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yNamaKomoditas = etNamaKomoditas.getText().toString();
                yJumlah = etJumlah.getText().toString();
                yAwal = etDateAwal.getText().toString();
                yAkhir = etDateAkhir.getText().toString();
                yDesa = etDesa.getText().toString();
                yKecamatan = etKecamatan.getText().toString();

                //updateData();

                if (yNamaKomoditas.trim().equals("")){
                    etNamaKomoditas.setError("Nama Komoditas Harus di isi");
                }
                else if (yJumlah.trim().equals("")){
                    etJumlah.setError("Jumlah Harus di isi");
                }
                else if (yAwal.trim().equals("")){
                    etDateAwal.setError("Tanggal Mulai harus di isi");
                }
                else if (yAkhir.trim().equals("")){
                    etDateAkhir.setError("Tanggal Terakhir harus di isi");
                }
                else if (yDesa.trim().equals("")){
                    etDesa.setError("Desa harus di isi");
                }
                else if (yKecamatan.trim().equals("")){
                    etKecamatan.setError("Kecamatan Harus di isi");
                }
                else {
                    updateData();
                }
            }
        });
    }

    private void updateData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ubahData = ardData.ardUpdateData(xIdKomoditas, yNamaKomoditas, yJumlah, yAwal, yAkhir, yDesa, yKecamatan);

        ubahData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahKomoditasActivity.this, "Kode : "+kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahKomoditasActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
