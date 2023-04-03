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
import com.google.ar.sceneform.samples.gltf.databinding.FragmentChinaCraftsManBinding;
import com.tencent.mmkv.MMKV;

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
    private MMKV kv;
    private AppDataBase db;
    private ChinaHistoryDao dao;
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
        initMmkv();
        initDatabase();
    }
    public void initDatabase(){
        db = AppDataBase.getInstance(getContext());
        dao = db.ChinaHistoryDao();
        String craftsman = kv.decodeString("craftsman");
        if(TextUtils.isEmpty(craftsman)){
            dao.insertChinaHistory(list);
            kv.encode("craftsman","1");
        }
    }
    public void initMmkv(){
        String rootDir = MMKV.initialize(getContext());
        kv = MMKV.defaultMMKV();
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
            list.add(new ChinaHistory("黄小玲","黄小玲，女，汉族，1968年9月12日出生，湖南醴陵人，享受国务院特殊津贴的中国陶瓷艺术大师，醴泉窑艺创办人。\n" +
                    "现任第十四届全国人大代表，醴陵市醴泉窑艺陶瓷有限公司总设计师（艺术总监）、高级工艺师。黄小玲长期以来对釉下五彩装饰工艺技法，不仅操作娴熟，而且能博采众长融会贯通，大胆突破，另立风貌。近年来，创作了大量的陶瓷艺术作品。其装饰风格清新秀丽、恬淡雅致，获得同道的好评。\n" +
                    "曾有《虞美人》瓶、《牡丹》瓶、《紫藤花》瓶等二十余件作品先后参加海内外的各种展览评比会，部分作品曾获各种奖励，还有《红杜鹃》等三十余件作品被海内外有关专家或博物馆收藏。",R.drawable.craftman1));
            list.add(new ChinaHistory("唐其意","唐其意：字奇乙，1959年生。湖南株洲市醴陵人，高级工艺美术师。1979年从事陶瓷工艺美术设计工作，1985年毕业于长沙理工大学美术系，一直从事设计绘画工作，工作至今已有四十多年。自幼受家学渊源熏陶，醉心书画，尤擅山水、人物、花鸟和陶瓷美术装饰。得岳父杨效基传授，叔父唐锡怀指点，师从著名画家李天玉、邹传安，博采众家之长加以融化，而又能脱变运用。以水墨或浅绛色作画使画意境如诗，作品苍润深厚、松秀幽淡。欣赏唐其意的瓷器作品如含精咀华，陶然喜形，沉醉于美妙的“天人和一”的意境中，同时也能感受到古人书画精华的传承：“熟不甜，生不涩、淡而厚，实而清”！作品深受广大爱好者喜爱。2018年经中国陶瓷艺术文化发展中心评审委员会评定，为“中国陶瓷艺术家”。",R.drawable.craftman2));
            list.add(new ChinaHistory("吴寿祺","1954年6月，醴陵艺术工作者联合会和工商联合会聘请为陶瓷业务美术训练班专职教师。次年9月，调入醴陵陶瓷研究所，晋升为陶瓷技术师。时已年近古稀，将所学专长毫无保留地传授给青年一代，先后培训釉下彩技艺人员300多名。在吴参与研究、试验下，陶瓷研究所于1955年10月烧制出已失传20多年的釉下彩瓷。随后同艺术陶（现群力瓷厂）配合，为人民大会堂、中国人民革命军事博物馆等单位承制餐具、茶具、烟具等釉下彩高档瓷。1961年送广州交易会展览的15个釉下彩产品。被与会者誉为“真、纯、美”佳品。所创制的万花瓶、六角回、古罗钱、铁线描折等技术别具特色，备受称赞。",R.drawable.craftman3));
            list.add(new ChinaHistory("杨钧","杨钧，男，1967年生，湖南醴陵人，国家高级工艺美术师，湖南省陶瓷艺术大师，湖南省工艺美术大师，中国名家书画研究会理事，醴陵市政协委员。现任醴陵希灵陶瓷制造有限公司艺术总监。\n" +
                    "他为人豪爽，兼具内向，又不失艺术大家的儒雅，这是他留给人的第一印象。\n" +
                    "\n" +
                    "1983年毕业于号称“陶瓷界的黄埔军校”的醴陵釉下五彩艺徒班，成为第一届毕业学生。1984年在群力瓷厂从事釉下五彩瓷设计工作，得到艺术大师李小年、张震先生亲授，主攻山水花鸟，兼攻人物。经过三十多年的沉淀和积累，绘画技法娴熟，锐意创新，其作品生动清新，笔墨流畅，构思巧妙，气势恢弘，极具山水画之气韵，作品深受业内及社会各界青睐。",R.drawable.craftman4));
            list.add(new ChinaHistory("林家湖","林家湖，醴陵人。代表作品是北京人民大会堂湖南厅直径1米的釉下五彩大挂盘。其代表作品还有为纪念邓小平诞辰百周年设计的玉兰花杯和釉下五彩蝴蝶花胜利杯，后者被选为新中国成立10周年国庆主席台专用茶杯。",R.drawable.craftman5));

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}