package com.android.china.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.china.viewModel.NavigationStatusModel;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.BottomNavigationBarBinding;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * 底部导航栏
 */
public class BottomNavigation extends Fragment {
    //binding
    BottomNavigationBarBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_navigation_bar,container,false);

        //binding
        binding = BottomNavigationBarBinding.inflate(inflater,container,false);
        view = binding.getRoot();

        /**
         * 点击跳转至首页
         */
        binding.naviFindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!NavigationStatusModel.status.getValue().equals(0)){
                    binding.naviFindBtn.setRippleColor(ColorStateList.valueOf
                            (ContextCompat.getColor(getContext(),R.color.green_wei)));
                    //跳转
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), FirstPageActivity.class);
                    startActivity(intent);
                }
            }
        });

        /**
         * 点击跳转至我的
         */
        binding.naviMyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!NavigationStatusModel.status.getValue().equals(2)){
                    //跳转
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MyPageActivity.class);
                    startActivity(intent);
                }
            }
        });

        /**
         * 点击跳转陶瓷创作
         */
        binding.naviCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!NavigationStatusModel.status.getValue().equals(1)){
                    //跳转
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SecondPageActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
