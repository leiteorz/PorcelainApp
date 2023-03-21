package com.android.china.view;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.ar.sceneform.lullmodel.Color;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityChinaGcItemBinding;

public class ChinaGcItemActivity extends AppCompatActivity {
    private ActivityChinaGcItemBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initData();
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
        binding.collapseActionView.setTitle("测试Title");
        Glide.with(this).load("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/imgCategories.png").into(binding.guancangItemImageview);
        binding.guanCangItemText.setText("测试Text");
    }
}