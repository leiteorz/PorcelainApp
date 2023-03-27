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
import com.google.ar.sceneform.samples.gltf.databinding.FragmentChinaCraftBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChinaCraftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChinaCraftFragment extends Fragment {
    private FragmentChinaCraftBinding binding;
    private KepuHistoryAdapter kepuHistoryAdapter;
    private List<ChinaHistory> list;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ChinaCraftFragment() {
        // Required empty public constructor
    }


    public static ChinaCraftFragment newInstance(String param1, String param2) {
        ChinaCraftFragment fragment = new ChinaCraftFragment();
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
        binding = FragmentChinaCraftBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initRecycerView();
    }
    public void initData(){
        list = new ArrayList<>();
//        https://baijiahao.baidu.com/s?id=1696029285507739395&wfr=spider&for=pc 后续如果要添加内容去这里找
        for(int i = 0;i<1;i++){
            list.add(new ChinaHistory("采石制泥","黏土（瓷土）是制作瓷器的原料，而黏土是由地壳表层的岩石风化分解而成。工匠们需要先将矿石采回，然后再打碎制泥。\n" +
                    "\n" +
                    "简单解释一下“岩石风化”：岩石在太阳辐射、大气、水以及某些生物的作用下会发生破碎、疏松等现象，受气象（风、雨、炎热等）影响，岩石崩裂成大小不等的碎石，经过漫长岁月的洗礼，这些碎石变成了细小的土粒沉积下来，形成了土壤。\n" +
                    "\n" +
                    "土壤的成分含有岩石的元素，属于含水铝硅酸盐矿物，主要成分为硅和铝的氧化物，此外还有铁、钾、钠、镁、钙、钛的氧化物。这八种元素是构成瓷器的重要元素。其中铝和硅是瓷器的骨架材料，铁钛氧化物是主要呈色元素。由于各地地质、地貌以及气候条件的不同，故而不同地区的土壤成分也不同。" +
                    "古代越州地区的土壤因含有制瓷所必需的矿物成分（花岗岩风化物质），因此越州窑首先烧制出了中国最早的瓷器；而白瓷的出现除了工艺因素外，当地土壤中铁含量较低也是其中因素。因此说，造就唐代“南青北白”之格局的先提因素，是因为南北土壤成分的不同而致，正所谓一方水土养一方瓷。",R.drawable.craft1));
            list.add(new ChinaHistory("淘练泥土","打碎的矿石需要经过淘练去除其中的杂质再做成泥，才能达到制瓷工艺的要求。含有过多杂质的胎泥在烧制过程中会出现变形、破裂等现象。因此，练泥的好坏在很大程度上决定了瓷器的质量。\n" +
                    "\n" +
                    "最佳淘泥案例当属龙山文化中的“蛋壳黑陶杯”。正是因为其胎泥经过了反复淘洗、不含有任何杂质，才保证了它能够在拉坯过程中始终保持质地致密，以致壁薄至0.1毫米也不破裂的世所罕见的薄度。",R.drawable.craft2));
            list.add(new ChinaHistory("轮制拉坯","将制好的胎泥置于工作台的中心，通过转动工作台，再配合双手的动作进行修整器型的过程，被叫做“拉坯成型法”，也称“轮制法”。轮制法使得器壁厚薄均匀、造型规整，同时也提高了生产效率。",R.drawable.craft3));
            list.add(new ChinaHistory(" 修坯细琢","对拉坯成型的胎坯进行修整，使器表平整光滑。",R.drawable.craft4));
            list.add(new ChinaHistory("绘画纹饰","在素胎上绘画纹饰图案。唐代长沙窑首创出在胎坯上绘画褐彩以及绿彩的釉下彩绘瓷。在此之前，瓷器纹饰仅为点彩的褐斑彩瓷，长沙窑把褐斑扩展成了褐（绿）彩的图案纹饰，极大地提升了瓷器的美感与艺术性。",R.drawable.craft5));
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