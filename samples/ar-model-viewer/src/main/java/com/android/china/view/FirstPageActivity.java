package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.china.adpter.ChinaAdapter;
import com.android.china.model.China;
import com.android.china.utils.ActivityContainer;
import com.android.china.utils.MyStatusBarTransparency;
import com.android.china.viewModel.NavigationStatusModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityFirstPageBinding;

import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class FirstPageActivity extends AppCompatActivity {
    private List<China> mList = new ArrayList<>();
    private List<String> mBannerList = new ArrayList();
    private Banner mBanner;
    private ActivityFirstPageBinding binding;
    private MyStatusBarTransparency myStatusBarTransparency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNavigationStatus(); //设置导航栏状态为0
        initBinding();
        initStatus();
        initData();
        initBanner();
        initChinaList();
        initRecyclerView();
        initToolbar();
        setSupportActionBar(binding.toolbarFirstPage);
        initClick();
    }
    public void initClick(){
        binding.guanCang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),ChinaGuanCangActivity.class);
                startActivity(intent);
            }
        });
    }
    public void initStatus(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);
    }
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    public void initToolbar(){
        binding.toolbarFirstPage.setTitle("首页");
    }
    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        ChinaAdapter chinaAdapter = new ChinaAdapter(mList);
        binding.recyclerView.setAdapter(chinaAdapter);
    }
    public void initBinding(){
        binding =  ActivityFirstPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
    /**
     * 设置导航栏状态
     */
    private void initNavigationStatus(){
        final NavigationStatusModel model = ViewModelProviders.of(this).get(NavigationStatusModel.class);
        model.setStatus(0);
        //         添加Activity到堆栈
        ActivityContainer.getInstance().addActivity(this);
    }

    private void initData(){
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/imgCategories.png");
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/imgbeijingtu.png");
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/imgTags.png");
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/imgdefault.png");
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/img20221013113552.png");
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/img20220923113255.png");
    }
    private void initBanner(){
        mBanner = findViewById(R.id.banner);
        mBanner.setAdapter(new BannerImageAdapter<String>(mBannerList){

            /**
             * 绑定布局数据
             *
             * @param holder   XViewHolder
             * @param data     数据实体
             * @param position 当前位置
             * @param size     总数
             */
            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                Glide.with(holder.itemView)
                        .load(mBannerList.get(position))
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        });
        mBanner.isAutoLoop(true);
        mBanner.setIndicator(new CircleIndicator(this));
        mBanner.setPageTransformer(new ZoomOutPageTransformer());
        mBanner.start();
    }
    public void initChinaList(){
        for(int i = 0 ; i< 10 ;i++){
            China china = new China("陶瓷"+"i"+"号",R.drawable.pic);
            mList.add(china);
        }
    }

    //方法一：自己控制banner的生命周期
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        mBanner.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止轮播
        mBanner.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁
        mBanner.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityContainer.getInstance().finishAllActivity();
    }
}