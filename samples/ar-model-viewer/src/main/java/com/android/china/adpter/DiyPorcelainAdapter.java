package com.android.china.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.model.DiyPorcelain;
import com.google.ar.sceneform.samples.gltf.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiyPorcelainAdapter extends RecyclerView.Adapter<DiyPorcelainAdapter.ViewHolder> {
    private List<DiyPorcelain> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView diyPorcelainImage;
        TextView diyPorcelainName;

        public ViewHolder(View view){
            super(view);

            diyPorcelainImage = view.findViewById(R.id.diy_porcelain_image);
            diyPorcelainName = view.findViewById(R.id.diy_porcelain_name);
        }
    }

    public DiyPorcelainAdapter(List<DiyPorcelain> diyPorcelainList){
        mList = diyPorcelainList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_diy_porcelain_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiyPorcelain diyPorcelain = mList.get(position);
        holder.diyPorcelainImage.setImageResource(diyPorcelain.getPictureId());
        holder.diyPorcelainName.setText(diyPorcelain.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
