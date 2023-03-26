package com.android.china.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.china.adpter.KepuHistoryAdapter;
import com.android.china.model.ChinaHistory;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.FragmentChinaKindBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChinaKindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChinaKindFragment extends Fragment {
    private FragmentChinaKindBinding binding;
    private KepuHistoryAdapter kepuHistoryAdapter;
    private List<ChinaHistory> list;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public ChinaKindFragment() {
        // Required empty public constructor
    }
    public static ChinaKindFragment newInstance(String param1, String param2) {
        ChinaKindFragment fragment = new ChinaKindFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChinaKindBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initKindData();
        initRecycerView();
    }
    public void initKindData(){
        list = new ArrayList<>();
        for(int i  = 0 ; i<10 ;i++){
            //https://baijiahao.baidu.com/s?id=1739575714925367101&wfr=spider&for=pc 如果后期需要再来这里找
            list.add(new ChinaHistory("青瓷","青瓷，是我国古代最早的一种青色或者灰绿色瓷器。青瓷一般都是厚釉瓷，造型典雅大气，纹饰多用刻花或者浮雕技艺。我国古陶瓷历史上最为知名的青瓷是龙泉青瓷。",R.drawable.kind1));
            list.add(new ChinaHistory("白瓷","白瓷，是区别于青瓷的一个瓷器品种。白瓷中的精品有唐代邢窑白瓷、宋代定窑白瓷、明代甜白釉瓷，以及德化窑白瓷等。\n" +
                    "\n" +
                    "白瓷以釉色取胜，无装饰花纹的白釉瓷器看起来更加素雅洁净。",R.drawable.kind2));
            list.add(new ChinaHistory("黑瓷","黑瓷，是在青釉瓷器的基础上所发展起来的一种瓷器。最早的黑瓷制品出现在东汉时期，以德清窑的作品成就最为突出。",R.drawable.kind3));
            list.add(new ChinaHistory("缥瓷","缥瓷，是我国两晋时期浙江温州地区所烧造的青瓷。缥瓷胎体十分细腻，釉色多为淡青色，有透明感，是瓦窑的得意之作。",R.drawable.kind4));
            list.add(new ChinaHistory("龙泉青瓷","龙泉青瓷，代表着青瓷的最高水准。龙泉青瓷的薄胎厚釉瓷，举世瞩目。粉青和梅子青这两种独特的釉色青翠晶莹，滋润柔和，堪称为龙泉青瓷中的上乘佳品。",R.drawable.kind5));
        }
    }
    public void initRecycerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.kepuHistoryRecyclerView.setLayoutManager(layoutManager);
        KepuHistoryAdapter adapter = new KepuHistoryAdapter(list);
        binding.kepuHistoryRecyclerView.setAdapter(adapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}