package com.android.china.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Dao;
import androidx.room.Database;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;

import com.android.china.adpter.KepuHistoryAdapter;
import com.android.china.model.ChinaHistory;
import com.android.china.room.AppDataBase;
import com.android.china.room.dao.ChinaHistoryDao;
import com.android.china.utils.MyApplication;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivitySearchBinding;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ActivitySearchBinding binding;
    private List<ChinaHistory> list;
    private ChinaHistoryDao dao;
    private AppDataBase db;
    private KepuHistoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initToolbar();
        initDatabase();
        initRecycerView();
        initData();
    }
    public void initDatabase(){
        db = AppDataBase.getInstance(getApplicationContext());
        dao = db.ChinaHistoryDao();
    }
    public void initData(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("editView");
        list = dao.queryChinaHistorysByName(name);
        adapter.refreshData(list);
    }
    public void initRecycerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.searchRv.setLayoutManager(layoutManager);
        adapter = new KepuHistoryAdapter(list);
        binding.searchRv.setAdapter(adapter);
    }
    public void initBinding(){
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
    public void initToolbar(){
        setSupportActionBar(binding.searchToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.searchToolbar.setTitleMargin(26,26,26,26);
        //设置toolbar背景 以及整个布局的背景
        binding.searchLinearLayout.setBackgroundColor(Color.rgb(131,175,155));
        binding.searchToolbar.setBackgroundColor(Color.rgb(131,175,155));
        binding.searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SearchActivity.this,FirstPageActivity.class);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_guancang,menu);

        /**
         * 搜索
         */
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("请输入要查询的内容");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(TextUtils.isEmpty(query)){
                    list = dao.queryAllChinaHistory();
                    adapter.notifyDataSetChanged();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    list = dao.queryAllChinaHistory();
                    adapter.refreshData(list);
                }else{
                    list = dao.queryChinaHistorysByName(newText);
                    adapter.refreshData(list);
                }
                return true;
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
}