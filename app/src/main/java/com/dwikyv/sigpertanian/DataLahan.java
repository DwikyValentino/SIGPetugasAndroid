package com.dwikyv.sigpertanian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dwikyv.sigpertanian.Adapter.TblahanpertanianAdapter;
import com.dwikyv.sigpertanian.Model.GetTb_lahanpertanian;
import com.dwikyv.sigpertanian.Model.Tb_lahanpertanian;
import com.dwikyv.sigpertanian.Rest.ApiClient;
import com.dwikyv.sigpertanian.Rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLahan extends AppCompatActivity {
    //Initialize variable
    ApiInterface mApiInterface;
    DrawerLayout drawerLayout;

    public static DataLahan dl;

    private RecyclerView rvData2;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    //private List<DataModel2> listData = new ArrayList<>();
    //private SwipeRefreshLayout srlData;
    //private ProgressBar pbData;
    private FloatingActionButton fabTambah2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_lahan);

//        //initialize fragment
//        Fragment fragment = new MapFragment();
//
//        //Open fragment
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_layout,fragment)
//                .commit();

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        rvData2 = (RecyclerView) findViewById(R.id.rv_data2);
//        pbData = findViewById(R.id.pb_data);
//        srlData = findViewById(R.id.srl_data);
//
//        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                srlData.setRefreshing(true);
//                retrieveData();
//                srlData.setRefreshing(false);
//            }
//        });

        fabTambah2 = findViewById(R.id.fab_tambah2);

        fabTambah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataLahan.this, TambahLahanActivity.class));
            }
        });

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData2.setLayoutManager(lmData);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        dl=this;

        //sudah ada di onResume untuk retrieveData
        //retrieveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //retrieveData();
    }

    public void refresh() {
        Call<GetTb_lahanpertanian> Tb_lahanpertanianCall = mApiInterface.getTblahanpertanian();
        Tb_lahanpertanianCall.enqueue(new Callback<GetTb_lahanpertanian>() {
            @Override
            public void onResponse(Call<GetTb_lahanpertanian> call, Response<GetTb_lahanpertanian> response) {
                List<Tb_lahanpertanian> tbLahanpertanianList = response.body().getListDataTblahanpertanian();
                Log.d("Retrofit Get", "Jumlah data Lahan Pertanian:" + String.valueOf(tbLahanpertanianList.size()));
                adData = new TblahanpertanianAdapter(tbLahanpertanianList);
                rvData2.setAdapter(adData);
            }

            @Override
            public void onFailure(Call<GetTb_lahanpertanian> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

//    public void retrieveData(){
//        pbData.setVisibility(View.VISIBLE);
//
//        APIRequestData2 ardData = RetroServer2.konekRetrofit().create(APIRequestData2.class);
//        Call<ResponseModel2> tampilData = ardData.ardRetrieveData();
//
//        tampilData.enqueue(new Callback<ResponseModel2>() {
//            @Override
//            public void onResponse(Call<ResponseModel2> call, Response<ResponseModel2> response) {
//                int kode = response.body().getKode();
//                String pesan = response.body().getPesan();
//
//                //Toast.makeText(DataLahan.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
//
//                listData = response.body().getData();
//
//                adData = new AdapterData2(DataLahan.this, listData);
//                rvData2.setAdapter(adData);
//                adData.notifyDataSetChanged();
//
//                pbData.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel2> call, Throwable t) {
//                Toast.makeText(DataLahan.this, "Gagal Menghubungkan Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                pbData.setVisibility(View.INVISIBLE);
//            }
//        });
//    }

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
        //Recreate activity
        recreate();
    }

    public void ClickDataKomoditas(View view){
        //Redirect activity to data komoditas hasil panen
        MainActivity.redirectActivity(this,DataKomoditas.class);
    }

    public void ClickAboutUs(View view){
        //Redirect activity to about us
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

    @SuppressWarnings("MissingPermission")
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
