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
import com.google.ar.sceneform.samples.gltf.databinding.FragmentChinaCraftsManBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChinaCraftsManFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChinaCraftsManFragment extends Fragment {
    private FragmentChinaCraftsManBinding binding;
    private List<ChinaHistory> list;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ChinaCraftsManFragment() {
    }


    public static ChinaCraftsManFragment newInstance(String param1, String param2) {
        ChinaCraftsManFragment fragment = new ChinaCraftsManFragment();
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
        binding = FragmentChinaCraftsManBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initRecycerView();
    }
    public void initRecycerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.kepuHistoryRecyclerView.setLayoutManager(layoutManager);
        KepuHistoryAdapter adapter = new KepuHistoryAdapter(list);
        binding.kepuHistoryRecyclerView.setAdapter(adapter);
    }
    public void initData(){
        list = new ArrayList<>();
        for(int i = 0;i<1;i++){
//            https://m.maigoo.com/top/420617.html 资料来源 如果后面需要可以来这
            list.add(new ChinaHistory("陈贻谟","中国工艺美术大师，中国陶瓷艺术大师，荣获“中国工艺美术终身成就奖”，享受国务院政府特殊津贴。其浮雕作品“龙盘”，被中国工艺美术珍宝馆收藏，定为国家一级工艺美术珍品;黑陶作品\"衡\"\"世纪碑\"和“贾思锶”，分别被中国国家博物馆和中国美术馆收藏;高石英瓷“中华龙\"国宴用瓷，被誉为当代国瓷。陈贻谟大师共创作了400多件套优秀陶瓷艺术作品，有108件获全国、省级奖，多件作品被选为国家礼品，同时在多个国家和地区展览。",R.drawable.craftman1));
            list.add(new ChinaHistory("王锡良","中国工艺美术大师，中国陶瓷美术大师，享受国务院政府特殊津贴，为中国陶瓷美术馆名誉馆长、昌南书画院名誉馆长。其瓷板画《收租院》市场估价在五干万到一亿之间;《革命摇篮井冈山》大型壁画，则悬挂在北京人民大会堂江西厅。在50多年的陶瓷艺术作生涯中，王锡良以山水、人物画最为擅长。他的作品大处作眼有气势，小处观之有意味，因善画中国画，刻意在陶瓷作品中揉进中国画讲求神韵、计白当黑的形式美感。【详细】",R.drawable.craftman2));
            list.add(new ChinaHistory("朱文立","国家级非物质文化遗产项目汝瓷烧制技艺代表性传承人，享受国务院政府特殊津贴。他填补了我国“汝窑”\"这项空白，使断代数百年的汝官瓷再现于世，并找到宋代真正的汝官窑遗址和宋代汝官窑汝州新容器，解决陶瓷史上两大悬案;后被评为中国陶瓷艺术大师，高级工程师，河南省优秀专家，河南省劳动模范，中国民间文化(汝瓷）杰出传承人，汝州市汝瓷博物馆副馆长。【详细】",R.drawable.craftman3));
            list.add(new ChinaHistory("冯乃藻","中国工艺美术大师，为淄博陶瓷作出过突出贡献。其作品创作题材广泛，充满了时代气息，不拘于形，注重内在神韵。其代表作《高温花釉浮雕九龙壁》长达17.4米，在我国陶瓷史上具有历史意义。同时，他研制的“云霞釉\"开创了淄博陶瓷窑变花釉之先河，又成功研制了名贵的兔毫釉、虎皮斑釉、鹧鸪斑釉等。他的作品先后被中国历史博物馆和中国工艺美术馆珍藏。1993年被评为中国工艺美术大师，享受国务院政府特殊津贴。【详细】",R.drawable.craftman4));
            list.add(new ChinaHistory("张明文","中国工艺美术大师，淄博刻瓷艺术创始奠基者。1993年，国务院授予“为中国文化艺术事业做出突出贡献\"政府特殊津贴奖。1997年授予“全国优秀工艺美术专业技术人员”奖章。其佳作蜚声国内外，曾享有“中国刻瓷第一人”的美誉。他对中国刻瓷的发掘、推广、传承做出了极其重要贡献，作品“鲁青瓷刻瓷尊”已被编入九年义务教育全日制学校教科书。其个人受到中央电视台《流行无限》栏目专题报道，后入选“中国陶瓷艺术大师排名榜”。【详细】",R.drawable.craftman5));

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}