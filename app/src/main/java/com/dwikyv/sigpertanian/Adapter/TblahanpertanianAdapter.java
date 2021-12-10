package com.dwikyv.sigpertanian.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dwikyv.sigpertanian.Config;
import com.dwikyv.sigpertanian.Model.Tb_lahanpertanian;
import com.dwikyv.sigpertanian.R;
import com.dwikyv.sigpertanian.UbahLahanActivity;

import org.w3c.dom.Text;

import java.util.List;

public class TblahanpertanianAdapter extends RecyclerView.Adapter<TblahanpertanianAdapter.MyViewHolder> {

    List<Tb_lahanpertanian> mTblahanpertanianList;

    public TblahanpertanianAdapter(List<Tb_lahanpertanian> TblahanpertanianList) {
        mTblahanpertanianList = TblahanpertanianList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item2, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(mView);
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextViewNamaPemilik.setText(mTblahanpertanianList.get(position).getNamapemilik());
        holder.mTextViewLuas.setText(mTblahanpertanianList.get(position).getLuas());
        holder.mTextViewMeter.setText(mTblahanpertanianList.get(position).getMeter());
        holder.mTextViewDesa.setText(mTblahanpertanianList.get(position).getDesa());
        holder.mTextViewKecamatan.setText(mTblahanpertanianList.get(position).getKecamatan());
        holder.mTextViewLatitude.setText(mTblahanpertanianList.get(position).getLatitude());
        holder.mTextViewLongtitude.setText(mTblahanpertanianList.get(position).getLongtitude());
        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGE_URL + mTblahanpertanianList.get(position).getFoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.mImageViewFoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), UbahLahanActivity.class);
                mIntent.putExtra("Id_lahan", mTblahanpertanianList.get(position).getId_lahan());
                mIntent.putExtra("Namapemilik", mTblahanpertanianList.get(position).getNamapemilik());
                mIntent.putExtra("Luas", mTblahanpertanianList.get(position).getLuas());
                mIntent.putExtra("Meter", mTblahanpertanianList.get(position).getMeter());
                mIntent.putExtra("Desa", mTblahanpertanianList.get(position).getDesa());
                mIntent.putExtra("Kecamatan", mTblahanpertanianList.get(position).getKecamatan());
                mIntent.putExtra("Latitude", mTblahanpertanianList.get(position).getLatitude());
                mIntent.putExtra("Longtitude", mTblahanpertanianList.get(position).getLongtitude());
                mIntent.putExtra("Foto", mTblahanpertanianList.get(position).getFoto());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        public TextView mTextViewNamaPemilik, mTextViewLuas, mTextViewMeter, mTextViewDesa, mTextViewKecamatan, mTextViewLatitude, mTextViewLongtitude;
        public ImageView mImageViewFoto;

        public MyViewHolder (View itemView) {
            super(itemView);
            mTextViewNamaPemilik = (TextView) itemView.findViewById(R.id.tv_namapemilik);
            mTextViewLuas = (TextView) itemView.findViewById(R.id.tv_luas);
            mTextViewMeter = (TextView) itemView.findViewById(R.id.tv_meter);
            mTextViewDesa = (TextView) itemView.findViewById(R.id.tv_desa);
            mTextViewKecamatan = (TextView) itemView.findViewById(R.id.tv_kecamatan);
            mTextViewLatitude = (TextView) itemView.findViewById(R.id.tv_latitude);
            mTextViewLongtitude = (TextView) itemView.findViewById(R.id.tv_longtitude);
            mImageViewFoto = itemView.findViewById(R.id.iv_foto);
        }
    }
}
