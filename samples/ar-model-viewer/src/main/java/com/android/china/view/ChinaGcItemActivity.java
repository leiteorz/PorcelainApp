package com.android.china.view;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.android.china.model.GuanCang;
import com.android.china.room.AppDataBase;
import com.android.china.room.dao.GuanCangDao;
import com.android.china.room.dao.MyCollectionDao;
import com.android.china.utils.MyStatusBarTransparency;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityChinaGcItemBinding;
import com.tencent.mmkv.MMKV;

public class ChinaGcItemActivity extends AppCompatActivity {
    private ActivityChinaGcItemBinding binding;

    private MMKV kv;
    private MyStatusBarTransparency myStatusBarTransparency;

    AppDataBase db;
    GuanCangDao dao;
    MyCollectionDao myCollectionDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        createDatabase();
        initMmkv();

        initData();
        setCollectionBtnColor();
        initStatusBarTransparency();

        onClick();
    }
    public void initStatusBarTransparency(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);
    }
    public void initBinding(){
        binding = ActivityChinaGcItemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    /**
     * 创建数据库
     */
    private void createDatabase(){
        db = AppDataBase.getInstance(this);
        dao = db.guanCangDao();
        myCollectionDao = db.myCollectionDao();
    }

    public void initData(){
        setSupportActionBar(binding.guanCangItemToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.guanCangItemToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),ChinaGuanCangActivity.class);
                startActivity(intent);
                finish();
            }
        });

        int guanCangId = getIntent().getIntExtra("guanCangId",0);
        GuanCang guanCang = dao.queryGuanCangById(guanCangId);

        binding.guanCangItemText.setText(guanCang.getContent());
        binding.guancangItemCollapsingToolbar.setTitle(guanCang.getName());
        binding.guancangItemImageview.setImageUrl(guanCang.getUrl());
    }

    private void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }

    /**
     * 设置收藏按钮的颜色
     */
    private void setCollectionBtnColor(){
        int guanCangId = getIntent().getIntExtra("guanCangId",0);
        int flag = myCollectionDao.queryIfCollectedById(guanCangId,1);   //flag为0:未收藏 flag为1:收藏

        if (flag == 1) {
            binding.floatBtn.setImageTintList(ColorStateList.valueOf
                    (ContextCompat.getColor(ChinaGcItemActivity.this,R.color.yellow)));
        } else if(flag == 0) {
            binding.floatBtn.setImageTintList(ColorStateList.valueOf
                    (ContextCompat.getColor(ChinaGcItemActivity.this,R.color.black40)));
        }
    }

    /**
     * 点击事件
     */
    private void onClick(){
        int guanCangId = getIntent().getIntExtra("guanCangId",0);
        /**
         * 收藏按钮的点击事件
         */
        binding.floatBtn.setOnClickListener(new View.OnClickListener() {
            int flag = myCollectionDao.queryIfCollectedById(guanCangId,1);   //flag为0:未收藏 flag为1:收藏
            @Override
            public void onClick(View v) {
                if (flag == 0){
                    //未收藏变成已收藏,设置Tint为yellow
                    binding.floatBtn.setImageTintList(ColorStateList.valueOf
                            (ContextCompat.getColor(ChinaGcItemActivity.this,R.color.yellow)));
                    flag = 1;
                    myCollectionDao.updateIsCollected(flag,guanCangId,1);
                }else if(flag == 1){
                    //已收藏变成未收藏,设置Tint为black40
                    binding.floatBtn.setImageTintList(ColorStateList.valueOf
                            (ContextCompat.getColor(ChinaGcItemActivity.this,R.color.black40)));
                    flag = 0;
                    myCollectionDao.updateIsCollected(flag,guanCangId,1);
                }
            }
        });
    }
}