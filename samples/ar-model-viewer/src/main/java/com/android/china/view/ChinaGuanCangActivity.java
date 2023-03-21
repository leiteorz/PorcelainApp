package com.android.china.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.china.adpter.ChinaAdapter;
import com.android.china.adpter.GuanCangAdapter;
import com.android.china.model.China;
import com.android.china.model.GuanCang;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityChinaGuanCangBinding;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityMyPageBinding;

import java.util.ArrayList;
import java.util.List;

public class ChinaGuanCangActivity extends AppCompatActivity {
    private ActivityChinaGuanCangBinding binding;
    private List<GuanCang> mList = new ArrayList<>();

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
        //设置toolbar背景 以及整个布局的背景
        binding.guanCangToolbar.setBackgroundColor(Color.rgb(131,175,155));
        binding.guanCangLayout.setBackgroundColor(Color.rgb(131,175,155));
        binding.guanCangToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChinaGuanCangActivity.this,FirstPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
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

    /**
     * ToolBar相关
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_guancang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.search:
                break;
            case R.id.comment:
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }

    public void initChinaList(){
        for(int i = 0 ; i< 10 ;i++){
            GuanCang guanCang = new GuanCang("青花瓷","青花瓷,又称白地青花瓷,常简称青花,是中国瓷器的主流品种之一,属釉下彩瓷。",R.drawable.qing_hua_ci);
            mList.add(guanCang);
        }
    }
    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.guanCangRecyclerView.setLayoutManager(layoutManager);
        GuanCangAdapter adapter = new GuanCangAdapter(mList);
        binding.guanCangRecyclerView.setAdapter(adapter);
    }
}