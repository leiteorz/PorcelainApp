package com.android.china.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.china.adpter.KepuHistoryAdapter;
import com.android.china.model.ChinaHistory;
import com.android.china.room.AppDataBase;
import com.android.china.room.dao.ChinaHistoryDao;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.FragmentChinaHistoryBinding;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;


public class ChinaHistoryFragment extends Fragment {
    private FragmentChinaHistoryBinding binding;
    private List<ChinaHistory> list;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AppDataBase db;
    private ChinaHistoryDao dao;
    private MMKV kv;
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
        initMmkv();
        initHistoryData();
        initRecycerView();
        initDatabase();
    }
    public void initMmkv(){
        String rootDir = MMKV.initialize(getContext());
        kv = MMKV.defaultMMKV();
    }
    public void initDatabase(){
        String history = kv.decodeString("history");
        db = AppDataBase.getInstance(getContext());
        dao = db.ChinaHistoryDao();
        if(TextUtils.isEmpty(history)){
           dao.insertChinaHistory(list);
        }
        kv.encode("history","1");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void initHistoryData(){
        list = new ArrayList<>();
        for(int i = 0 ;i<1;i++){
            list.add(new ChinaHistory("东汉","东汉时期，醴陵瓷窑较为集中的分布在渌水流域。2022年7月，在左权镇永兴村的考古调查中，发现窑业堆积点17处，其中东汉窑业堆积就占10处。后对易家山一处东汉中晚期窑址进行抢救性考古发掘，这次发掘再次改写了醴陵烧造瓷器的历史，填补了湘东地区早期青瓷窑址的空白。该窑为长条形泥砖龙窑，出土器物多为硬陶质，部分施青釉。器表装饰方格纹，器形主要有罐、壶、釜、盘等。",R.drawable.history_1));
            list.add(new ChinaHistory("五代","五代时期醴陵瓷业发展为宋元时期瓷业繁荣奠定了基础。2018年在醴陵市孙家湾镇铁水河岸一带发现了五代时期的毛家岭窑址和凤形岭窑址，其中毛家岭窑址为依山而建的长条形龙窑，其烧制的瓷器多施青釉、青黄釉。器型以碗为主，其它器型还有执壶、罐、钵、盏、缸、坛、杯等。\n" +
                    "\n",R.drawable.history_2));
            list.add(new ChinaHistory("宋元","宋元时期是醴陵瓷业的兴盛时期，目前，已发现的宋元时期瓷窑以醴陵枫林镇、沩山镇最为集中，2010年在枫林镇发掘了唐家坳宋代窑址，2015年在沩山镇发掘了钟鼓塘窑址。这些宋元窑址主要烧造青白瓷为主，还有部分影青瓷和酱釉瓷等。器型有碗、盘、碟、壶、杯、水注等，器类繁多，清新素雅。",R.drawable.history_3));
            list.add(new ChinaHistory("明清","明清时期，醴陵主要烧制青花日用瓷为主。窑址广泛分布于醴陵市北乡一带，尤以沩山镇最为集中，2018年发掘了枫树坡窑址，该窑址还保留较完整的阶梯龙窑，出土器物以青瓷碗为主。",R.drawable.history_4));
            list.add(new ChinaHistory("新中国","新中国成立后，醴陵窑迎来了大发展机遇，形成了享誉中外的“醴陵窑”品牌。1956年，国务院批准成立醴陵瓷业总公司，恢复釉下五彩瓷生产。\n" +
                    "\n" +
                    "1958年，醴陵瓷业总公司艺术瓷厂成立，1964年更名为群力瓷厂。数十年来，群力瓷厂承担了为党和国家领导人、中央机关、国家重大节庆纪念、国家礼品等烧制瓷器的任务。1958年，群力瓷厂开始为毛泽东生产胜利杯等生活用具。1974年，群力瓷厂为毛泽东设计制作餐具、茶具等专用瓷，为当代醴陵瓷中的极品，被称为“毛瓷”。群力瓷厂也因此被称为“红色官窑”。\n" +
                    "\n" +
                    "醴陵瓷器的历史，也是技术革新带来的艺术发展的历史，这样的进步历程，持续几千年绵延至今，醴陵成为湖南仅有的历尽千年窑火不息的制瓷名城，在中国瓷器发展史上留下了浓墨重彩的一笔。",R.drawable.history_5));
        }
    }
    public void initRecycerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.kepuHistoryRecyclerView.setLayoutManager(layoutManager);
        KepuHistoryAdapter adapter = new KepuHistoryAdapter(list);
        binding.kepuHistoryRecyclerView.setAdapter(adapter);
    }
}