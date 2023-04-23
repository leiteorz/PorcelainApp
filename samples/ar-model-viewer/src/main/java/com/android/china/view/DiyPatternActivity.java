package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.china.adpter.GuanCangAdapter;
import com.android.china.adpter.PatternAdapter;
import com.android.china.model.Pattern;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityDiyPatternBinding;

import java.util.ArrayList;
import java.util.List;

public class DiyPatternActivity extends AppCompatActivity {
    private ActivityDiyPatternBinding binding;
    private PatternAdapter adapter;
    private List<Pattern> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBingding();
        initData();
        initToolbar();
        initRecyclerView();
        initData();
    }
    public void initBingding(){
        binding = ActivityDiyPatternBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void initData() {
        list = new ArrayList<>();
        Pattern pattern1 = new Pattern("1", R.drawable.pattern_1);
        list.add(pattern1);
        Pattern pattern2 = new Pattern("2", R.drawable.pattern_2);
        list.add(pattern2);
        Pattern pattern3 = new Pattern("3", R.drawable.pattern_3);
        list.add(pattern3);
        Pattern pattern4 = new Pattern("4", R.drawable.pattern_4);
        list.add(pattern4);
        Pattern pattern5 = new Pattern("4", R.drawable.pattern_5);
        list.add(pattern5);
    }
    public void initToolbar(){
        setSupportActionBar(binding.patternToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.patternToolbar.setTitleMargin(26,26,26,26);
        //设置toolbar背景 以及整个布局的背景
        binding.patternToolbar.setBackgroundColor(Color.rgb(131,175,155));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.patternRecyclerView.setLayoutManager(layoutManager);
        adapter = new PatternAdapter(list);
        binding.patternRecyclerView.setAdapter(adapter);
    }
}