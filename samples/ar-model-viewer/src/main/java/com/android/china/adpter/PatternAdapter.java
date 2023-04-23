package com.android.china.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.china.model.Pattern;
import com.google.ar.sceneform.samples.gltf.R;

import java.util.List;

/**
 * @Author Crwei
 * date 2023/4/23 20:06
 */

public class PatternAdapter extends RecyclerView.Adapter<PatternAdapter.ViewHolder> {
    private List<Pattern> list;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.pattern_imageView);
        }
    }
    public PatternAdapter(List<Pattern> list){
        this.list = list;
    }
    @NonNull
    @Override
    public PatternAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diy_pattern_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatternAdapter.ViewHolder holder, int position) {
        Pattern pattern = list.get(position);
        holder.imageView.setImageResource(pattern.getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
