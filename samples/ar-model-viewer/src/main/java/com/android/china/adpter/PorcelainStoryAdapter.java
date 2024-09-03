package com.android.china.adpter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.china.model.PorcelainStory;
import com.android.china.view.PorcelainStoryDetailPageActivity;
import com.google.android.material.button.MaterialButton;
import com.google.ar.sceneform.samples.gltf.R;
import com.tencent.mmkv.MMKV;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PorcelainStoryAdapter extends RecyclerView.Adapter<PorcelainStoryAdapter.ViewHolder> {
    private List<PorcelainStory> mList;

    private static MMKV kv;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView porcelainStoryImage;
        TextView porcelainStoryName;
        TextView porcelainStoryDescription;
        MaterialButton porcelainStoryBtn;

        public ViewHolder(View view){
            super(view);

            String rootDir = MMKV.initialize(view.getContext());
            kv = MMKV.defaultMMKV();

            porcelainStoryImage = view.findViewById(R.id.porcelain_story_image);
            porcelainStoryName = view.findViewById(R.id.porcelain_story_name);
            porcelainStoryDescription = view.findViewById(R.id.porcelain_story_description);
            porcelainStoryBtn = view.findViewById(R.id.porcelain_story_btn);
        }
    }

    public PorcelainStoryAdapter(List<PorcelainStory> list){
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_porcelain_story_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        /**
         * 点击事件
         */
        viewHolder.porcelainStoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAbsoluteAdapterPosition();
                //"魂兮归来"
                if (position == 0){
                    String name = "魂兮归来";
                    String url = "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/DuiSuGuan.png";
                    String content = "\t\t\t\t堆塑罐是长江中下游地区的一种有堆贴塑内容的青瓷随葬品，其典型特征为在主体罐的上部堆附四只小罐或小罐变体，在颈腹间堆贴塑庭院式门楼。历来的研究者常称之为谷仓罐、魂瓶等，不一而足。\n\n\t\t\t\t一般认为堆塑罐起源于东汉中晚期的五联罐。到了吴晋，五联罐的造型开始发生变化，以罐为主体，罐口上堆塑飞鸟瑞兽、门阙楼阁、仙佛人像，其中部分器物上还有龟趺阴刻碑铭，记载制造年月、产地及吉祥文字。\n\n\t\t\t\t西晋至东晋堆塑罐逐渐消失，目前已发现最晚的一件堆塑罐出土于永昌元年东晋墓。战乱、招魂葬的禁止、越窑的衰落、佛教的被冲击，都是堆塑罐消失的因素。\n\n\t\t\t\t在众多讨论堆塑罐作用的文章中，明器是最具可能性的。以碑文内容而言，罗振玉所藏一器碑文为 “会稽出始寍（宁）用此丧葬，宜子孙作吏高，迁众无极”，更是明白地指出堆塑罐是丧葬用器，也反映了死者亲属乞求家族富贵兴。有意思的是，还有学者将堆塑罐上的佛像与其功能相联系，推测堆塑罐具有“安魂”作用。\n\n\t\t\t\t《全上古三代秦汉三国六朝文》中有记，311年洛阳沦陷之后，在众多企图流亡长江下游寻求避难的北方上层阶级中，许多人不能成行，还有许多人死于南迁之前或死于途中，尸骨未得安葬。这些未得安葬的游魂时常搅扰着死者的家人，后者不得已面对痛苦的抉择：葬而无尸，有悖儒家礼法；不葬，又会招致不孝、不义、不仁之名。对于吴越地区的许多新居民来说，当地的一种古老的丧葬习俗听起来必定显得更合理。\n\n\t\t\t\t这些文献不仅对堆塑罐的功能研究提供了当时的社会文化背景，同时也记载了堆塑罐在葬仪中的用途。而堆塑罐的很可能就是被称为“灵座”或“魂堂”的器物。在葬礼行三日之后，与椅子、供桌和饮食物品一起放置于墓中。堆塑罐作为死者灵魂的居所在下述话语中得到清楚的证明：“魂堂、几筵，设于寝，岂唯饭尸，亦以宁神也”“若乃缸魂于棺”。\n\n\t\t\t\t一些堆塑罐腹部的孔洞，被认为是象征灵魂归来的入口，有的堆塑罐上还塑有蛇出没于两孔之间，将魂瓶的下腹部分与死人之魄的去处——传统中的黄泉概念联系在一起。与此形成鲜明的对比，魂瓶顶部华丽的楼阁以及环塑于楼阁周围的各种神祇、使乐人物、门卫等等，造成了一个早期中国人所能想象得到的、拥有各种象征安全与奢华图像符号的乐土。这是灵魂的天堂，是生命所能转化的最大极限，魂兮回来，是这类器物永恒的主题。";
                    kv.encode("PorcelainStoryContent",content);
                    kv.encode("PorcelainStoryName",name);
                    kv.encode("PorcelainStoryUrl",url);
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), PorcelainStoryDetailPageActivity.class);
                    view.getContext().startActivity(intent);
                }
                //"幽蓝神采"
                if (position == 1){
                    String name = "幽兰神采";
                    String url = "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/USSJ34SUXHT~BQE[L~}NC_T.jpg";
                    String content = "\t\t\t\t景德镇生产的元青花至今已有近700年历史，系统认识和研究元青花的历史却仅有70年。\n\n\t\t\t\t尽管唐中期青花已经出现，但宋元并无任何关于青花烧造的记载。\n\n\t\t\t\t元代瓷器带款的极为少见，除青白釉盘碗中有印着“枢府”或“太禧”款的以外，一般青花、或是釉里红器物均无正规年款，这一时期世面上有少量元青花因为没有铭文和底款，被简单地误认为明代青花。\n\n\t\t\t\t1929年霍布逊对英国大维德基金会所藏瓷器进行研究，首次介绍带有“至正十一年”题记的青花云龙纹象耳瓶；1949年美国学者以这件青花象耳瓶为标准器，首次分离出一批元青花瓷器，并将这类元青花瓷器归为“戴维德瓶风格”，这对青花云龙纹象耳瓶，除去其本身所具备的艺术价值，其所附铭文尤其实是“至正十一年四月”七字极具史料价值；1952年，波普教授在整理托普卡普皇宫和阿德比尔神庙收藏的中国瓷器时，借鉴前述学者的研究成果，经过系统的排比和分析，再度分离出更多的元青花瓷器，并提出了“十四世纪青花类型”的概念。\n\n\t\t\t\t20世纪80年代左右，波普的研究被介绍到国内，加之国内大量考古报告的发表，掀起学界对元青花的研究浪潮。\n\n\t\t\t\t至正型元青花的发现，对学界来说仅仅是研究元青花的开端，由此引申的更多问题——浮梁磁局的设立、青花料在当时景德镇的使用范围、青花瓷器的消费对象、甚至是图像的使用（人物画逐渐出现在陶瓷上）——值得进行更多的讨论与论证。";
                    kv.encode("PorcelainStoryContent",content);
                    kv.encode("PorcelainStoryName",name);
                    kv.encode("PorcelainStoryUrl",url);
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), PorcelainStoryDetailPageActivity.class);
                    view.getContext().startActivity(intent);
                }
                //"唐代青花"
                if (position == 2){
                    String name = "唐代青花";
                    String url = "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/OX~ERU67S5MLSA2QIN)8`]0.jpg";
                    String content = "\t\t\t\t自20世纪80年代开始，青花的起源成为中国陶瓷学界的热门讨论话题。\n\n\t\t\t\t1998年，“黑石号”上三件完整青花瓷盘的出水，昭示着早在公元9世纪，中国所生产的青花瓷已经远销海外。\n\n\t\t\t\t据以往的认知，青花起源于元代，风行于明清时期。这三件完整青花瓷盘的出水，更新了大家对青花出现时间的认知。\n\n\t\t\t\t早在1975年，扬州唐城遗址的发掘现场就出土了一片青花残片，这件残片以蓝色绘制菱形花叶纹。\n\n\t\t\t\t这件极为个例的残片，让考古的记录者在考古报告中写下这样一句话“这一片青花瓷的年代是值得探讨的问题，记录于此，仅供研究者参考。”可见发掘者对其出土于唐代地层肯定无疑，但在面对传统认知时候带有一丝的犹豫。但我想至少他为人们开始思考青花最早产生于何时提供了思路。\n\n\t\t\t\t随着国内考古发掘的不断深入，扬州、郑州、洛阳等地发现了更多青花残片，巩义黄冶窑、白河窑窑址出土的各类青花标本让人们不仅找到了唐青花的产地，还为探究唐青花的生产过程、制作工艺以及发展提供了珍贵的实物资料，人们对唐代青花的认知逐渐清晰。\n\n\t\t\t\t如果说扬州发现的第一例标本拉开了认识唐青花历史的序幕，以后更多标本和巩县窑唐青花窑场的发现则进一步改写了历史，而黑石号唐青花的打捞出水，更是再次见证了这一段历史。\n\n\t\t\t\t更有意思的是，当我们认为这类青花是专为外国市场而生产的外销产品时，瓷枕残片、青花盒、青花器盖也提示着我们应该关注青花的内销市场。\n\n\t\t\t\t2006年，郑州唐墓出土了两个青花塔式罐，这上面的纹饰十分有趣。“一人呈站立状，手中拿弯钩状长物，下部有一圆球，作击打状” 这其实是唐人手持球杖、徒步击球的步打球形象，类似的图像可在甘肃瓜州榆林窟中唐第15窟南壁的壁画、日本正仓院所藏唐代花毡中见到。\n\n\t\t\t\t最重要的是，这两个青花塔式罐的出土，完全确认了唐代青花的存在，并证明最晚不超过唐代中期，青花已经进入成熟阶段。\n\n\t\t\t\t至此，关于唐代的青花故事就结束了。\n\n\t\t\t\t也许很多小伙伴会问，那宋代呢，有没有更多的青花？\n\n\t\t\t\t由于目前尚未发现宋代烧造青花的窑址，一些地区出土的宋青花受到质疑，所以关于宋代青花瓷的问题仍存在争议，我们只能期待相关考古工作的进一步深入。";
                    kv.encode("PorcelainStoryContent",content);
                    kv.encode("PorcelainStoryName",name);
                    kv.encode("PorcelainStoryUrl",url);
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), PorcelainStoryDetailPageActivity.class);
                    view.getContext().startActivity(intent);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PorcelainStory porcelainStory = mList.get(position);
        holder.porcelainStoryName.setText(porcelainStory.getName());
        holder.porcelainStoryDescription.setText(porcelainStory.getDescription());
        holder.porcelainStoryImage.setImageResource(porcelainStory.getImageId());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
