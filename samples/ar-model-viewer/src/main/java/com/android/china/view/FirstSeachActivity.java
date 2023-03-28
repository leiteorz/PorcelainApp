package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;

import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityFirstSeachBinding;

public class FirstSeachActivity extends AppCompatActivity {
    private ActivityFirstSeachBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initToolbar();
    }
    public void initBinding(){
        binding = ActivityFirstSeachBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void initToolbar() {
        setSupportActionBar(binding.searchActivityToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.searchActivityToolbar.setTitleMargin(26, 26, 26, 26);
        //设置toolbar背景
        binding.searchActivityToolbar.setBackgroundColor(Color.rgb(131, 175, 155));
        binding.searchActivityToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(FirstSeachActivity.this, FirstPageActivity.class);
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
}