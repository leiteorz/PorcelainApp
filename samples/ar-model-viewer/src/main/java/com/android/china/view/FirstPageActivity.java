package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.china.adpter.ChinaAdapter;
import com.android.china.model.China;
import com.android.china.utils.ActivityContainer;
import com.android.china.utils.MyApplication;
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
        /**
         * 跳转至馆藏
         */
        binding.guanCang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),ChinaGuanCangActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 跳转至故事
         */
        binding.stroyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FirstPageActivity.this,PorcelainStoryActivity.class);
                startActivity(intent);
            }
        });
        binding.porcelainKepu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(FirstPageActivity.this,PocelainKepuActivity.class);
                startActivity(intent);
            }
        });
        binding.searchDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(binding.searchEditText.getText().toString())){
                    Toast.makeText(FirstPageActivity.this,"请输入查询内容！",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    intent.setClass(FirstPageActivity.this,SearchActivity.class);
                    intent.putExtra("editView",binding.searchEditText.getText().toString());
                    startActivity(intent);
                }
            }
        });
        binding.searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i == EditorInfo.IME_ACTION_UNSPECIFIED || i == EditorInfo.IME_ACTION_SEARCH) && keyEvent != null&& !TextUtils.isEmpty(binding.searchEditText.getText().toString())) {
                    Intent intent = new Intent();
                    intent.setClass(FirstPageActivity.this,SearchActivity.class);
                    intent.putExtra("editView",binding.searchEditText.getText().toString());
                    startActivity(intent);
                }else if(TextUtils.isEmpty(binding.searchEditText.getText().toString())){
                    Toast.makeText(FirstPageActivity.this,"请输入查询内容！",Toast.LENGTH_SHORT).show();
                }
                return false;
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
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/imgCache_-68b9853ad839dee4.jpg");
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/imgCache_-40a567fb8d8c357.jpg");
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/imgTags.png");
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/img20220913205647.png");
        mBannerList.add("https://picgo-wei.oss-cn-shenzhen.aliyuncs.com/imgarticlebeijingtu.png");
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
        for(int i = 0 ; i< 1 ;i++){
            China china = new China("\t\t\t\t\t\t\t\t如果你接触陶瓷时间不算久，正处于兴趣萌芽期， 以下三本内容权威、扎实，又十分系统的“初学者必读读物”，能够让刚接触陶瓷的朋友，对源远流长、丰富绮丽的中国陶瓷史形成一个初步概念。 兴趣能满足、疑难可解答，爱好者们在这一阶段的诉求，它们照单全收。\n\t\t\t\t\t\t\t\t《中国陶瓷史》作者叶喆民先生家学渊源，自幼随父叶麟趾教授学习陶瓷，后在故宫博物院的十六年间，他得到陈万里、孙瀛洲等名师亲" +
                    "自指导，并追随二位前往全国各地探访，进行陶瓷鉴定与窑址考察工作。收货全明星教育阵容指导、注重将理论与实际相结合的叶喆民，不但出版过多部古陶瓷研究专著，发表了百余篇学术论文，就连我们再熟悉不过的汝窑窑址，也是叶先生首先发现并认定的。",R.drawable.book1,"博物馆里的奇妙中国","作者：约翰夫斯基");
            mList.add(china);
            China china1 = new China("\t\t\t\t\t\t\t\t瓷器是中国的创造，是世界的语言。瓷器是水、火、土的完美结合，是人类想像力和创造力的最好体现，是自然与人文交汇的结晶。瓷器凝结了我们祖先的智慧，满足了社会生活的需要，积聚了时代与民族的精华，成为中国乃至世界科技、工艺、文化史上的一项伟大发明，成为外国语汇里中国的代名词。\n\t\t\t\t\t\t\t\t《瓷器中国》一书用权威、通俗的语言，配以大量高清图片，讲述了中国瓷器三千年的发展历程，展现了中华民族博大精深的精神世界和审美情怀。",R.drawable.book2,"瓷器中国","作者：陈克伦");
            mList.add(china1);
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
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}