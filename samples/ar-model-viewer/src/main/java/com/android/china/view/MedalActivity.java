package com.android.china.view;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityMedalBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MedalActivity extends AppCompatActivity {
    ActivityMedalBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);

        initBinding();
        initToolBar();
    }
    /**
     * 初始化binding
     */
    private void initBinding(){
        binding = ActivityMedalBinding.inflate(LayoutInflater.from(this));
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar(){
        setSupportActionBar(binding.toolBar);
    }
}
