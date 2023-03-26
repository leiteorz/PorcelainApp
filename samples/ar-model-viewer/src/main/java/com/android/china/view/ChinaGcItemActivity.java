package com.android.china.view;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.android.china.model.GuanCang;
import com.android.china.utils.MyStatusBarTransparency;
import com.bumptech.glide.Glide;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityChinaGcItemBinding;
import com.tencent.mmkv.MMKV;

public class ChinaGcItemActivity extends AppCompatActivity {
    private ActivityChinaGcItemBinding binding;

    private MMKV kv;
    private MyStatusBarTransparency myStatusBarTransparency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
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

        String content = kv.decodeString("GuanCangContent");
        String name = kv.decodeString("GuanCangName");
        String url = kv.decodeString("GuanCangUrl");

        binding.guanCangItemText.setText(content);
        binding.guancangItemCollapsingToolbar.setTitle(name);
        binding.guancangItemImageview.setImageUrl(url);
    }

    private void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }
}