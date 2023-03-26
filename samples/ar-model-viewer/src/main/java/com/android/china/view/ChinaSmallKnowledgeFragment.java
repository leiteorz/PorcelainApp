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
import com.google.ar.sceneform.samples.gltf.databinding.FragmentChinaSmallKnowledgeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChinaSmallKnowledgeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChinaSmallKnowledgeFragment extends Fragment {
    private FragmentChinaSmallKnowledgeBinding binding;
    private List<ChinaHistory> list;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ChinaSmallKnowledgeFragment() {
    }


    public static ChinaSmallKnowledgeFragment newInstance(String param1, String param2) {
        ChinaSmallKnowledgeFragment fragment = new ChinaSmallKnowledgeFragment();
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
        binding = FragmentChinaSmallKnowledgeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initRecycerView();
    }
    public void initData(){
        list = new ArrayList<>();
        for(int i = 0; i<5;i++){
            list.add(new ChinaHistory("陶瓷是陶器和瓷器的总称","陶瓷是以粘土以及各种天然矿物经过粉碎混炼、成型和煅烧制得的材料为主要原料的各种制品。陶器的缺点----就是它吸水。用来盛水、盛粥，很快就把水吸干了。陶器上有很多细孔，容易吸水。瓷器是从陶器发展演变而成的，中国的四大发明：造纸、火药、指南针、活字印刷，还有一个更重要、使用更广泛的发明------就是瓷器，英文的“中国”都叫 China，意为瓷器，西方文明对中国的认同，是从陶瓷开始的。\n" +
                    "哪什么是瓷器呢？必须具备以下几条才能称之为瓷器：\n" +
                    "1、胎料必须是瓷土的。瓷土的成分主要是高岭土，并含有长石、石英石和莫来石成分；含铁量低。经过高温烧成之后，胎色白。\n" +
                    "\n" +
                    "2、必须经过1200℃～1300℃的高温焙烧才具备瓷器的物理性能。瓷土不同，烧成温度也有差异，要以烧结为准。\n" +
                    "\n" +
                    "3、瓷器表面所施的釉，必须是在高温之下和瓷器一道烧成的玻璃质釉。\n" +
                    "\n" +
                    "4、吸水率。我们都知道陶器吸水，瓷器胎体吸水率不足0.5%，几乎不吸水。\n" +
                    "\n" +
                    "5、透光率。陶器无论什么条件下，它都不会透过光线。瓷器要求在一定的条件下，能够透光，透光度越高，瓷器质地越好。",R.drawable.small1));
            list.add(new ChinaHistory("陶瓷三要素","土：骨瓷土、低骨土、强化土、中温土、低温土、滚压土、注浆土、高压土（泥浆的浓度不一样）、色土（例如大理石）。\n" +
                    "\n" +
                    "水（釉水）：透明釉、色釉、窑变釉等。\n" +
                    "\n" +
                    "火（烧窑）：烧成温度，烧成曲线的控制，放在窑车窑板上的哪个位置，颜色本身的敏感程度等。",R.drawable.small2));
            list.add(new ChinaHistory("如何鉴别瓷器的好坏","购买瓷器有个“四字诀”，即“看”、“听”、“比”、“试”。　　“看”就是要将瓷器上下内外细细观察一遍。一看瓷器釉面是否光洁润滑，有无擦伤、小孔、黑点和气泡；二看形状是否规整，有无变形；三看画面有无损缺；四看底部是否平整，须放置平稳，无毛刺。“听”就是听轻轻弹叩瓷器时发出的声音。如声音清脆、悦耳，则说明瓷胎细致密实，无裂损，在高温烧成时，瓷化完全。如声音喑哑，就可断定瓷胎有裂损，或者瓷化不完全，这类瓷器经冷热变化，易开裂。\n" +
                    "\n" +
                    "比”就是比较。对配套瓷器，要比较各配件，看其造型及画面装饰是否协调一致。尤其是成套的产品，因为颜色会随烧成条件不同而发生变化，所以同是一个颜色，颜色有深有浅，一套几件乃至数十件的成套瓷器，如各件呈色有明显差异，这套瓷器就大为逊色了。客户通常重视的是色釉或花纸的“色差”问题。#陶瓷#“试”就是试盖、试装、试验。有的瓷器带盖子，有的瓷器由几个元件组合而成，在挑选瓷器时，别忘了将盖子试盖一下，将元件试组装一下，看看是否合适。另外，有的瓷器具有特殊功能，如茶壶盖，盖不会轻易掉下来；如杯盖，盖子是否与杯口吻合；如密封罐的密封程度。所以要试验一下，看其功能是否正常。", R.drawable.small3));
            list.add(new ChinaHistory("日用陶瓷的保养与清洁","1、要轻拿轻放，手拿瓷器的大盘、大碗时一定要双手捧握，忌用单手拿盘、碗的一边，以防滑落。\n" +
                    "\n" +
                    "2、勿将热杯直接浸入冷水中，以免温度迅速改变损伤瓷质。（不要骤冷骤热）\n" +
                    "\n" +
                    "3、用清水洗净时，水温不超过80℃。\n" +
                    "\n" +
                    "4、建议用手清洗，不要用洗碗机。若真不愿意用手洗，则要选择有瓷器及水晶类洗涤功能的洗碗机。\n" +
                    "\n" +
                    "5、带有黄金和铂金边装饰，勿放入微波炉加热，\n" +
                    "\n" +
                    "否则腐蚀金边。\n" +
                    "\n" +
                    "6、洗剂PH值必须在11-11.5之间，因为陶瓷怕碱。\n" +
                    "\n" +
                    "7、如有刮花，可以用牙膏略打磨。\n" +
                    "\n" +
                    "8、如果有茶渍，可以用柠檬汁或食醋清洗。",R.drawable.small4));
            list.add(new ChinaHistory("陶瓷之都","江西景德镇、四川邛崃、湖南醴陵、江苏宜兴、福建德化。",R.drawable.small5));
        }
    }
}