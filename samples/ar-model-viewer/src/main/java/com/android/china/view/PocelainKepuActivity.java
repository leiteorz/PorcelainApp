package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.china.adpter.PorcelainKepuFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityPocelainKepuBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PocelainKepuActivity extends AppCompatActivity {
    private ActivityPocelainKepuBinding binding;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private PorcelainKepuFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initToolbar();
        initData();
        initStatusandTitlebar();
//        git测试

    }
    public void initBinding(){
        binding = ActivityPocelainKepuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void initData(){
        titleList = new ArrayList<>();
        titleList.add("历史");
        titleList.add("种类");
        titleList.add("工艺");
        titleList.add("瓷匠");
        titleList.add("陶瓷小知识");
        fragmentList = new ArrayList<>();
        fragmentList.add(ChinaHistoryFragment.newInstance("历史页面",""));
        fragmentList.add(ChinaKindFragment.newInstance("种类页面",""));
        fragmentList.add(ChinaCraftFragment.newInstance("工艺页面",""));
        fragmentList.add(ChinaCraftsManFragment.newInstance("瓷匠页面",""));
        fragmentList.add(ChinaSmallKnowledgeFragment.newInstance("陶瓷小知识页面",""));
        adapter = new PorcelainKepuFragmentAdapter(getSupportFragmentManager(),fragmentList,titleList);
        binding.viewPagerKepu.setAdapter(adapter);
        binding.tabLayoutKepu.setupWithViewPager(binding.viewPagerKepu);
    }
    public void initToolbar(){
        setSupportActionBar(binding.toolbarKepu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbarKepu.setTitleMargin(26,26,26,26);

        binding.toolbarKepu.setBackgroundColor(Color.rgb(131,175,155));
        binding.toolbarKepu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(PocelainKepuActivity.this,FirstPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void initStatusandTitlebar(){
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
}