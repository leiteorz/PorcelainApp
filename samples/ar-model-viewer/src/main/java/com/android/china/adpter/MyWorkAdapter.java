package com.android.china.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.model.MyWork;
import com.google.ar.sceneform.samples.gltf.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyWorkAdapter extends RecyclerView.Adapter<MyWorkAdapter.ViewHolder> {
    private List<MyWork> mMyWorkList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView workImage;    //作品图片
        TextView workName;  //作品名称

        public ViewHolder(View view) {
            super(view);
            workImage = (ImageView) view.findViewById(R.id.work_image);
            workName = (TextView) view.findViewById(R.id.work_name);
        }
    }

    public MyWorkAdapter(List<MyWork> myWorkList){
        mMyWorkList = myWorkList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_my_work_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyWork myWork = mMyWorkList.get(position);
        holder.workImage.setImageResource(myWork.getPicture_id());
        holder.workName.setText(myWork.getName());
    }

    @Override
    public int getItemCount() {
        return mMyWorkList.size();
    }
}
