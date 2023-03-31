package com.android.china.adpter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.china.model.ChinaHistory;
import com.android.china.model.GuanCang;
import com.android.china.view.ChinaGcItemActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.ar.sceneform.samples.gltf.R;

import java.util.List;

/**
 * @Author Crwei
 * date 2023/3/24 20:43
 */

public class KepuHistoryAdapter extends RecyclerView.Adapter<KepuHistoryAdapter.ViewHolder> {
    private List<ChinaHistory> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView historyImage;
        TextView historyName;
        TextView historyDescription;
        MaterialCardView historyItem;

        public ViewHolder(View view){
            super(view);
            historyImage = view.findViewById(R.id.kepu_history_iamge);
            historyName = view.findViewById(R.id.kepu_history_textView);
            historyDescription = view.findViewById(R.id.kepu_history_description);
            historyItem = view.findViewById(R.id.kepu_history_item);
        }
    }

    public KepuHistoryAdapter(List<ChinaHistory> historyList){
        mList = historyList;
    }

    @Override
    public KepuHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_kepu_history_item,parent,false);
        KepuHistoryAdapter.ViewHolder holder = new KepuHistoryAdapter.ViewHolder(view);
        holder.historyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(KepuHistoryAdapter.ViewHolder holder, int position) {
        ChinaHistory history = mList.get(position);
        holder.historyName.setText(history.getName());
        holder.historyDescription.setText(history.getDescription());
        holder.historyImage.setImageResource(history.getImageId());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void refreshData(List<ChinaHistory> list){
        this.mList = list;
        notifyDataSetChanged();

    }

}
