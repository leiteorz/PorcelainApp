package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.china.adpter.ChinaAdapter;
import com.android.china.model.China;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityChinaGuanCangBinding;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityMyPageBinding;

import java.util.ArrayList;
import java.util.List;

public class ChinaGuanCangActivity extends AppCompatActivity {
    private ActivityChinaGuanCangBinding binding;
    private List<China> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initToolbar();
        initChinaList();
        initRecyclerView();
    }
    public void initToolbar(){
        setSupportActionBar(binding.guanCangToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.guanCangToolbar.setTitleMargin(26,26,26,26);
        binding.guanCangToolbar.setBackgroundColor(Color.rgb(131,175,155));
        binding.guanCangLayout.setBackgroundColor(Color.rgb(131,175,155));
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
    public void initBinding(){
        binding = ActivityChinaGuanCangBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_guancang,menu);
        return true;
    }

    public void initChinaList(){
        for(int i = 0 ; i< 10 ;i++){
            China china = new China("陶瓷"+"i"+"号",R.drawable.pic);
            mList.add(china);
        }
    }
    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.guanCangRecyclerView.setLayoutManager(layoutManager);
        ChinaAdapter chinaAdapter = new ChinaAdapter(mList);
        binding.guanCangRecyclerView.setAdapter(chinaAdapter);

    }
}