package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.china.utils.MyStatusBarTransparency;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityPorcelainStoryDetailPageBinding;
import com.tencent.mmkv.MMKV;

public class PorcelainStoryDetailPageActivity extends AppCompatActivity {
    private ActivityPorcelainStoryDetailPageBinding binding;
    private MMKV kv;
    private MyStatusBarTransparency myStatusBarTransparency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        initActivityStatus();
        initMmkv();
        initData();
    }

    private void initBinding(){
        binding = ActivityPorcelainStoryDetailPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void initActivityStatus(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);
    }

    private void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }

    private void initData(){
        String content = kv.decodeString("PorcelainStoryContent");
        String name = kv.decodeString("PorcelainStoryName");
        String url = kv.decodeString("PorcelainStoryUrl");

        binding.porcelainStoryContent.setText(content);
        binding.porcelainStoryCollapsingToolbar.setTitle(name);
        binding.porcelainStoryBarImage.setImageUrl(url);
    }

}