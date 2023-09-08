package com.android.china.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;

import com.android.china.adpter.GuanCangAdapter;
import com.android.china.model.GuanCang;
import com.android.china.model.MyCollect;
import com.android.china.room.AppDataBase;
import com.android.china.room.dao.GuanCangDao;
import com.android.china.room.dao.MyCollectionDao;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityChinaGuanCangBinding;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ChinaGuanCangActivity extends AppCompatActivity {
    private ActivityChinaGuanCangBinding binding;
    private List<GuanCang> mList = new ArrayList<>();
    GuanCangAdapter adapter;
    private MMKV kv;
    AppDataBase db;
    GuanCangDao dao;
    MyCollectionDao myCollectionDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTransition();
        initBinding();
        initToolbar();
        createDatabase();
        initMmkv();
        initGuanCangListFromDb();
        initRecyclerView();
    }

    public void initTransition() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Fade());

    }

    private void initMmkv() {
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }

    /**
     * 创建数据库
     */
    private void createDatabase() {
        db = AppDataBase.getInstance(this);
        dao = db.guanCangDao();
        myCollectionDao = db.myCollectionDao();
    }

    public void initToolbar() {
        setSupportActionBar(binding.guanCangToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.guanCangToolbar.setTitleMargin(26, 26, 26, 26);
        //设置toolbar背景 以及整个布局的背景
        binding.guanCangToolbar.setBackgroundColor(Color.rgb(131, 175, 155));
        binding.guanCangLayout.setBackgroundColor(Color.rgb(131, 175, 155));
        binding.guanCangToolbar.setNavigationOnClickListener(view -> {
            Intent intent = new Intent(ChinaGuanCangActivity.this, FirstPageActivity.class);
            startActivity(intent);
            finish();
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void initBinding() {
        binding = ActivityChinaGuanCangBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    /**
     * ToolBar相关
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_guancang, menu);

        /**
         * 搜索
         */
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("请输入要查询的瓷器名称");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("")) {
                    mList = dao.queryAllGuanCangs();
                    adapter.refreshData(mList);
                } else {
                    mList = dao.queryGuanCangsByName(query);
                    adapter.refreshData(mList);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    mList = dao.queryAllGuanCangs();
                    adapter.refreshData(mList);
                } else {
                    mList = dao.queryGuanCangsByName(newText);
                    adapter.refreshData(mList);
                }
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.search:

                break;
            case R.id.comment:
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 该插入方法仅作测试,检测RecyclerView的显示效果
     * 已经弃用
     */
    public void initChinaList() {
        GuanCang guanCang1 = new GuanCang("越窑青釉水丞", "隋越窑系青釉水丞是隋朝时期制作的工艺品，高10.5厘米，口径17厘米，束口，圆鼓腹，平底", R.drawable.guancang_1);
        mList.add(guanCang1);
        GuanCang guanCang2 = new GuanCang("邢窑白釉鼓式钵", "邢窑白瓷是我国最早的白釉瓷之一，与南方越窑青釉瓷齐名、享誉遐迩的邢窑白瓷在唐朝亦发展鼎盛", R.drawable.guancang_2);
        mList.add(guanCang2);
        GuanCang guanCang3 = new GuanCang("巩县窑三彩七星托盘", "巩县窑在盛唐时期曾作为贡品，与邢窑白釉瓷媲美，而巩县窑的唐三彩无论在规模、品种、质量均盛极一时", R.drawable.guancang_3);
        mList.add(guanCang3);
        GuanCang guanCang4 = new GuanCang("龙泉窑梅子青釉弦纹三足炉", "龙泉窑在宋代达到巅峰，南宋时品质最佳，以粉青、梅子青和黑胎厚釉青瓷最为有名，将青瓷推向高峰", R.drawable.guancang_4);
        mList.add(guanCang4);
        GuanCang guanCang5 = new GuanCang("耀州窑青釉盒", "耀州窑在北宋达到鼎盛时期，耀州窑青瓷釉面光洁、匀净、色泽青幽；擅刻花、印花、划花等饰法", R.drawable.guancang_5);
        mList.add(guanCang5);
    }

    /**
     * 从数据库加载馆藏列表
     */
    public void initGuanCangListFromDb() {
        /**
         * 如果InsertGuanCang=0,则插入数据
         */
        int flag = kv.decodeInt("InsertGuanCangs", 0);
        Log.d("leiteorz", "initGuanCangListFromDb: " + flag);
        if (flag == 0) {
            List<GuanCang> list = new ArrayList<>();
            List<MyCollect> myCollectList = new ArrayList<>();

            GuanCang guanCang1 = new GuanCang("釉下五彩镂空葡萄纹瓷瓶",
                    "现收藏于湖南博物馆",
                    R.drawable.guancang_1,
                    "\t\t\t\t清宣统二年（公元1910年）制作，高41.5厘米，口径11厘米，底径12.8厘米。为陈设用瓷，喇叭口，细束颈，溜肩，弧腹下敛，圈足。是醴陵窑用彩及镂空透雕装饰的代表器。\n\n\t\t\t\t此瓶颈部及肩部绘田园山水，山水环绕中有小小的村落，村民或插秧，或聊天，旁有小狗等候主人回家，整个图景充满着祥和、宁静的气氛。腹部镂空为枝蔓缠绕的葡萄纹，藤蔓曲折蜿蜒，葡萄晶莹剔透，叶片青翠欲滴，叶脉清晰，毛虫爬行其上，青蛙腾跃其间。通过镂空处可见内瓶花纹。此瓷瓶乃传统套瓶式样。套瓶是以在镂空瓶内套装一小瓶而得名，于明代龙泉窑首创，后来清乾隆年间继续发展，制作出能转动的内瓶即所谓的转心瓶。此件宣统时期的套瓶，从腹部花叶间镂空处可见内瓶，设计结构上也极为生动有趣，可谓匠心独运，兼具审美与实用价值。\n\n\t\t\t\t瓷瓶底双圈内青花书“大清宣统二年湖南瓷业公司”三行楷书款。1905年至1930年之前，醴陵釉下五彩瓷器款识很多，它们都是由指定人员所写。光绪末年款识有“湘造”、“湖南制造”，还有“湖南瓷业公司”、“湖南瓷业学堂制”、“湖南瓷校”、“瓷业公司造”，同时还有编年款“戊申湖南瓷业公司造”，也有不打款识的。早期作品，瓷坯偏厚，量重，款识多为楷书、行书。宣统期间有“大清宣统元年湖南瓷业公司”、“大清宣统二年湖南瓷业公司”、“大清宣统三年湖南瓷业公司”，民国初期有“洪宪元年”款。此外还有学校及画师的姓名款如：“大清宣统二年湖南瓷业公司罗正五制”等。又有作坊款“湖南模范窑业工场造”、“台田”“台田讲习所造”、“长沙台田”、“长沙台田瓷业讲习所谨制”。还有横写瓶口款识等等。款识有双圈和无双圈两种，多为楷书，也有隶书的。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/1.png"
            );
            list.add(guanCang1);
            myCollectList.add(new MyCollect(0, 1, 0, "釉下五彩镂空葡萄纹瓷瓶", "现收藏于湖南博物馆", R.drawable.guancang_1));

            GuanCang guanCang2 = new GuanCang("釉下五彩花卉瓷瓶",
                    "现藏于湖南博物馆",
                    R.drawable.guancang_2,
                    "\t\t\t\t清宣统（公元1909—1911年），高49.2厘米，口径15.2厘米，底径16.7厘米。陈设用瓷，撇口，束颈，垂鼓腹，圈足。通体白地釉下五彩装饰，腹部绘五彩花卉一束，有菊花、腊梅、山茶花、绣球花等，底双圈内青花书“大清宣统二年湖南瓷业公司”三行楷书款。器物造型秀美，画面素雅，花卉多姿娇娆，寓意富贵吉祥。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/2.png"
            );
            list.add(guanCang2);
            myCollectList.add(new MyCollect(1, 1, 0, "釉下五彩花卉瓷瓶", "现藏于湖南博物馆", R.drawable.guancang_2));

            GuanCang guanCang3 = new GuanCang("釉下五彩葫芦形瓷瓶",
                    "现藏于湖南博物馆",
                    R.drawable.guancang_3,
                    "\t\t\t\t清宣统（公元1909—1911年），高32.2厘米，口径5.3厘米，底径9.9厘米。陈设用瓷，喇叭口，束颈，腹作葫芦形，圈足。\n\n\t\t\t\t腹部饰釉下五彩菊花一束，描绘时先以墨线勾勒出花的轮廓，再以色彩涂染，叶正面用深绿，阴面及叶茎用草绿色，花瓣用紫、浅绛色，花蕊以绿色点成，焙烧后墨线消失，有如“无骨画”， 底双圈内青花书“大清宣统二年湖南瓷业公司”三行楷书款。器物造型别致，纹饰色彩妍丽，画工精湛。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/3.png"
            );
            list.add(guanCang3);
            myCollectList.add(new MyCollect(2, 1, 0, "釉下五彩葫芦形瓷瓶", "现藏于湖南博物馆", R.drawable.guancang_3));

            GuanCang guanCang4 = new GuanCang("釉下彩凤尾瓷瓶",
                    "现藏于湖南博物馆",
                    R.drawable.guancang_4,
                    "\t\t\t\t釉下彩凤尾瓷瓶为清宣统年间的瓷器，高55.8 口径27.5厘米，1953年，湖南省文管会征集而来。现收藏于湖南省博物馆。\n\n\t\t\t\t陈设用器。器形高大，长颈鼓腹。右上墨书：“浭阳尚书钧鉴 属吏晏元枢监制”。底书“大清宣统三年湖南瓷业公司”青花款。可见这是一件礼品瓷。据考证，“浭阳尚书”指时任鄂汉、川汉铁路督办端方，这件醴陵瓷就是其部下所送。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/4.png"
            );
            list.add(guanCang4);
            myCollectList.add(new MyCollect(3, 1, 0, "釉下彩凤尾瓷瓶", "现藏于湖南博物馆", R.drawable.guancang_4));

            GuanCang guanCang5 = new GuanCang("山水人物纹瓷瓶",
                    "现藏于长沙市博物馆",
                    R.drawable.guancang_5,
                    "\t\t\t\t清醴陵窑釉下五彩山水人物纹瓷瓶是清代文物。\n\n\t\t\t\t此展品为清代文物。高47.4厘米，口径12厘米，足径13厘米。瓶口微喇，折肩，长圆腹，圈足，足外侧凸起一周，器表作釉下五彩装饰，口外侧有一周由卷叶纹组成的花带，颈部一侧为折枝芙蓉花纹，另一侧为折枝芙蓉花和菊花纹，折肩部由青花绘有花朵纹和如意纹，组成二圈花纹带，腹部彩绘纹饰。现收藏于长沙市博物馆。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/5.png"
            );
            list.add(guanCang5);
            myCollectList.add(new MyCollect(4, 1, 0, "山水人物纹瓷瓶", "现藏于长沙市博物馆", R.drawable.guancang_5));
            /**
             * 将list插入数据库中
             */
            dao.insertGuanCangs(list);
            myCollectionDao.insertMyCollections(myCollectList);
            //加载了馆藏列表,则将InsertGuanCangs设置为1
            kv.encode("InsertGuanCangs", 1);
        }
        /**
         * 从数据库中查询所有馆藏item为mList赋值
         */
        mList = dao.queryAllGuanCangs();
    }

    public void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.guanCangRecyclerView.setLayoutManager(layoutManager);
        adapter = new GuanCangAdapter(mList);
        binding.guanCangRecyclerView.setAdapter(adapter);
    }
}