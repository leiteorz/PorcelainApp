package com.android.china.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.android.china.adpter.PorcelainStoryAdapter;
import com.android.china.model.PorcelainStory;
import com.android.china.utils.MyStatusBarTransparency;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityPorcelainStoryBinding;

import java.util.ArrayList;
import java.util.List;

public class PorcelainStoryActivity extends AppCompatActivity {
    private ActivityPorcelainStoryBinding binding;
    private MyStatusBarTransparency myStatusBarTransparency;
    private List<PorcelainStory> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        initStatus();
        initToolBar();

        initPorcelainStoryList();
        initRecyclerView();
    }

    private void initBinding(){
        binding = ActivityPorcelainStoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void initToolBar(){
        binding.toolbarPorcelainStory.setTitle("陶瓷故事");
        setSupportActionBar(binding.toolbarPorcelainStory);

    }

    private void initStatus(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);
    }

    private void initPorcelainStoryList(){
        for (int i=0;i<10;i++){
            PorcelainStory porcelainStory = new PorcelainStory("魂兮归来","堆塑罐是长江中下游地区的一种有堆贴塑内容的青瓷随葬品，其典型特征为在主体罐的上部堆附四只小罐或小罐变体，在颈腹间堆贴塑庭院式门楼。",R.drawable.story_first);
            mList.add(porcelainStory);
        }
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.porcelainStoryRecyclerView.setLayoutManager(layoutManager);
        PorcelainStoryAdapter adapter = new PorcelainStoryAdapter(mList);
        binding.porcelainStoryRecyclerView.setAdapter(adapter);
    }
}