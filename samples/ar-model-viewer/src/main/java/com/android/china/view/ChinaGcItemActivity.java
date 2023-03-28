package com.android.china.view;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.android.china.model.GuanCang;
import com.android.china.room.AppDataBase;
import com.android.china.room.dao.GuanCangDao;
import com.android.china.utils.MyStatusBarTransparency;
import com.bumptech.glide.Glide;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityChinaGcItemBinding;
import com.tencent.mmkv.MMKV;

public class ChinaGcItemActivity extends AppCompatActivity {
    private ActivityChinaGcItemBinding binding;

    private MMKV kv;
    private MyStatusBarTransparency myStatusBarTransparency;

    AppDataBase db;
    GuanCangDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        createDatabase();
        initMmkv();
        initData();
        initStatusBarTransparency();
    }
    public void initStatusBarTransparency(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);

    }
    public void initBinding(){
        binding = ActivityChinaGcItemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    /**
     * 创建数据库
     */
    private void createDatabase(){
        db = AppDataBase.getInstance(this);
        dao = db.guanCangDao();
    }

    public void initData(){
        setSupportActionBar(binding.guanCangItemToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.guanCangItemToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),ChinaGuanCangActivity.class);
                startActivity(intent);
                finish();
            }
        });

        int guanCangId = getIntent().getIntExtra("guanCangId",0);
        GuanCang guanCang = dao.queryGuanCangById(guanCangId);

        binding.guanCangItemText.setText(guanCang.getContent());
        binding.guancangItemCollapsingToolbar.setTitle(guanCang.getName());
        binding.guancangItemImageview.setImageUrl(guanCang.getUrl());
    }

    private void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }
}