package com.android.china.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.android.china.adpter.ArPorcelainAdapter;
import com.android.china.model.ArPorcelain;
import com.android.china.utils.MyStatusBarTransparency;
import com.android.china.viewModel.NavigationStatusModel;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivitySecondPageBinding;

import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

public class SecondPageActivity extends AppCompatActivity {
    private List<ArPorcelain> arPorcelainList = new ArrayList<>();
    private ArPorcelainAdapter arPorcelainAdapter;
    MyStatusBarTransparency myStatusBarTransparency;
    private ActivitySecondPageBinding binding;
    private MMKV kv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        initNavigationStatus();
        initToolbar();
        initStatus();

        initMmkv();

        initArPorcelains();
        initArPorcelainRecyclerView();
    }

    private void initNavigationStatus(){
        final NavigationStatusModel model = ViewModelProviders.of(this).get(NavigationStatusModel.class);
        model.setStatus(1);
    }

    public void initBinding(){
        binding = ActivitySecondPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void initStatus(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);
    }

    @SuppressLint({"ResourceAsColor", "ResourceType"})
    public void initToolbar(){
        binding.toolbarSecondPage.setTitle("陶瓷创作");
    }

    public void initArPorcelainRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.arPorcelainRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        arPorcelainAdapter = new ArPorcelainAdapter(arPorcelainList);
        binding.arPorcelainRecyclerView.setAdapter(arPorcelainAdapter);
    }

    private void initArPorcelains(){
        ArPorcelain arPorcelain = new ArPorcelain("青花瓷", R.drawable.qing_hua_ci);
        arPorcelainList.add(arPorcelain);
    }

    public void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }
}