package com.android.china.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.model.China;
import com.google.ar.sceneform.samples.gltf.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author Crwei
 * date 2023/3/14 20:58
 */

public class ChinaAdapter extends RecyclerView.Adapter<ChinaAdapter.ViewHolder> {
    private List<China> mList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.recycle_image_view);
            textView = view.findViewById(R.id.recycle_text_view);
        }
    }
    public ChinaAdapter(List<China> list){
        mList = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_first_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        China china = mList.get(position);
        holder.imageView.setImageResource(china.getImageId());
        holder.textView.setText(china.getDescribe());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
