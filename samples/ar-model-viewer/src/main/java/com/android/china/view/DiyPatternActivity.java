package com.android.china.view;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.china.adpter.GuanCangAdapter;
import com.android.china.adpter.PatternAdapter;
import com.android.china.model.Pattern;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityDiyPatternBinding;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class DiyPatternActivity extends AppCompatActivity {
    private String TAG = "leiteorz";
    private ActivityDiyPatternBinding binding;
    private PatternAdapter adapter;
    private List<Pattern> list;
    private MMKV shapeKv;
    private MMKV patternKv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initMmkv();
        initShape();
        initData();
        initToolbar();
        initRecyclerView();
        initData();
        onClick();
    }
    public void initBinding(){
        binding = ActivityDiyPatternBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void initMmkv(){
        String rootDir = MMKV.initialize(this);
        shapeKv = MMKV.defaultMMKV();
        patternKv = MMKV.defaultMMKV();
    }

    public void initData() {
        list = new ArrayList<>();
        Pattern pattern1 = new Pattern("1", R.drawable.pattern_2);
        list.add(pattern1);
        Pattern pattern2 = new Pattern("2", R.drawable.pattern_8);
        list.add(pattern2);
        Pattern pattern3 = new Pattern("3", R.drawable.pattern_1);
        list.add(pattern3);
        Pattern pattern4 = new Pattern("4", R.drawable.pattern_3);
        list.add(pattern4);
        Pattern pattern5 = new Pattern("5", R.drawable.pattern_5);
        list.add(pattern5);
        Pattern pattern6 = new Pattern("6",R.drawable.pattern_7);
        list.add(pattern6);
    }
    public void initToolbar(){
        setSupportActionBar(binding.patternToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.patternToolbar.setTitleMargin(26,26,26,26);
        //设置toolbar背景 以及整个布局的背景
        binding.patternToolbar.setBackgroundColor(Color.rgb(131,175,155));
//        binding.patternLayout.setBackgroundColor(Color.rgb(81,117,115));
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
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.patternRecyclerView.setLayoutManager(layoutManager);
        adapter = new PatternAdapter(list);
        binding.patternRecyclerView.setAdapter(adapter);
    }

    /**
     * 加载形状
     */
    public void initShape(){
        int shape = shapeKv.decodeInt("shape");
        if (shape == 1) binding.shapeImage.setImageResource(R.drawable.diy11);
        if (shape == 2) binding.shapeImage.setImageResource(R.drawable.diy21);
        if (shape == 3) binding.shapeImage.setImageResource(R.drawable.diy31);
        if (shape == 4) binding.shapeImage.setImageResource(R.drawable.diy41);
        if (shape == 5) binding.shapeImage.setImageResource(R.drawable.diy51);
        if (shape == 6) binding.shapeImage.setImageResource(R.drawable.diy61);
    }

    /**
     * 单击事件
     */
    public void onClick(){
        /**
         * 点击选择按钮,更新花纹
         */
        binding.checkPatternBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shape = shapeKv.decodeInt("shape");
                int pattern = patternKv.decodeInt("pattern");
                if (shape == 1){
                    if (pattern == 1) binding.shapeImage.setImageResource(R.drawable.diy11);
                    if (pattern == 2) binding.shapeImage.setImageResource(R.drawable.diy12);
                    if (pattern == 3) binding.shapeImage.setImageResource(R.drawable.diy13);
                    if (pattern == 4) binding.shapeImage.setImageResource(R.drawable.diy14);
                    if (pattern == 5) binding.shapeImage.setImageResource(R.drawable.diy15);
                }
                if (shape == 2){
                    if (pattern == 1) binding.shapeImage.setImageResource(R.drawable.diy21);
                    if (pattern == 2) binding.shapeImage.setImageResource(R.drawable.diy22);
                    if (pattern == 3) binding.shapeImage.setImageResource(R.drawable.diy23);
                    if (pattern == 4) binding.shapeImage.setImageResource(R.drawable.diy24);
                    if (pattern == 5) binding.shapeImage.setImageResource(R.drawable.diy25);
                }
                if (shape == 3){
                    if (pattern == 1) binding.shapeImage.setImageResource(R.drawable.diy31);
                    if (pattern == 2) binding.shapeImage.setImageResource(R.drawable.diy32);
                    if (pattern == 3) binding.shapeImage.setImageResource(R.drawable.diy33);
                    if (pattern == 4) binding.shapeImage.setImageResource(R.drawable.diy34);
                    if (pattern == 5) binding.shapeImage.setImageResource(R.drawable.diy35);
                }
                if (shape == 4){
                    if (pattern == 1) binding.shapeImage.setImageResource(R.drawable.diy41);
                    if (pattern == 2) binding.shapeImage.setImageResource(R.drawable.diy42);
                    if (pattern == 3) binding.shapeImage.setImageResource(R.drawable.diy43);
                    if (pattern == 4) binding.shapeImage.setImageResource(R.drawable.diy44);
                    if (pattern == 5) binding.shapeImage.setImageResource(R.drawable.diy45);
                }
                if (shape == 5){
                    if (pattern == 1) binding.shapeImage.setImageResource(R.drawable.diy51);
                    if (pattern == 2) binding.shapeImage.setImageResource(R.drawable.diy52);
                    if (pattern == 3) binding.shapeImage.setImageResource(R.drawable.diy53);
                    if (pattern == 4) binding.shapeImage.setImageResource(R.drawable.diy54);
                    if (pattern == 5) binding.shapeImage.setImageResource(R.drawable.diy55);
                }
                if (shape == 6){
                    if (pattern == 1) binding.shapeImage.setImageResource(R.drawable.diy61);
                    if (pattern == 2) binding.shapeImage.setImageResource(R.drawable.diy62);
                    if (pattern == 3) binding.shapeImage.setImageResource(R.drawable.diy63);
                    if (pattern == 4) binding.shapeImage.setImageResource(R.drawable.diy64);
                    if (pattern == 5) binding.shapeImage.setImageResource(R.drawable.diy65);
                }
            }
        });
    }
}