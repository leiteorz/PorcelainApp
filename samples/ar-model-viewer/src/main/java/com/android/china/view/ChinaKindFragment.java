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
import com.android.china.model.MyCollect;
import com.android.china.room.AppDataBase;
import com.android.china.room.dao.ChinaHistoryDao;
import com.android.china.room.dao.MyCollectionDao;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.FragmentChinaKindBinding;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChinaKindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChinaKindFragment extends Fragment {
    private FragmentChinaKindBinding binding;
    private List<ChinaHistory> list;
    private AppDataBase db;
    private ChinaHistoryDao dao;
    private MMKV kv;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    List<MyCollect> myCollectList = new ArrayList<>();
    private MyCollectionDao myCollectionDao;

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
        initMmkv();
        initKindData();
        initRecycerView();
        initDatabase();
    }
    public void initDatabase(){
        db = AppDataBase.getInstance(getContext());
        dao = db.ChinaHistoryDao();
        myCollectionDao = db.myCollectionDao();

        String kind = kv.decodeString("kind");
        if(TextUtils.isEmpty(kind)){
            dao.insertChinaHistory(list);
            myCollectionDao.insertMyCollections(myCollectList);
        }
        kv.encode("kind","1");
    }
    public void initMmkv(){
        String rootDir = MMKV.initialize(getContext());
        kv = MMKV.defaultMMKV();
    }
    public void initKindData(){
        list = new ArrayList<>();
        for(int i  = 0 ; i<1 ;i++){
            //https://baijiahao.baidu.com/s?id=1739575714925367101&wfr=spider&for=pc 如果后期需要再来这里找
            list.add(new ChinaHistory("电瓷","醴陵是全国高电压等级电瓷生产基地之一。电瓷是醴陵陶瓷产业集群中的重要门类，涌现了一批如华鑫、阳东、浦口、华高等龙头电瓷生产企业。" +
                    "\n\t\t\t\t醴陵生产的特高压、超高压瓷绝缘子行销全球，服务全球电力基础设施建设，年出口量连续多年位居全球第一。" +
                    "\n\t\t\t\t2017年11月，醴陵成功创建国家级出口电瓷质量安全示范区。醴陵高规格建设了电力电瓷电器产业园，设有国家级电瓷电器检测检验中心、原辅料配送中心、标准厂房等，一批电瓷企业相继进驻。",R.drawable.kind_1,"https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/0EBE031592470828E67B24E2421A8C2E.jpg"));
            myCollectList.add(new MyCollect(6,0,0,"电瓷","醴陵是全国高电压等级电瓷生产基地之一。电瓷是醴陵陶瓷产业集群中的重要门类，涌现了一批如华鑫、阳东、浦口、华高等龙头电瓷生产企业。" +
                    "\n\t\t\t\t醴陵生产的特高压、超高压瓷绝缘子行销全球，服务全球电力基础设施建设，年出口量连续多年位居全球第一。" +
                    "\n\t\t\t\t2017年11月，醴陵成功创建国家级出口电瓷质量安全示范区。醴陵高规格建设了电力电瓷电器产业园，设有国家级电瓷电器检测检验中心、原辅料配送中心、标准厂房等，一批电瓷企业相继进驻。",R.drawable.kind_1));

            list.add(new ChinaHistory("艺术瓷","醴陵原创的釉下五彩是陶瓷产业和文化的瑰宝，釉下五彩技艺是国家非物质文化遗产。自创烧成功100多年来，凭借“白如玉、明如镜、薄如纸、声如磬“的特质，一直备受好评" +
                    "\n\t\t\t\t1915年釉下五彩”扁豆双禽瓶“获巴拿马万国展览会一等金奖，被誉为“东方陶瓷艺术的巅峰”。现在，醴陵五彩瓷珍品也作为国礼赠送给各国政要，成为中国国际交往的纽带。" +
                    "\n\t\t\t\t醴陵涌现了红官窑、醴陵窑艺、振美、尚方窑等优秀艺术瓷企业以及一批艺术陶瓷工作室。目前醴陵拥有180多位国家级和省级工艺美术大师、陶瓷艺术大师及一大批工程人员",R.drawable.kind_2,"https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/BAC5511A6ACD26EFEF62B0F4D4A4AE20.jpg"));
            myCollectList.add(new MyCollect(7,0,0,"艺术瓷","醴陵原创的釉下五彩是陶瓷产业和文化的瑰宝，釉下五彩技艺是国家非物质文化遗产。自创烧成功100多年来，凭借“白如玉、明如镜、薄如纸、声如磬“的特质，一直备受好评" +
                    "\n\t\t\t\t1915年釉下五彩”扁豆双禽瓶“获巴拿马万国展览会一等金奖，被誉为“东方陶瓷艺术的巅峰”。现在，醴陵五彩瓷珍品也作为国礼赠送给各国政要，成为中国国际交往的纽带。" +
                    "\n\t\t\t\t醴陵涌现了红官窑、醴陵窑艺、振美、尚方窑等优秀艺术瓷企业以及一批艺术陶瓷工作室。目前醴陵拥有180多位国家级和省级工艺美术大师、陶瓷艺术大师及一大批工程人员",R.drawable.kind_2));

            list.add(new ChinaHistory("特种瓷","陶瓷新材料作为湖南省重点培育的20大优势新兴产业链之一，已经成为醴陵陶瓷产业集群的新增长极。醴陵先进陶瓷材料产业经过十多年的努力，形成了以高温耐磨陶瓷材料、新型环保陶瓷材料、生物医疗陶瓷材料、电子通讯陶瓷材料为主的陶瓷新材料产业。" +
                    "\n\t\t\t\t目前，醴陵培育了一批在各自行业内有影响力的企业，如科一环保、银和瓷业等在环保陶瓷膜领域发展迅速；勇博陶瓷、仁龙特瓷、友立特瓷等是耐火材料、蓄热材料行业中的佼佼者；华联特瓷、杰瑞精密在电子通讯材料行业内的地位显著提升。",R.drawable.kind_3,"https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/127BF99700BFAFEF3D3AF6C4C6B0DAC8.jpg"));
            myCollectList.add(new MyCollect(8,0,0,"特种瓷","陶瓷新材料作为湖南省重点培育的20大优势新兴产业链之一，已经成为醴陵陶瓷产业集群的新增长极。醴陵先进陶瓷材料产业经过十多年的努力，形成了以高温耐磨陶瓷材料、新型环保陶瓷材料、生物医疗陶瓷材料、电子通讯陶瓷材料为主的陶瓷新材料产业。" +
                    "\n\t\t\t\t目前，醴陵培育了一批在各自行业内有影响力的企业，如科一环保、银和瓷业等在环保陶瓷膜领域发展迅速；勇博陶瓷、仁龙特瓷、友立特瓷等是耐火材料、蓄热材料行业中的佼佼者；华联特瓷、杰瑞精密在电子通讯材料行业内的地位显著提升。",R.drawable.kind_3));

            list.add(new ChinaHistory("日用瓷","日用瓷是醴陵陶瓷产业集群最大的门类，包括炻瓷、新骨瓷、陶瓷酒瓶、釉下五彩瓷等多个种类。其中炻瓷是醴陵的优势陶瓷品种，有餐具、茶具、文具、酒具等近2000多个样式。" +
                    "\n\t\t\t\t醴陵涌现了一批如华联、陶润、仙凤、新世纪、泰鑫、银和等全国知名的日用瓷生产企业。醴陵日用瓷年产量近100亿件，远销欧美、日韩、中东、东南亚以及南美等150多个国家和地区，成为宜家、沃尔玛、星巴克等全球著名品牌的优质产品，广受世界人民喜爱。2017年，成功创建国家级出口日用瓷质量安全示范区。",R.drawable.kind_4,"https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/5137706C630607AB7BF2A5219674E454.jpg"));
            myCollectList.add(new MyCollect(9,0,0,"日用瓷","日用瓷是醴陵陶瓷产业集群最大的门类，包括炻瓷、新骨瓷、陶瓷酒瓶、釉下五彩瓷等多个种类。其中炻瓷是醴陵的优势陶瓷品种，有餐具、茶具、文具、酒具等近2000多个样式。" +
                    "\n\t\t\t\t醴陵涌现了一批如华联、陶润、仙凤、新世纪、泰鑫、银和等全国知名的日用瓷生产企业。醴陵日用瓷年产量近100亿件，远销欧美、日韩、中东、东南亚以及南美等150多个国家和地区，成为宜家、沃尔玛、星巴克等全球著名品牌的优质产品，广受世界人民喜爱。2017年，成功创建国家级出口日用瓷质量安全示范区。",R.drawable.kind_4));
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