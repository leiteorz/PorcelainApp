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
import com.tencent.mmkv.MMKV;

import java.util.List;


public class PorcelainShapeAdapter extends RecyclerView.Adapter<PorcelainShapeAdapter.ViewHolder> {
    private List<PorcelainShape> porcelainShapeList;
    private static MMKV kv;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View view){
            super(view);

            String rootDir = MMKV.initialize(view.getContext());
            kv = MMKV.defaultMMKV();

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
                int position = holder.getAbsoluteAdapterPosition();
                //形状1:凤尾瓶
                if (position == 0) kv.encode("shape",1);
                //形状2:柳叶瓶
                else if (position == 1) kv.encode("shape",2);
                //形状3:油锤瓶
                else if (position == 2) kv.encode("shape",3);
                //形状4:天球瓶
                else if (position == 3) kv.encode("shape",4);
                //形状5:玉壶春瓶
                else if (position == 4) kv.encode("shape",5);
                //形状6:花浇瓶
                else if (position == 5) kv.encode("shape",6);
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
