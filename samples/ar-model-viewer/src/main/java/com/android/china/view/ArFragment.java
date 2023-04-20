package com.android.china.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.china.adpter.ArPorcelainAdapter;
import com.android.china.model.ArPorcelain;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.FragmentArBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArFragment extends Fragment {

    private FragmentArBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<ArPorcelain> arPorcelainList = new ArrayList<>();
    private ArPorcelainAdapter arPorcelainAdapter;
    private String mParam1;
    private String mParam2;

    public ArFragment() {
        // Required empty public constructor
    }


    public static ArFragment newInstance(String param1, String param2) {
        ArFragment fragment = new ArFragment();
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
        binding = FragmentArBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initArPorcelainRecyclerView();
        initArPorcelains();
    }
    public void initArPorcelainRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.arRecyclerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        arPorcelainAdapter = new ArPorcelainAdapter(arPorcelainList);
        binding.arRecyclerview.setAdapter(arPorcelainAdapter);
    }
    private void initArPorcelains(){
        arPorcelainList.add(new ArPorcelain("青花瓷_1", R.drawable.qing_hua_ci));
        arPorcelainList.add(new ArPorcelain("青花瓷_2",R.drawable.model_2));
        arPorcelainList.add(new ArPorcelain("白瓷",R.drawable.model_4));
        arPorcelainList.add(new ArPorcelain("青花瓷_3",R.drawable.model_3));
    }
}