package com.android.china.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.china.adpter.PorcelainShapeAdapter;
import com.android.china.model.PorcelainShape;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.FragmentDiyBinding;

import java.util.ArrayList;
import java.util.List;


public class DiyFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentDiyBinding binding;
    private String mParam1;
    private List<PorcelainShape> list;
    private String mParam2;

    public DiyFragment() {
        // Required empty public constructor
    }

    public static DiyFragment newInstance(String param1, String param2) {
        DiyFragment fragment = new DiyFragment();
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
        binding = FragmentDiyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
        initRecyclerView();
    }
    public void initRecyclerView(){
        StaggeredGridLayoutManager layoutManager =new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        binding.diyRecyclerview.setLayoutManager(layoutManager);
        PorcelainShapeAdapter adapter = new PorcelainShapeAdapter(list);
        binding.diyRecyclerview.setAdapter(adapter);
    }
    public void initData(){
        list = new ArrayList<>();
        PorcelainShape porcelainShape = new PorcelainShape(R.drawable.cixin_1,"凤尾瓶");
        PorcelainShape porcelainShape1 = new PorcelainShape(R.drawable.cixin_2,"柳叶瓶");
        PorcelainShape porcelainShape2 = new PorcelainShape(R.drawable.cixin_3,"油锤瓶");
        PorcelainShape porcelainShape3 = new PorcelainShape(R.drawable.cixin_4,"天球瓶");
        PorcelainShape porcelainShape4 = new PorcelainShape(R.drawable.cixin_5,"玉壶春瓶");
        PorcelainShape porcelainShape5 = new PorcelainShape(R.drawable.cixin_6,"花浇瓶");
        list.add(porcelainShape);
        list.add(porcelainShape1);
        list.add(porcelainShape5);
        list.add(porcelainShape3);
        list.add(porcelainShape4);
        list.add(porcelainShape2);
    }
}