package com.android.china.adpter;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.model.China;
import com.android.china.view.BookActivity;
import com.android.china.view.FirstPageActivity;
import com.google.ar.sceneform.samples.gltf.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

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
        holder.imageView.setOnClickListener(view1 -> {
            Intent intent = new Intent(view1.getContext(), BookActivity.class);
            China china = mList.get(holder.getPosition());
            intent.putExtra("china", china);
            view1.getContext().startActivity(intent);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        China china = mList.get(position);
        holder.imageView.setImageResource(china.getImageId());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
