package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.china.model.China;
import com.android.china.utils.MyStatusBarTransparency;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityBookBinding;

public class BookActivity extends AppCompatActivity {
    private ActivityBookBinding binding;
    private TextView bookTextView;
    private MyStatusBarTransparency myStatusBarTransparency;
    private China china;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initIntent();
        initStatus();
    }
    public void initStatus(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);
    }
    public void initIntent(){
        Intent intent = getIntent();
        china = (China) intent.getSerializableExtra("china");
        binding.bookDiscribe.setText(china.getDescribe());
        binding.bookBackground.setBackgroundResource(china.getImageId());
        binding.bookName.setText(china.getBookName());
        binding.bookAuthor.setText(china.getBookAuthor());
    }
    public void initBinding(){
        binding = ActivityBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}