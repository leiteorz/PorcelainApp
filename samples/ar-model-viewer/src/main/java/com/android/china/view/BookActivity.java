package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.china.model.China;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityBookBinding;

public class BookActivity extends AppCompatActivity {
    private ActivityBookBinding binding;
    private TextView bookTextView;
    private China china;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initToolbar();
        initIntent();
    }
    public void initIntent(){
        Intent intent = getIntent();
        china = (China) intent.getSerializableExtra("china");
        binding.bookText.setText(china.getDescribe());
        binding.bookImageView.setImageResource(china.getImageId());
        binding.bookName.setText(china.getBookName());
        binding.bookAuthor.setText(china.getBookAuthor());
    }
    public void initBinding(){
        binding = ActivityBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void initToolbar(){
        setSupportActionBar(binding.BookToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.BookToolbar.setTitleMargin(26,26,26,26);
        binding.bookLinearLayout.setBackgroundColor(Color.rgb(131,175,155));
        //设置toolbar背景 以及整个布局的背景
        binding.BookToolbar.setBackgroundColor(Color.rgb(131,175,155));
        binding.BookToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(BookActivity.this,FirstPageActivity.class);
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
}