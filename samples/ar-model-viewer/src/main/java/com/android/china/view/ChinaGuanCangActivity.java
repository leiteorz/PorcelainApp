package com.android.china.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
        initBinding();
        initToolbar();
        createDatabase();
        initMmkv();
        initGuanCangListFromDb();
        initRecyclerView();
    }

    private void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }

    /**
     * 创建数据库
     */
    private void createDatabase(){
        db = AppDataBase.getInstance(this);
        dao = db.guanCangDao();
        myCollectionDao = db.myCollectionDao();
    }

    public void initToolbar(){
        setSupportActionBar(binding.guanCangToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.guanCangToolbar.setTitleMargin(26,26,26,26);
        //设置toolbar背景 以及整个布局的背景
        binding.guanCangToolbar.setBackgroundColor(Color.rgb(131,175,155));
        binding.guanCangLayout.setBackgroundColor(Color.rgb(131,175,155));
        binding.guanCangToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChinaGuanCangActivity.this,FirstPageActivity.class);
                startActivity(intent);
                finish();
            }
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
    public void initBinding(){
        binding = ActivityChinaGuanCangBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    /**
     * ToolBar相关
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_guancang,menu);

        /**
         * 搜索
         */
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("请输入要查询的瓷器名称");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("")){
                    mList = dao.queryAllGuanCangs();
                    adapter.refreshData(mList);
                }else {
                    mList = dao.queryGuanCangsByName(query);
                    adapter.refreshData(mList);
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")){
                    mList = dao.queryAllGuanCangs();
                    adapter.refreshData(mList);
                }else {
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
        switch (itemId){
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
    public void initChinaList(){
        GuanCang guanCang1 = new GuanCang("越窑青釉水丞","隋越窑系青釉水丞是隋朝时期制作的工艺品，高10.5厘米，口径17厘米，束口，圆鼓腹，平底",R.drawable.guancang_1);
        mList.add(guanCang1);
        GuanCang guanCang2 = new GuanCang("邢窑白釉鼓式钵","邢窑白瓷是我国最早的白釉瓷之一，与南方越窑青釉瓷齐名、享誉遐迩的邢窑白瓷在唐朝亦发展鼎盛",R.drawable.guancang_2);
        mList.add(guanCang2);
        GuanCang guanCang3 = new GuanCang("巩县窑三彩七星托盘","巩县窑在盛唐时期曾作为贡品，与邢窑白釉瓷媲美，而巩县窑的唐三彩无论在规模、品种、质量均盛极一时",R.drawable.guancang_3);
        mList.add(guanCang3);
        GuanCang guanCang4 = new GuanCang("龙泉窑梅子青釉弦纹三足炉","龙泉窑在宋代达到巅峰，南宋时品质最佳，以粉青、梅子青和黑胎厚釉青瓷最为有名，将青瓷推向高峰",R.drawable.guancang_4);
        mList.add(guanCang4);
        GuanCang guanCang5 = new GuanCang("耀州窑青釉盒","耀州窑在北宋达到鼎盛时期，耀州窑青瓷釉面光洁、匀净、色泽青幽；擅刻花、印花、划花等饰法",R.drawable.guancang_5);
        mList.add(guanCang5);
    }

    /**
     * 从数据库加载馆藏列表
     */
    public void initGuanCangListFromDb(){
        /**
         * 如果InsertGuanCang=0,则插入数据
         */
        int flag = kv.decodeInt("InsertGuanCangs",0);
        Log.d("leiteorz", "initGuanCangListFromDb: " + flag);
        if (flag == 0){
            List<GuanCang> list = new ArrayList<>();
            List<MyCollect> myCollectList = new ArrayList<>();

            GuanCang guanCang1 = new GuanCang("越窑青釉水丞",
                    "隋越窑系青釉水丞是隋朝时期制作的工艺品，高10.5厘米，口径17厘米，束口，圆鼓腹，平底",
                    R.drawable.guancang_1,
                    "\t\t\t\t隋越窑系青釉水丞是隋朝时期制作的工艺品，高10.5厘米，口径17厘米，束口，圆鼓腹，平底。\n\n\t\t\t\t隋越窑青釉水丞高10.5厘米，口径17厘米，底径5.2厘米。束口，圆鼓腹，平底。内外施青釉，内满釉外釉接近底部，釉面开有细小之纹片，釉色泛青。胎质坚至细腻，烧成温度高，整器造型稳重端庄而又不失秀丽，韵味十足。\n\n\t\t\t\t该种器型始见于南北朝，隋唐时广泛采用，成为通用器型，在传世文物中相同器型的见有陶瓷器、青铜器和金银器等。及至清代，因其器型及其秀美，历朝官窑均沿用此造型生产文房用具，并波及民窑瓷作以及玉作。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang1.png"
            );
            list.add(guanCang1);
            myCollectList.add(new MyCollect(0,1,0,"越窑青釉水丞","隋越窑系青釉水丞是隋朝时期制作的工艺品，高10.5厘米，口径17厘米，束口，圆鼓腹，平底",R.drawable.guancang_1));

            GuanCang guanCang2 = new GuanCang("邢窑白釉鼓式钵",
                    "邢窑白瓷是我国最早的白釉瓷之一，与南方越窑青釉瓷齐名、享誉遐迩的邢窑白瓷在唐朝亦发展鼎盛",
                    R.drawable.guancang_2,
                    "\t\t\t\t邢窑是中国白瓷的发祥地。中国古代最早的官窑之一。内丘在唐代属于邢州，故史称邢窑。河北内丘邢窑遗址被评为 2012年度全国十大考古新发现。邢窑古代窑址位于今邢台市内丘县与临城县一带。邢窑是中国最早的白瓷窑址，邢白瓷的发明与制作，打破了自商代以来，青瓷一统天下的局面，形成南方以浙江慈溪越窑为代表的青瓷和北方以河北内丘邢窑为代表的白瓷并驾齐驱、平分秋色的格局，形成了 “南青北白”的格局。\n\n\t\t\t\t邢窑创烧于北朝晚期，经过隋朝的飞速发展，到唐朝已达到鼎盛阶段，衰落于唐末五代时期，成为我国早期生产白瓷的中心。\n\n\t\t\t\t邢州（今邢台）瓷窑是唐代七大名窑之一，因产在邢州而得名，是华夏白瓷的鼻祖，在中国陶瓷史上占有非常重要的地位，被称为陶瓷史上一座辉煌的里程碑。\n\n\t\t\t\t邢窑的所在内丘被当称为瓷都，内丘现有如今位于河北省内丘县的中国邢窑博物馆、邢瓷文化体验馆、邢窑遗址博物馆。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang2.png"
            );
            list.add(guanCang2);
            myCollectList.add(new MyCollect(1,1,0,"邢窑白釉鼓式钵","邢窑白瓷是我国最早的白釉瓷之一，与南方越窑青釉瓷齐名、享誉遐迩的邢窑白瓷在唐朝亦发展鼎盛",R.drawable.guancang_2));

            GuanCang guanCang3 = new GuanCang("巩县窑三彩七星托盘",
                    "巩县窑在盛唐时期曾作为贡品，与邢窑白釉瓷媲美，而巩县窑的唐三彩无论在规模、品种、质量均盛极一时",
                    R.drawable.guancang_3,
                    "\t\t\t\t巩县窑，唐代重要瓷窑，在今河南巩县。1957年发现。隋已生产青瓷平底碗及高足盘等，器外壁施釉不到底，唐代生产白瓷、黑瓷、绞胎、唐三彩及黄、绿、蓝等单色釉陶器。白瓷中有一部分作为贡品，供民间所用之茶器为其大宗产品。巩县窑是已发现的烧造唐三彩的最主要窑址。\n\n\t\t\t\t在今河南省巩县，故名巩县窑。该窑始烧于隋，盛于唐，到五代初衰退。隋代烧青瓷，唐代烧白瓷，还烧三彩陶器、黑瓷、纹胎和茶叶未釉等。产品有碗、注壶、盘口瓶、盒 、钵、杯、豆、枕、人物动物雕塑以及玩具等。\n\n\t\t\t\t该窑产品特征是：白瓷胎色灰白，釉色白腻，质量较好；黑瓷胎体厚重，胎白釉黑，修胎精细，制作规整。三彩陶器胎呈灰白色，釉色有黄、绿、红、兰、白、褐等多种，也有单色釉器及纹胎装饰釉器。工艺特征是器物多为平底、玉壁底、浅圈足园饼状突足等。三彩器是先烧素坏，再挂彩烧成。器物装烧垫具有三岔支具，长方形垫具和园形托珠，器物内或足底一般留有几个垫具痕。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang3.png"
            );
            list.add(guanCang3);
            myCollectList.add(new MyCollect(2,1,0,"邢窑白釉鼓式钵","巩县窑在盛唐时期曾作为贡品，与邢窑白釉瓷媲美，而巩县窑的唐三彩无论在规模、品种、质量均盛极一时",R.drawable.guancang_3));

            GuanCang guanCang4 = new GuanCang("龙泉窑梅子青釉弦纹三足炉",
                    "龙泉窑在宋代达到巅峰，南宋时品质最佳，以粉青、梅子青和黑胎厚釉青瓷最为有名，将青瓷推向高峰",
                    R.drawable.guancang_4,
                    "\t\t\t\t龙泉窑因在今浙江龙泉县，故名，属我国南方青瓷系统。创造于北宋早期，南宋中晚期进入鼎盛时期，至明代中叶以后渐趋衰落，传世的龙泉青瓷下限至清康熙年间，烧造历史达七、八百年之久。北宋时期的龙泉青瓷，胎骨较厚，胎土淡灰，底足露胎处见赭褐色窑红，胎微出烧，釉的玻化程度好，釉层透明，釉表光泽很强。装饰花纹较简练，常见纹样有鱼纹、蕉叶、金枝、荷花等。装饰风格趋于奔放。处于南宋鼎盛时期的龙泉青瓷，形成了自已独有的艺术风格，显示了独特的魅力。\n\n\t\t\t\t南宋龙泉青瓷的造型亦形成自已的风格，稳重大方，浑厚淳朴而又不失秀媚，器型丰富多样，装饰普遍采用刻花和堆塑法，颇具艺术匠心。\n\n\t\t\t\t元代龙泉青瓷烧造量大，风格与南宋迥异：器型高大、胎体厚重；胎色为白中带灰或淡黄；釉色为粉青带黄绿，光泽较强，釉层半透明；装饰手法多种多样，有刻、划、印、贴、塑等，以划花为主，花纹粗略，线条奔放，纹饰以云龙、飞凰、双鱼、八仙、八卦、牡丹、荷叶等为多见。此外，还大量出现汉文和八思巴文字款铭。\n\n\t\t\t\t明代龙泉青瓷走向衰弱，器物胎体厚重，制作粗糙，胎色为灰黄，釉层厚，透明度高，釉表光泽强，釉色有青灰、茶叶末、灰黄等几种，装饰以釉下刻花为主，亦有模印人物故事的装饰方法。\n\n\t\t\t\t宋代龙泉青瓷是青瓷工艺的历史高峰。其青瓷的釉色与质地之美，亦如巧夺天工的人造美玉，全世人为之倾倒。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang4.jpg"
            );
            list.add(guanCang4);
            myCollectList.add(new MyCollect(3,1,0,"龙泉窑梅子青釉弦纹三足炉","龙泉窑在宋代达到巅峰，南宋时品质最佳，以粉青、梅子青和黑胎厚釉青瓷最为有名，将青瓷推向高峰",R.drawable.guancang_4));

            GuanCang guanCang5 = new GuanCang("耀州窑青釉盒",
                    "耀州窑在北宋达到鼎盛时期，耀州窑青瓷釉面光洁、匀净、色泽青幽；擅刻花、印花、划花等饰法",
                    R.drawable.guancang_5,
                    "\t\t\t\t耀州窑是北方青瓷的代表。唐代开始烧制黑釉、白釉、青釉、茶叶末釉和白釉绿彩、褐彩、黑彩以及三彩陶器等。宋、金以青瓷为主。北宋是耀州窑的鼎盛时期，据记载且为朝廷烧造“贡瓷”。金代延续北宋时期继续发展，元代开始转型，走向末落，经明代、清代，终于民国。\n\n\t\t\t\t耀州窑青瓷的主要特点：纹饰刻的非常清晰，带有北方人的性格特点，史籍上记载又叫刀刀见泥。\n\n\t\t\t\t耀州窑的传统工艺主要体现在原料的采配、成分及加工，泥料的储备及练揉，手工拉坯及修坯，手工雕花、刻花、划花、贴花、印花，釉药的选配、制备及敷施，匣钵、窑具的制作及装窑，火焰气氛及烧成等七个方面。一件制品完成要经过采料、精选、风化、配比、粑泥、陈腐、熟泥、揉泥、手拉坯、修坯、釉料精选、配制、施釉、手工装饰（雕、刻、贴、印）、窑具制作、装窑、烧窑等17道工序。各工序都有相应的技术要求，掌握相关技艺的人被称为“匠人”。",
                    "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang5.png"
            );
            list.add(guanCang5);
            myCollectList.add(new MyCollect(4,1,0,"耀州窑青釉盒","耀州窑在北宋达到鼎盛时期，耀州窑青瓷釉面光洁、匀净、色泽青幽；擅刻花、印花、划花等饰法",R.drawable.guancang_5));
            /**
             * 将list插入数据库中
             */
            dao.insertGuanCangs(list);
            myCollectionDao.insertMyCollections(myCollectList);
            //加载了馆藏列表,则将InsertGuanCangs设置为1
            kv.encode("InsertGuanCangs",1);
        }
        /**
         * 从数据库中查询所有馆藏item为mList赋值
         */
         mList = dao.queryAllGuanCangs();
    }

    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.guanCangRecyclerView.setLayoutManager(layoutManager);
        adapter = new GuanCangAdapter(mList);
        binding.guanCangRecyclerView.setAdapter(adapter);
    }
}