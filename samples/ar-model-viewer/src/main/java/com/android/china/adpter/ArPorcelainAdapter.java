package com.android.china.adpter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.view.Activity;
import com.android.china.model.ArPorcelain;
import com.google.ar.sceneform.samples.gltf.R;
import com.tencent.mmkv.MMKV;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ArPorcelainAdapter extends RecyclerView.Adapter<ArPorcelainAdapter.ViewHolder> {
    private List<ArPorcelain> mArPorcelainList;

    private static MMKV kv;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView arPorcelainImage;
        TextView arPorcelainName;

        public ViewHolder(View view){
            super(view);

            String rootDir = MMKV.initialize(view.getContext());
            kv = MMKV.defaultMMKV();

            arPorcelainImage = (ImageView) view.findViewById(R.id.ar_porcelain_item_picture);
            arPorcelainName = (TextView) view.findViewById(R.id.ar_porcelain_item_name);
        }
    }

    public ArPorcelainAdapter(List<ArPorcelain> arPorcelainList){
        mArPorcelainList = arPorcelainList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_ar_porcelain_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        /**
         * 点击图片进入AR相机
         */
        holder.arPorcelainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAbsoluteAdapterPosition();
                //青花瓷_1
                if(position == 0){
                    kv.encode("ArModel","models/QingHuaCi.glb");
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), Activity.class);
                    view.getContext().startActivity(intent);
                }
                //青花瓷_2
                if(position == 1){
                    kv.encode("ArModel","models/model_2.glb");
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), Activity.class);
                    view.getContext().startActivity(intent);
                }
                //白瓷
                if (position == 2){
                    kv.encode("ArModel","models/model_4.glb");
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), Activity.class);
                    view.getContext().startActivity(intent);
                }
                //青花瓷_3
                if(position == 3){
                    kv.encode("ArModel","models/model_3.glb");
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), Activity.class);
                    view.getContext().startActivity(intent);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArPorcelain arPorcelain = mArPorcelainList.get(position);
        holder.arPorcelainImage.setImageResource(arPorcelain.getPicture_id());
        holder.arPorcelainName.setText(arPorcelain.getName());
    }

    @Override
    public int getItemCount() {
        return mArPorcelainList.size();
    }
}
