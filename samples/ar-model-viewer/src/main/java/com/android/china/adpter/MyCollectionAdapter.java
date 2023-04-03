package com.android.china.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.model.MyCollect;
import com.google.android.material.card.MaterialCardView;
import com.google.ar.sceneform.samples.gltf.R;
import com.tencent.mmkv.MMKV;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyCollectionAdapter extends RecyclerView.Adapter<MyCollectionAdapter.ViewHolder> {
    private List<MyCollect> mList;

    private static MMKV kv;

    public MyCollectionAdapter(List<MyCollect> list){
        this.mList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView myCollectionImage;
        TextView myCollectionName;
        TextView myCollectionDescription;
        MaterialCardView myCollectionItem;

        public ViewHolder(View view){
            super(view);

            String rootDir = MMKV.initialize(view.getContext());
            kv = MMKV.defaultMMKV();

            myCollectionImage = view.findViewById(R.id.collection_image);
            myCollectionName = view.findViewById(R.id.collection_name);
            myCollectionDescription = view.findViewById(R.id.collection_description);
            myCollectionItem = view.findViewById(R.id.collection_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_my_collection_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyCollect myCollect = mList.get(position);
        holder.myCollectionName.setText(myCollect.getName());
        holder.myCollectionImage.setImageResource(myCollect.getImage());
        holder.myCollectionDescription.setText(myCollect.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
