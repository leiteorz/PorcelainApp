package com.android.china.adpter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.china.model.PorcelainShape;
import com.android.china.view.DiyPatternActivity;
import com.google.ar.sceneform.samples.gltf.R;

import java.util.List;

/**
 * @Author Crwei
 * date 2023/4/21 18:33
 */

public class PorcelainShapeAdapter extends RecyclerView.Adapter<PorcelainShapeAdapter.ViewHolder> {
    private List<PorcelainShape> porcelainShapeList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.cixin_img);
            textView = view.findViewById(R.id.cixin_name_text);
        }
    }
    public PorcelainShapeAdapter(List<PorcelainShape> list){
        porcelainShapeList = list;
    }
    @NonNull
    @Override
    public PorcelainShapeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diy_china_model_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DiyPatternActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PorcelainShapeAdapter.ViewHolder holder, int position) {
        PorcelainShape porcelainShape = porcelainShapeList.get(position);
        holder.imageView.setImageResource(porcelainShape.getId());
        holder.textView.setText(porcelainShape.getName());
    }

    @Override
    public int getItemCount() {
        return porcelainShapeList.size();
    }
}
