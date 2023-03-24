package com.android.china.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.china.adpter.GuanCangAdapter;
import com.android.china.adpter.KepuHistoryAdapter;
import com.android.china.model.China;
import com.android.china.model.ChinaHistory;
import com.android.china.model.GuanCang;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.FragmentChinaHistoryBinding;

import java.util.ArrayList;
import java.util.List;


public class ChinaHistoryFragment extends Fragment {
    private FragmentChinaHistoryBinding binding;
    private KepuHistoryAdapter kepuHistoryAdapter;
    private List<ChinaHistory> list;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ChinaHistoryFragment() {

    }

    public static ChinaHistoryFragment newInstance(String param1, String param2) {
        ChinaHistoryFragment fragment = new ChinaHistoryFragment();
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
        binding = FragmentChinaHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initHistoryData();
        initRecycerView();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void initHistoryData(){
        list = new ArrayList<>();
        for(int i = 0 ;i<10;i++){
            list.add(new ChinaHistory("六朝古期",("六朝时期（公元220年──581年），迅速兴起的佛教艺术对陶瓷也产生了相应的影响，" +
                    "在此季作品造型上留有明显痕迹。公元581年隋朝夺取了权力，结束了长期的南北分裂局面，但它只统治到公元618年就被唐所取代。隋朝瓷器也开始兴起于市。"),
                    R.drawable.kepu1));
            list.add(new ChinaHistory("唐代","唐代（公元618年至公元970年）被认为是中国艺术史上的一个伟大时期。陶瓷工艺技术改进巨大，许多精细瓷器品种大量出现，即用当今的鉴测标准来衡量，它们也算得上是真正的优质瓷器。唐末大乱，英雄竟起，接踵而来的是一个朝代争夺局面，即五代，" +
                    "这种局面一直持续到公元960年。连年战乱中却出现了一个陶瓷新品种──柴窑瓷（萧窑），质地之优被广为传颂，但传世者极为罕见。",R.drawable.kepu2));
        }
    }
    public void initRecycerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.kepuHistoryRecyclerView.setLayoutManager(layoutManager);
        KepuHistoryAdapter adapter = new KepuHistoryAdapter(list);
        binding.kepuHistoryRecyclerView.setAdapter(adapter);
    }
}