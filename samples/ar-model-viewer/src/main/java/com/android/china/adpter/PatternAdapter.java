package com.android.china.adpter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.china.model.Pattern;
import com.google.ar.sceneform.samples.gltf.R;
import com.tencent.mmkv.MMKV;

import java.util.List;

/**
 * @Author Crwei
 * date 2023/4/23 20:06
 */

public class PatternAdapter extends RecyclerView.Adapter<PatternAdapter.ViewHolder> {
    private List<Pattern> list;
    private static MMKV kv;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        CardView cardView;
        public ViewHolder(View view){
            super(view);

            String rootDir = MMKV.initialize(view.getContext());
            kv = MMKV.defaultMMKV();

            imageView = view.findViewById(R.id.pattern_imageView);
            cardView = view.findViewById(R.id.pattern_card);
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

        /**
         * 点击选择花纹
         */
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int last_check = kv.decodeInt("patternCheck",-1);   //获取上一个被选中的图案
                //将上一个被选中的图案取消
                if (last_check != -1) {
                    list.get(last_check).setCheck(0);
                    notifyItemChanged(last_check);
                }
                //设置当前图案为选中模式
                holder.cardView.setCardBackgroundColor(Color.BLUE);
                list.get(holder.getAbsoluteAdapterPosition()).setCheck(1);
                kv.encode("patternCheck",holder.getAbsoluteAdapterPosition());
                /**
                 * 分情况选择
                 */
                int position = holder.getAbsoluteAdapterPosition();
                if (position == 0) kv.encode("pattern",1);
                if (position == 1) kv.encode("pattern",2);
                if (position == 2) kv.encode("pattern",3);
                if (position == 3) kv.encode("pattern",4);
                if (position == 4) kv.encode("pattern",5);
                if (position == 5) kv.encode("pattern",6);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatternAdapter.ViewHolder holder, int position) {
        Pattern pattern = list.get(position);
        holder.imageView.setImageResource(pattern.getId());
        //设置颜色
        if (pattern.getCheck() == 0) holder.cardView.setCardBackgroundColor(Color.WHITE);
        else if (pattern.getCheck() == 1) holder.cardView.setCardBackgroundColor(Color.BLUE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
