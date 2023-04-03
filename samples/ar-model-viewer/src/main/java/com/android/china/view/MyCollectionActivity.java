package com.android.china.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.china.adpter.MyCollectionAdapter;
import com.android.china.model.MyCollect;
import com.android.china.room.AppDataBase;
import com.android.china.room.dao.MyCollectionDao;
import com.android.china.utils.MyStatusBarTransparency;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityChinaGuanCangBinding;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityMyCollectionBinding;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends AppCompatActivity {
    private ActivityMyCollectionBinding binding;

    private List<MyCollect> mList = new ArrayList<>();

    MyCollectionAdapter adapter;

    private MMKV kv;

    AppDataBase db;

    MyCollectionDao dao;

    MyStatusBarTransparency myStatusBarTransparency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        initBinding();
        initStatusBarTransparency();
        initMmkv();

        initRecyclerView();
    }

    public void initBinding(){
        binding = ActivityMyCollectionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void initStatusBarTransparency(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);

        setSupportActionBar(binding.myCollectionToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("我的收藏");
    }

    private void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.myCollectionsRecyclerView.setLayoutManager(layoutManager);
        adapter = new MyCollectionAdapter(mList);
        binding.myCollectionsRecyclerView.setAdapter(adapter);
    }
}