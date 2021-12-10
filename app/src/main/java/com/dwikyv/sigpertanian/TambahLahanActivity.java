package com.dwikyv.sigpertanian;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dwikyv.sigpertanian.Model.PostPutDelTb_lahanpertanian;
import com.dwikyv.sigpertanian.Rest.ApiClient;
import com.dwikyv.sigpertanian.Rest.ApiInterface;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahLahanActivity extends AppCompatActivity {
    EditText etNamapemilik, etLuas, etMeter, etDesa, etKecamatan, etLatitude, etLongtitude;
    ImageView eiFoto;
    Button btnSimpan, btnGalery;

    ApiInterface mApiInterface;

    private String mediaPath, postPath;

    private static final int REQUEST_PICK_PHOTO = Config.REQUEST_PICK_PHOTO;

    private static final int REQUEST_WRITE_PERMISSION = Config.REQUEST_WRITE_PERMISSION;

    private static final String INSERT_FLAG = Config.INSERT_FLAG;

    // Akses izin ambil gambar dari Storage
    @Override
    public void onRequestPermissionResult(int requestCode, String[] permission, int[] grantResults){
        if(requestCode ==REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            saveImageUpload();
        }
    }

    //private String namapemilik, luas, meter, desa, kecamatan, latitude, longtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_lahan);

        // Identifikasi komponen action bar
        String actionBarTitle;
        actionBarTitle="Tambah";
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Identifikasi komponen Form
        etNamapemilik = (EditText) findViewById(R.id.et_namapemilik);
        etLuas = (EditText) findViewById(R.id.et_luas);
        etMeter = (EditText) findViewById(R.id.et_meter);
        etDesa = (EditText) findViewById(R.id.et_desa);
        etKecamatan = (EditText) findViewById(R.id.et_kecamatan);
        etLatitude = (EditText) findViewById(R.id.et_latitude);
        etLongtitude = (EditText) findViewById(R.id.et_longtitude);
        eiFoto = (ImageView) findViewById(R.id.ei_foto);
        btnGalery = (Button) findViewById(R.id.btn_galeri);
        btnSimpan = (Button) findViewById(R.id.btn_simpan);

        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Fungsi Tombol pilih Galery
        btnGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });

        //Fungsi Tombol Simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });
    }

    //Akses izin  ambil gambar dari Storage
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if (requestCode == REQUEST_PICK_PHOTO){
                if (data != null){
                    // Ambil Image dari Galeri Foto
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    eiFoto.setImageURI(data.getData());
                    cursor.close();

                    postPath = mediaPath;
                }
            }
        }
    }

    //Simpan Gambar
    private void saveImageUpload(){
        final String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if (mediaPath == null){
            Toast.makeText(getApplicationContext(), "Pilih Gambar Terlebih dahulu, setelah itu bisa di simpan", Toast.LENGTH_LONG).show();
        }
        else {
            File imagefile = new File(mediaPath);
            RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
            MultipartBody.Part partImage = MultipartBody.Part.createFormData("image", imagefile.getName(), reqBody);
            Call<PostPutDelTb_lahanpertanian> postTb_lahanpertanianCall = mApiInterface.postTblahanpertanian(partImage, RequestBody.create(MediaType.parse("text/plain"), etNamapemilik.getText().toString()),
                                                                                                                          RequestBody.create(MediaType.parse("text/plain"), etLuas.getText().toString()),
                                                                                                                          RequestBody.create(MediaType.parse("text/plain"), etMeter.getText().toString()),
                                                                                                                          RequestBody.create(MediaType.parse("text/plain"), etDesa.getText().toString()),
                                                                                                                          RequestBody.create(MediaType.parse("text/plain"), etKecamatan.getText().toString()),
                                                                                                                          RequestBody.create(MediaType.parse("text/plain"), etLatitude.getText().toString()),
                                                                                                                          RequestBody.create(MediaType.parse("text/plain"), etLongtitude.getText().toString()),
                                                                                                                          RequestBody.create(MediaType.parse("text/plain"), INSERT_FLAG));
            postTb_lahanpertanianCall.enqueue(new Callback<PostPutDelTb_lahanpertanian>() {
                @Override
                public void onResponse(Call<PostPutDelTb_lahanpertanian> call, Response<PostPutDelTb_lahanpertanian> response) {
                    DataLahan.dl.refresh();
                    finish();
                }

                @Override
                public void onFailure(Call<PostPutDelTb_lahanpertanian> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " +t.getMessage());
                }
            });
        }
    }

    // Cek Versi Android untuk meminta izin
    private void requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
        else {
            saveImageUpload();
        }
    }

    //Menu Kembali ke Halaman Sebelumnya
    @Override
    public boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:;
            // API +5 Solution
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
