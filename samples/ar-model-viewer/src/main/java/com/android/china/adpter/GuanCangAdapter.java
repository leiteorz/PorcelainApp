package com.android.china.adpter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.model.GuanCang;
import com.android.china.utils.MyApplication;
import com.android.china.view.ChinaGcItemActivity;
import com.android.china.view.ChinaGuanCangActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.ar.sceneform.samples.gltf.R;
import com.tencent.mmkv.MMKV;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuanCangAdapter extends RecyclerView.Adapter<GuanCangAdapter.ViewHolder>{
    private List<GuanCang> mList;
    private static MMKV kv;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView guanCangImage;
        TextView guanCangName;
        TextView guanCangDescription;
        MaterialCardView guanCangItem;

        public ViewHolder(View view){
            super(view);

            String rootDir = MMKV.initialize(view.getContext());
            kv = MMKV.defaultMMKV();

            guanCangImage = view.findViewById(R.id.guan_cang_image);
            guanCangName = view.findViewById(R.id.guan_cang_name);
            guanCangDescription = view.findViewById(R.id.guan_cang_description);
            guanCangItem = view.findViewById(R.id.guan_cang_item);
        }
    }

    public GuanCangAdapter(List<GuanCang> guanCangList){
        mList = guanCangList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_guan_cang_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.guanCangItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAbsoluteAdapterPosition();
                /**
                 * 把id(对应position)通过Intent传入点进去的页面中
                 */
                Intent intent = new Intent();
                intent.setClass(view.getContext(),ChinaGcItemActivity.class);
                intent.putExtra("guanCangId",position+1);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GuanCang guanCang = mList.get(position);
        holder.guanCangName.setText(guanCang.getName());
        holder.guanCangDescription.setText(guanCang.getDescription());
        holder.guanCangImage.setImageResource(guanCang.getImageId());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshData(List<GuanCang> list){
        this.mList = list;
        notifyDataSetChanged();
    }
}
