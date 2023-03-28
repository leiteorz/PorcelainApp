package com.android.china.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;

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

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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
        GuanCang guanCang1 = new GuanCang("越窑青釉水丞","隋越窑系青釉水丞是隋朝时期制作的工艺品，高10.5厘米，口径17厘米，束口，圆鼓腹，平底",R.drawable.guancang_1);
        mList.add(guanCang1);
        GuanCang guanCang2 = new GuanCang("邢窑白釉鼓式钵","邢窑白瓷是我国最早的白釉瓷之一，与南方越窑青釉瓷齐名、享誉遐迩的邢窑白瓷在唐朝亦发展鼎盛",R.drawable.guancang_2);
        mList.add(guanCang2);
        GuanCang guanCang3 = new GuanCang("巩县窑三彩七星托盘","巩县窑在盛唐时期曾作为贡品，与邢窑白釉瓷媲美，而巩县窑的唐三彩无论在规模、品种、质量均盛极一时",R.drawable.guancang_3);
        mList.add(guanCang3);
        GuanCang guanCang4 = new GuanCang("龙泉窑梅子青釉弦纹三足炉","龙泉窑在宋代达到巅峰，南宋时品质最佳，以粉青、梅子青和黑胎厚釉青瓷最为有名，将青瓷推向高峰",R.drawable.guancang_4);
        mList.add(guanCang4);
        GuanCang guanCang5 = new GuanCang("耀州窑青釉盒","耀州窑在北宋达到鼎盛时期，耀州窑青瓷釉面光洁、匀净、色泽青幽；擅刻花、印花、划花等饰法",R.drawable.guancang_5);
        mList.add(guanCang5);
    }
    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.guanCangRecyclerView.setLayoutManager(layoutManager);
        GuanCangAdapter adapter = new GuanCangAdapter(mList);
        binding.guanCangRecyclerView.setAdapter(adapter);
    }
}