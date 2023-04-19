package com.android.china.view;

import static com.android.china.utils.MyApplication.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.china.adpter.ArPorcelainAdapter;
import com.android.china.adpter.DiyPorcelainAdapter;
import com.android.china.adpter.PorcelainKepuFragmentAdapter;
import com.android.china.model.ArPorcelain;
import com.android.china.model.DiyPorcelain;
import com.android.china.utils.MyApplication;
import com.android.china.utils.MyStatusBarTransparency;
import com.android.china.viewModel.NavigationStatusModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivitySecondPageBinding;

import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SecondPageActivity extends AppCompatActivity {
    private List<ArPorcelain> arPorcelainList = new ArrayList<>();
    private ArPorcelainAdapter arPorcelainAdapter;
    private PorcelainKepuFragmentAdapter adapter;
    private List<DiyPorcelain> diyPorcelainList = new ArrayList<>();
    private DiyPorcelainAdapter diyPorcelainAdapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;
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
        initData();
//        initDiyTabLayout();

//        initArPorcelains();
//        initArPorcelainRecyclerView();

//        initDiyPorcelains();
//        initDiyPorcelainRecyclerView();
    }
    public void initDiyTabLayout(){
        titleList = Arrays.asList("DIY", "AR","识别");
        for(int i = 0;i<3;i++){
            Context context = getContext();
            TabLayout.Tab tab = binding.testTablayout.newTab();
            View view = LayoutInflater.from(this).inflate(R.layout.test_tablayout,null);
            TextView textView = view.findViewById(R.id.choose_icon_tab_tv);
            textView.setText(titleList.get(i));
            tab.setCustomView(view);
            binding.testTablayout.addTab(tab);
        }
    }
    public void initData(){
        titleList = Arrays.asList("DIY", "AR","识别");
        fragmentList = Arrays.asList(
                TestFragment2.newInstance("DIY",""),
                TestFragment2.newInstance("AR",""),
                TestFragment2.newInstance("识别","")
                );
//        for(int i = 0;i<3;i++){
//            Context context = getContext();
//            TabLayout.Tab tab = binding.testTablayout.newTab();
//            View view = LayoutInflater.from(this).inflate(R.layout.test_tablayout,null);
//            TextView textView = view.findViewById(R.id.choose_icon_tab_tv);
//            textView.setText(titleList.get(i));
//            tab.setCustomView(view);
//            binding.testTablayout.addTab(tab);
//            binding.testTablayout.setupWithViewPager(binding.viewPagerTest);
//        }
        adapter = new PorcelainKepuFragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        binding.viewPagerTest.setAdapter(adapter);
        binding.testTablayout.setupWithViewPager(binding.viewPagerTest);
        for (int i = 0; i < adapter.getCount(); i++) {
            TabLayout.Tab tab = binding.testTablayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.test_tablayout);//给每一个tab设置view
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.choose_icon_tab_tv).setSelected(true);//第一个tab被选中
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.choose_icon_tab_tv);
            textView.setText(titleList.get(i));//设置tab上的文字
        }
        binding.testTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.choose_icon_tab_tv).setSelected(true);
                binding.viewPagerTest.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.choose_icon_tab_tv).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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

//    public void initArPorcelainRecyclerView(){
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        binding.arPorcelainRecyclerView.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        arPorcelainAdapter = new ArPorcelainAdapter(arPorcelainList);
//        binding.arPorcelainRecyclerView.setAdapter(arPorcelainAdapter);
//    }

//    private void initDiyPorcelainRecyclerView(){
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        binding.diyPorcelainRecyclerView.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        diyPorcelainAdapter = new DiyPorcelainAdapter(diyPorcelainList);
//        binding.diyPorcelainRecyclerView.setAdapter(diyPorcelainAdapter);
//    }

//    private void initArPorcelains(){
//        arPorcelainList.add(new ArPorcelain("青花瓷_1", R.drawable.qing_hua_ci));
//        arPorcelainList.add(new ArPorcelain("青花瓷_2",R.drawable.model_2));
//        arPorcelainList.add(new ArPorcelain("白瓷",R.drawable.model_4));
//        arPorcelainList.add(new ArPorcelain("青花瓷_3",R.drawable.model_3));
//    }

//    private void initDiyPorcelains(){
//        for (int i=0;i<3;i++){
//            diyPorcelainList.add(new DiyPorcelain("AR展示",R.drawable.qing_hua_ci));
//            diyPorcelainList.add(new DiyPorcelain("AR展示",R.drawable.qing_hua_ci));
//        }
//    }

    public void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }
}