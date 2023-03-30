package com.android.china.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.android.china.adpter.ArPorcelainAdapter;
import com.android.china.adpter.DiyPorcelainAdapter;
import com.android.china.model.ArPorcelain;
import com.android.china.model.DiyPorcelain;
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

    private List<DiyPorcelain> diyPorcelainList = new ArrayList<>();
    private DiyPorcelainAdapter diyPorcelainAdapter;

    MyStatusBarTransparency myStatusBarTransparency;
    private ActivitySecondPageBinding binding;
    private MMKV kv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        initNavigationStatus();
        initStatus();

        initMmkv();

        initArPorcelains();
        initArPorcelainRecyclerView();

        initDiyPorcelains();
        initDiyPorcelainRecyclerView();
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

    public void initArPorcelainRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.arPorcelainRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        arPorcelainAdapter = new ArPorcelainAdapter(arPorcelainList);
        binding.arPorcelainRecyclerView.setAdapter(arPorcelainAdapter);
    }

    private void initDiyPorcelainRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.diyPorcelainRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        diyPorcelainAdapter = new DiyPorcelainAdapter(diyPorcelainList);
        binding.diyPorcelainRecyclerView.setAdapter(diyPorcelainAdapter);
    }

    private void initArPorcelains(){
        for (int i=0;i<8;i++){
            ArPorcelain arPorcelain = new ArPorcelain("青花瓷", R.drawable.qing_hua_ci);
            arPorcelainList.add(arPorcelain);
        }
    }

    private void initDiyPorcelains(){
        for (int i=0;i<3;i++){
            DiyPorcelain diyPorcelain1 = new DiyPorcelain("花浇形",R.drawable.qing_hua_ci);
            diyPorcelainList.add(diyPorcelain1);
            DiyPorcelain diyPorcelain2 = new DiyPorcelain("军持形",R.drawable.qing_hua_ci);
            diyPorcelainList.add(diyPorcelain2);
        }
    }

    public void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }
}