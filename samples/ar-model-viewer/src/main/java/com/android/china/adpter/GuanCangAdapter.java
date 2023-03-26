package com.android.china.adpter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.model.GuanCang;
import com.android.china.utils.MyApplication;
import com.android.china.view.ChinaGcItemActivity;
import com.android.china.view.ChinaGuanCangActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.ar.sceneform.samples.gltf.R;
import com.tencent.mmkv.MMKV;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuanCangAdapter extends RecyclerView.Adapter<GuanCangAdapter.ViewHolder>{
    private List<GuanCang> mList;
    private static MMKV kv;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView guanCangImage;
        TextView guanCangName;
        TextView guanCangDescription;
        MaterialCardView guanCangItem;

        public ViewHolder(View view){
            super(view);

            String rootDir = MMKV.initialize(view.getContext());
            kv = MMKV.defaultMMKV();

            guanCangImage = view.findViewById(R.id.guan_cang_image);
            guanCangName = view.findViewById(R.id.guan_cang_name);
            guanCangDescription = view.findViewById(R.id.guan_cang_description);
            guanCangItem = view.findViewById(R.id.guan_cang_item);
        }
    }

    public GuanCangAdapter(List<GuanCang> guanCangList){
        mList = guanCangList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_guan_cang_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.guanCangItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAbsoluteAdapterPosition();
                //越窑青釉水丞
                if (position == 0){
                    String name = "越窑青釉水丞";
                    String content = "\t\t\t\t隋越窑系青釉水丞是隋朝时期制作的工艺品，高10.5厘米，口径17厘米，束口，圆鼓腹，平底。\n\n\t\t\t\t隋越窑青釉水丞高10.5厘米，口径17厘米，底径5.2厘米。束口，圆鼓腹，平底。内外施青釉，内满釉外釉接近底部，釉面开有细小之纹片，釉色泛青。胎质坚至细腻，烧成温度高，整器造型稳重端庄而又不失秀丽，韵味十足。\n\n\t\t\t\t该种器型始见于南北朝，隋唐时广泛采用，成为通用器型，在传世文物中相同器型的见有陶瓷器、青铜器和金银器等。及至清代，因其器型及其秀美，历朝官窑均沿用此造型生产文房用具，并波及民窑瓷作以及玉作。";
                    String url = "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang1.png";
                    kv.encode("GuanCangName",name);
                    kv.encode("GuanCangContent",content);
                    kv.encode("GuanCangUrl",url);
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(),ChinaGcItemActivity.class);
                    view.getContext().startActivity(intent);
                }
                //邢窑白釉鼓式钵
                if (position == 1){
                    String name = "邢窑白釉鼓式钵";
                    String content = "\t\t\t\t邢窑是中国白瓷的发祥地。中国古代最早的官窑之一。内丘在唐代属于邢州，故史称邢窑。河北内丘邢窑遗址被评为 2012年度全国十大考古新发现。邢窑古代窑址位于今邢台市内丘县与临城县一带。邢窑是中国最早的白瓷窑址，邢白瓷的发明与制作，打破了自商代以来，青瓷一统天下的局面，形成南方以浙江慈溪越窑为代表的青瓷和北方以河北内丘邢窑为代表的白瓷并驾齐驱、平分秋色的格局，形成了 “南青北白”的格局。\n\n\t\t\t\t邢窑创烧于北朝晚期，经过隋朝的飞速发展，到唐朝已达到鼎盛阶段，衰落于唐末五代时期，成为我国早期生产白瓷的中心。\n\n\t\t\t\t邢州（今邢台）瓷窑是唐代七大名窑之一，因产在邢州而得名，是华夏白瓷的鼻祖，在中国陶瓷史上占有非常重要的地位，被称为陶瓷史上一座辉煌的里程碑。\n\n\t\t\t\t邢窑的所在内丘被当称为瓷都，内丘现有如今位于河北省内丘县的中国邢窑博物馆、邢瓷文化体验馆、邢窑遗址博物馆。";
                    String url = "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang2.png";
                    kv.encode("GuanCangName",name);
                    kv.encode("GuanCangContent",content);
                    kv.encode("GuanCangUrl",url);
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(),ChinaGcItemActivity.class);
                    view.getContext().startActivity(intent);
                }
                //巩县窑三彩七星托盘
                if(position == 2){
                    String name = "巩县窑三彩七星托盘";
                    String content = "\t\t\t\t巩县窑，唐代重要瓷窑，在今河南巩县。1957年发现。隋已生产青瓷平底碗及高足盘等，器外壁施釉不到底，唐代生产白瓷、黑瓷、绞胎、唐三彩及黄、绿、蓝等单色釉陶器。白瓷中有一部分作为贡品，供民间所用之茶器为其大宗产品。巩县窑是已发现的烧造唐三彩的最主要窑址。\n\n\t\t\t\t在今河南省巩县，故名巩县窑。该窑始烧于隋，盛于唐，到五代初衰退。隋代烧青瓷，唐代烧白瓷，还烧三彩陶器、黑瓷、纹胎和茶叶未釉等。产品有碗、注壶、盘口瓶、盒 、钵、杯、豆、枕、人物动物雕塑以及玩具等。\n\n\t\t\t\t该窑产品特征是：白瓷胎色灰白，釉色白腻，质量较好；黑瓷胎体厚重，胎白釉黑，修胎精细，制作规整。三彩陶器胎呈灰白色，釉色有黄、绿、红、兰、白、褐等多种，也有单色釉器及纹胎装饰釉器。工艺特征是器物多为平底、玉壁底、浅圈足园饼状突足等。三彩器是先烧素坏，再挂彩烧成。器物装烧垫具有三岔支具，长方形垫具和园形托珠，器物内或足底一般留有几个垫具痕。";
                    String url = "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang3.png";
                    kv.encode("GuanCangName",name);
                    kv.encode("GuanCangContent",content);
                    kv.encode("GuanCangUrl",url);
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(),ChinaGcItemActivity.class);
                    view.getContext().startActivity(intent);
                }
                //龙泉窑梅子青釉弦纹三足炉
                if(position == 3){
                    String name = "梅子青釉弦纹三足炉";
                    String content = "\t\t\t\t龙泉窑因在今浙江龙泉县，故名，属我国南方青瓷系统。创造于北宋早期，南宋中晚期进入鼎盛时期，至明代中叶以后渐趋衰落，传世的龙泉青瓷下限至清康熙年间，烧造历史达七、八百年之久。北宋时期的龙泉青瓷，胎骨较厚，胎土淡灰，底足露胎处见赭褐色窑红，胎微出烧，釉的玻化程度好，釉层透明，釉表光泽很强。装饰花纹较简练，常见纹样有鱼纹、蕉叶、金枝、荷花等。装饰风格趋于奔放。处于南宋鼎盛时期的龙泉青瓷，形成了自已独有的艺术风格，显示了独特的魅力。\n\n\t\t\t\t南宋龙泉青瓷的造型亦形成自已的风格，稳重大方，浑厚淳朴而又不失秀媚，器型丰富多样，装饰普遍采用刻花和堆塑法，颇具艺术匠心。\n\n\t\t\t\t元代龙泉青瓷烧造量大，风格与南宋迥异：器型高大、胎体厚重；胎色为白中带灰或淡黄；釉色为粉青带黄绿，光泽较强，釉层半透明；装饰手法多种多样，有刻、划、印、贴、塑等，以划花为主，花纹粗略，线条奔放，纹饰以云龙、飞凰、双鱼、八仙、八卦、牡丹、荷叶等为多见。此外，还大量出现汉文和八思巴文字款铭。\n\n\t\t\t\t明代龙泉青瓷走向衰弱，器物胎体厚重，制作粗糙，胎色为灰黄，釉层厚，透明度高，釉表光泽强，釉色有青灰、茶叶末、灰黄等几种，装饰以釉下刻花为主，亦有模印人物故事的装饰方法。\n\n\t\t\t\t宋代龙泉青瓷是青瓷工艺的历史高峰。其青瓷的釉色与质地之美，亦如巧夺天工的人造美玉，全世人为之倾倒。";
                    String url = "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang4.jpg";
                    kv.encode("GuanCangName",name);
                    kv.encode("GuanCangContent",content);
                    kv.encode("GuanCangUrl",url);
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(),ChinaGcItemActivity.class);
                    view.getContext().startActivity(intent);
                }
                //耀州窑青釉盒
                if (position == 4){
                    String name = "耀州窑青釉盒";
                    String content = "\t\t\t\t耀州窑是北方青瓷的代表。唐代开始烧制黑釉、白釉、青釉、茶叶末釉和白釉绿彩、褐彩、黑彩以及三彩陶器等。宋、金以青瓷为主。北宋是耀州窑的鼎盛时期，据记载且为朝廷烧造“贡瓷”。金代延续北宋时期继续发展，元代开始转型，走向末落，经明代、清代，终于民国。\n\n\t\t\t\t耀州窑青瓷的主要特点：纹饰刻的非常清晰，带有北方人的性格特点，史籍上记载又叫刀刀见泥。\n\n\t\t\t\t耀州窑的传统工艺主要体现在原料的采配、成分及加工，泥料的储备及练揉，手工拉坯及修坯，手工雕花、刻花、划花、贴花、印花，釉药的选配、制备及敷施，匣钵、窑具的制作及装窑，火焰气氛及烧成等七个方面。一件制品完成要经过采料、精选、风化、配比、粑泥、陈腐、熟泥、揉泥、手拉坯、修坯、釉料精选、配制、施釉、手工装饰（雕、刻、贴、印）、窑具制作、装窑、烧窑等17道工序。各工序都有相应的技术要求，掌握相关技艺的人被称为“匠人”。";
                    String url = "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/guancang5.png";
                    kv.encode("GuanCangName",name);
                    kv.encode("GuanCangContent",content);
                    kv.encode("GuanCangUrl",url);
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(),ChinaGcItemActivity.class);
                    view.getContext().startActivity(intent);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GuanCang guanCang = mList.get(position);
        holder.guanCangName.setText(guanCang.getName());
        holder.guanCangDescription.setText(guanCang.getDescription());
        holder.guanCangImage.setImageResource(guanCang.getImageId());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
