package com.android.china.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.model.PorcelainStory;
import com.google.android.material.button.MaterialButton;
import com.google.ar.sceneform.samples.gltf.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PorcelainStoryAdapter extends RecyclerView.Adapter<PorcelainStoryAdapter.ViewHolder> {
    private List<PorcelainStory> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView porcelainStoryImage;
        TextView porcelainStoryName;
        TextView porcelainStoryDescription;
        MaterialButton porcelainStoryBtn;

        public ViewHolder(View view){
            super(view);

            porcelainStoryImage = view.findViewById(R.id.porcelain_story_image);
            porcelainStoryName = view.findViewById(R.id.porcelain_story_name);
            porcelainStoryDescription = view.findViewById(R.id.porcelain_story_description);
            porcelainStoryBtn = view.findViewById(R.id.porcelain_story_btn);
        }
    }

    public PorcelainStoryAdapter(List<PorcelainStory> list){
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_porcelain_story_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PorcelainStory porcelainStory = mList.get(position);
        holder.porcelainStoryName.setText(porcelainStory.getName());
        holder.porcelainStoryDescription.setText(porcelainStory.getDescription());
        holder.porcelainStoryImage.setImageResource(porcelainStory.getImageId());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
