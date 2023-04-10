package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
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
import com.bumptech.glide.annotation.GlideExtension;
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
        initClick();
    }
    public void initClick(){
        /**
         * 跳转至馆藏
         */
        binding.guanCang.setOnClickListener(view -> {
            Intent intent = new Intent(FirstPageActivity.this,ChinaGuanCangActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });
        /**
         * 跳转至故事
         */
        binding.stroyBtn.setOnClickListener( view -> {
            Intent intent = new Intent(FirstPageActivity.this,PorcelainStoryActivity.class);
            startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });
        /**
         * 跳转至科普
         */
        binding.porcelainKepu.setOnClickListener(view -> {
            Intent intent = new Intent(FirstPageActivity.this,PocelainKepuActivity.class);
            startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });
        /**
         * 搜索框以及回车查询
         */
        binding.searchDiary.setOnClickListener(view -> {
            if(TextUtils.isEmpty(binding.searchEditText.getText().toString())){
                Toast.makeText(FirstPageActivity.this,"请输入查询内容！",Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(FirstPageActivity.this,SearchActivity.class);
                intent.putExtra("editView",binding.searchEditText.getText().toString());
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }
        });
        binding.searchEditText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if ((i == EditorInfo.IME_ACTION_UNSPECIFIED || i == EditorInfo.IME_ACTION_SEARCH) && keyEvent != null&& !TextUtils.isEmpty(binding.searchEditText.getText().toString())) {
                Intent intent = new Intent(FirstPageActivity.this,SearchActivity.class);
                intent.putExtra("editView",binding.searchEditText.getText().toString());
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }else if(TextUtils.isEmpty(binding.searchEditText.getText().toString())){
                Toast.makeText(FirstPageActivity.this,"请输入查询内容！",Toast.LENGTH_SHORT).show();
            }
            return false;
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
        setSupportActionBar(binding.toolbarFirstPage);
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
        mBannerList.add("https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/A1E7BEBFDE1D4987A89DC12D2C3097E2.jpg");
        mBannerList.add("https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/E77DAC60958E24B8B10B67CB97CBA787.jpg");
        mBannerList.add("https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/2FDC2F90282E2F0B31A442017EA9C26F.jpg");
        mBannerList.add("https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/F533302960D9F6102809CAFABCAA9B81.jpg");
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
            China china = new China("\t\t\t\t\t\t\t\t《古瓷之光》是知名瓷人、陶瓷文化研究者涂睿明的全新力作。以朝代为序，遴选了中国陶瓷艺术史上77件至美珍宝，记录下陶艺与中华文明碰撞的每个精彩瞬间，深入讲述中国陶瓷艺术的美学变迁。\n\n\t\t\t\t\t\t\t\t从古瓷的造型、色彩、材质、工艺、历史背景等角度切入，全方位展现中国陶瓷征服世界的美学魅力，重点解读其美在何处、普通人如何欣赏以及这些陶瓷器在中国古代社会文化中的功能与角色，让我们得以从一件件陶瓷作品中，探寻到历代的审美雅趣、民俗风情与匠心工艺。\n\n\t\t\t\t\t\t\t\t读完本书，当我们在博物馆中面对一件瓷器时，便懂得如何去欣赏它造型的优雅、颜色的美妙、画面的意趣、细节的深意、背后的故事……对中国陶瓷的美，产生更加具体、深刻的认知和感受，而不再只是用简单的一个“美”字笼统概括。"
                    ,R.drawable.book1,"古瓷之光","作者：涂睿明");
            mList.add(china);
            China china1 = new China("\t\t\t\t\t\t\t\t瓷器是中国的创造，是世界的语言。瓷器是水、火、土的完美结合，是人类想像力和创造力的最好体现，是自然与人文交汇的结晶。瓷器凝结了我们祖先的智慧，满足了社会生活的需要，积聚了时代与民族的精华，成为中国乃至世界科技、工艺、文化史上的一项伟大发明，成为外国语汇里中国的代名词。\n\n\t\t\t\t\t\t\t\t《瓷器中国》一书用权威、通俗的语言，配以大量高清图片，讲述了中国瓷器三千年的发展历程，展现了中华民族博大精深的精神世界和审美情怀。",R.drawable.book2,"瓷器中国","作者：陈克伦");
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