package com.android.china;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.china.room.AppDataBase;
import com.android.china.utils.ActivityContainer;
import com.android.china.utils.MyApplication;
import com.android.china.utils.MyStatusBarTransparency;
import com.android.china.view.FirstPageActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityMainBinding;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    MyStatusBarTransparency myStatusBarTransparency;
    ActivityMainBinding binding;
    AppDataBase db;

    private List<String> mBannerList = new ArrayList<>();
    //随便写的一句话来测试能不能合并
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        添加Activity到堆栈
        ActivityContainer.getInstance().addActivity(this);

//        initStatus();
//        initBinding();
//        initBannerData();
//        initBanner();
//        createDatabase();
        goToFirtPage();
    }

    /**
     * 创建数据库
     */
    private void createDatabase(){
        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"TaoCi_db").build();
    }

    private void initStatus(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);
    }

    /**
     * 加载binding
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    /**
     * 跳转至首页
     */
    private void goToFirtPage() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), FirstPageActivity.class);
        startActivity(intent);
    }

    /**
     * 为mBannerList赋值
     */
    private void initBannerData(){
        mBannerList.add("https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/E5Y91Y1(T4QWZ}X_CK(1]2Q.jpg");
        mBannerList.add("https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/P98C{7D[EB_K]}0PMRVLP`D.jpg");
        mBannerList.add("https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/[(N6LM]4R]((@AXA}605O30.jpg");
    }

    private void initBanner(){
        binding.launchPageBanner.setAdapter(new BannerImageAdapter<String>(mBannerList) {
            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                Glide.with(holder.itemView)
                        .load(mBannerList.get(position))
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(2)))
                        .into(holder.imageView);
            }
        });
        binding.launchPageBanner.isAutoLoop(false);
        binding.launchPageBanner.setIndicator(new CircleIndicator(this));
        binding.launchPageBanner.setPageTransformer(new AlphaPageTransformer());
        binding.launchPageBanner.start();

//        Log.d("leiteorz",String.valueOf(binding.launchPageBanner.getCurrentItem()));
    }
}