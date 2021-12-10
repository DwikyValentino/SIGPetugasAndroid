package com.dwikyv.sigpertanian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dwikyv.sigpertanian.API.APIRequestData;
import com.dwikyv.sigpertanian.API.RetroServer;
import com.dwikyv.sigpertanian.Adapter.AdapterData;
import com.dwikyv.sigpertanian.Model.DataModel;
import com.dwikyv.sigpertanian.Model.ResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataKomoditas extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    private FloatingActionButton fabTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_komoditas);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

        rvData = findViewById(R.id.rv_data);
        srlData = findViewById(R.id.srl_data);
        pbData = findViewById(R.id.pb_data);
        fabTambah = findViewById(R.id.fab_tambah);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        //sudah ada di onResume untuk retrieveData
        //retrieveData();

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataKomoditas.this, TambahKomoditasActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveData();
    }

    public void retrieveData(){
        pbData.setVisibility(View.VISIBLE);

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardRetrieveData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                //Toast.makeText(DataKomoditas.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();

                adData = new AdapterData(DataKomoditas.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(DataKomoditas.this, "Gagal Menghubungkan Server"+t.getMessage(), Toast.LENGTH_SHORT).show();

                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void ClickMenu(View view){
        //Open drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        //Redirect activity to home
        MainActivity.redirectActivity(this,MainActivity.class);
    }

    public void ClickDataLahan(View view){
        //Redirect activity to data lahan
        MainActivity.redirectActivity(this,DataLahan.class);
    }

    public void ClickDataKomoditas(View view){
        //Redirect activity to data komoditas
        recreate();
    }

    public void ClickAboutUs(View view){
        //Recreate activity
        MainActivity.redirectActivity(this,AboutUs.class);
    }

    public void ClickLogout(View view){
        //Close app
        MainActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}
