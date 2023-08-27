package com.android.china.view;

import static android.app.Activity.RESULT_OK;
import static com.kongzue.dialogx.interfaces.BaseDialog.getApplicationContext;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.china.model.ResultEasydl;
import com.android.china.model.Results;
import com.android.china.taocishibie.Base64Util;
import com.android.china.taocishibie.FileUtil;
import com.android.china.taocishibie.GsonUtils;
import com.android.china.taocishibie.HttpUtil;
import com.android.china.utils.MyApplication;
import com.android.china.utils.PostToken;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.FragmentTest2Binding;
import com.google.gson.Gson;
import com.kongzue.dialogx.DialogX;
import com.kongzue.dialogx.dialogs.CustomDialog;
import com.kongzue.dialogx.dialogs.PopMenu;
import com.kongzue.dialogx.dialogs.TipDialog;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnBindView;
import com.kongzue.dialogx.interfaces.OnIconChangeCallBack;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;
import com.kongzue.dialogx.style.IOSStyle;
import com.tencent.mmkv.MMKV;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.xml.transform.Result;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class TestFragment2 extends Fragment {
    private static final int TAKE_PHOTO = 1;
    private static FragmentTest2Binding binding;
    private static final int CHOOSE_PHOTO = 2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private File outputImage;
    private  String accessToken;
    private Map<String,String> map;
    private boolean isVisibleToUser = false;
    private MMKV kv;
    private String mParam1;
    private String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/ciyuliling";
    private String mParam2;
    //    拍照的图片保存至bitmapDialog
    private Bitmap bitmapDialog;
    public TestFragment2() {
        // Required empty public constructor
    }


    public static TestFragment2 newInstance(String param1, String param2) {
        TestFragment2 fragment = new TestFragment2();
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
        binding = FragmentTest2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initClick();
        initMmkv();

    }
    public void initMmkv(){
        String rootDir = MMKV.initialize(getActivity());
        kv = MMKV.defaultMMKV();
    }
    public void initClick(){
        binding.pictureShibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog();
//                测试 可以删除
            }
        });
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;

        if (isVisibleToUser) {
            // 当前 Fragment 可见，调用特定方法
            yourMethod();
        }
    }

    private void yourMethod() {
        // 这里是你想要调用的特定方法的代码
        initDialog();
    }
    public void initData(){
        map = new HashMap<>();
        map.put("釉下五彩人物故事瓶","釉下五彩人物故事瓶是中国传统陶瓷艺术的一种代表性作品，以其精美的釉下彩绘和丰富多样的人物故事而闻名。这种瓶子通常由陶瓷制成，然后在表面施以釉下彩绘装饰。");
        map.put("釉下五彩各族人民大团结瓷瓶","中华人民共和国（20世纪70年代），高64cm，口径18.5cm，底座17.2cm。不同民族的人物形象承载了人们对于民族团结和和谐发展的美好愿景，展示了中国多元文化的丰富性和包容性。");
        map.put("釉下五彩孔雀牡丹纹瓷花瓶","孔雀在中国文化中象征着美丽、吉祥和尊贵，被认为是国家的象征之一。牡丹是中国传统文化中的名花之一，代表着繁荣富贵、美好和富饶。将孔雀和牡丹结合在一起，形成了独特的图案和纹饰，展现了华丽和典雅的艺术风格。");
        map.put("釉下五彩山水楼阁图瓶","中国山水画是中国传统绘画中的重要流派之一，以描绘自然景色、山川河流为主题，通过艺术手法表现山水之间的气势、意境和情感。楼阁是中国古代建筑的代表之一，也是山水画中常见的元素之一。将山水和楼阁结合在一起，形成了富有中国传统文化特色的图案和纹饰。");
        map.put("釉下五彩扁豆双禽纹凤尾尊","扁豆是一种古老的图形纹饰，具有简洁、规则而富有装饰性的特点，在中国传统陶瓷中常被用作背景花纹。双禽纹则代表了两只鸟类，通常是凤凰、孔雀或者鹤等吉祥的鸟类形象。它不仅代表着中国传统陶瓷的独特魅力，还反映了人们对美好事物和吉祥祝福的追求。");
        map.put("釉下五彩松鹤纹瓷瓶","松树在中国文化中象征着长寿、坚韧和不朽，被誉为\"岁寒三友\"之一。鹤则是中国传统文化中的吉祥之鸟，代表着祥瑞和长寿釉下五彩松鹤纹瓷瓶通过精湛的釉下彩绘技法，展现了生动的松树和飞翔的鹤的形象。");
        map.put("釉下五彩牡丹耄耋纹琵琶尊","\"牡丹\"是中国传统文化中非常重要和著名的花卉之一，象征着繁荣、富贵和美丽。\"耄耋\"一词通常用来描述年长的人，表示他们岁月的积累和智慧的体现。\"琵琶\"是中国传统乐器之一，具有浓厚的文化内涵，展示了寿命长久、音乐艺术和美好生活的意象。");
        map.put("釉下五彩玉兰牡丹纹带座瓷瓶","玉兰和牡丹都是中国传统文化中非常重要的花卉。玉兰象征着高洁、纯美和高贵，被誉为\"花中之王\"。牡丹则象征着繁荣、富贵和美丽，是中国传统文化中的\"国花\"，不仅展现了展示了对美的追求和对繁荣富贵的向往,也表达了对传统文化的尊崇和传承。");
        map.put("釉下五彩竹林七贤纹瓷赏瓶","竹林七贤是中国历史上七位文人学士的集合名词，通过细致的彩绘表现出他们的风采和特征，如士人形象、笔墨纵横、酒杯琴瑟等。整个纹样形成了一幅富有意境和情趣的画面，让人联想到文人雅士的境界和人生追求。");
        map.put("釉下五彩花卉孔雀纹瓷瓶","花卉和孔雀在中国文化中都有特殊的象征意义。花卉常常被赋予吉祥、美好的寓意，不同的花卉代表着不同的寓意;孔雀则象征着华丽、自由和繁荣，在中国文化中被视为吉祥的象征之一。通过富有生机的花卉与华丽的孔雀图案，展现了自然之美与繁荣之意，并传递着吉祥和美好的寓意。");
        map.put("釉下五彩花鸟纹瓷象鼻花瓶","釉下五彩花鸟纹瓷象鼻花瓶展示了传统陶瓷艺术中花鸟元素的精湛技艺，通过色彩的丰富和图案的精细呈现，营造出一种充满生命力和美好寓意的艺术氛围。");
        map.put("釉下五彩锦鸡牡丹纹凤尾尊","锦鸡和牡丹是中国传统文化中常见的元素,牡丹作为国花，象征着繁荣、富贵和美丽；而锦鸡则是具有华丽羽毛、高贵气质的鸟类，代表着吉祥和祥瑞的意义。通过色彩的丰富和图案的精细呈现，营造出一种华美、吉祥和美好的艺术氛围。");
        map.put("釉下五彩青花缠枝瓷瓶","缠枝纹是中国传统陶瓷装饰纹样之一，通常以花草枝干交织缠绕的形式展现。它象征着生命的延续和繁盛，寓意着吉祥、繁荣和团圆。釉下五彩青花缠枝瓷瓶展示了传统陶瓷艺术中缠枝纹饰的精湛技艺。通过色彩的丰富和图案的细腻呈现，营造出一种自然、和谐和吉祥的艺术氛围。");
        map.put("釉下五彩黑底满花瓶","釉下五彩黑底满花瓶的制作过程需要高度的技艺和耐心，整个瓶子表面都被各种花朵图案所装饰,艺术家们通过层层上釉和烧制，使得花卉图案色彩饱满，绘画效果逼真。");
        map.put("釉下五彩龙纹瓷瓶","龙在中国文化中象征着吉祥、权威和力量，被视为神圣的存在。在龙纹瓷瓶上，艺术家们会运用釉下五彩技法来绘制生动逼真的龙纹图案，突出龙的神秘和威严。龙纹瓷瓶的制作需要高超的技艺和耐心，艺术家们通过精湛的绘画技巧描绘出龙的形态和神态，使得龙纹图案栩栩如生、栩栩欲动。" );
        map.put("釉下墨彩瓷琵琶尊","瓷琵琶尊是一种瓷器容器，形状类似于琵琶，上部为圆形，下部有底座。它通常使用在喝茶或品尝美酒时，以增加仪式感和文化内涵。釉下墨彩瓷琵琶尊通过在瓷器表面绘制各种图案，如花卉、山水、人物等，展现了醴陵独特的陶瓷艺术风格。");
        map.put("釉下复合彩花卉瓶","釉下复合彩是一种在瓷器施釉后，在表面进行多重彩绘的技法。与传统的釉下彩绘不同，釉下复合彩使用多种不同颜色的釉料，通过层层上釉和烧制，使得图案色彩更加丰富、立体感更强。它展现了中国陶瓷艺术中对花卉的崇尚和赞美，具有浓厚的自然和文化氛围。");
        map.put("釉下孔雀牡丹青花瓷瓶","艺术家使用釉下彩绘技法绘制出精美的孔雀和牡丹图案。通过巧妙运用蓝色和白色，图案呈现出层次感，栩栩如生。这种细腻而精致的装饰赋予了瓷瓶高雅和独特的艺术价值。不仅可以欣赏其美丽的图案和精湛的工艺，还能彰显中国传统文化的魅力。");
        map.put("釉下彩松鹤纹瓷瓶","釉下彩松鹤纹瓷瓶是一种以松树和鹤为主题的陶瓷艺术品，松树象征着坚强、长寿、忍耐和勇气,被誉为“君子之木”，而鹤在中国文化中也是吉祥的象征，代表着祥瑞、长寿和幸福。釉下彩松鹤纹瓷瓶以其精美的图案和独特的艺术风格展示和传承着中国传统陶瓷文化的精髓。");
        map.put("釉下祭蓝瓶","祭蓝釉天球瓶，霁蓝釉是以钴为呈色剂的一种蓝釉，其起源可追溯到唐三彩陶器。高温钴蓝釉瓷器则是元代景德镇发明的。钴是青色的呈色剂，融入釉中，即可烧成钴蓝釉，呈色十分稳定。");
    }
    private void initDialog(){
        DialogX.init(getActivity());
        DialogX.globalStyle = new IOSStyle();
        PopMenu.show(new String[]{"拍照识别", "相册选择", "取消"})
                .setOnMenuItemClickListener(new OnMenuItemClickListener<PopMenu>() {
                    @Override
                    public boolean onClick(PopMenu dialog, CharSequence text, int index) {
                        switch (index){
                            case 0:
                                startPhoto();
                                break;
                            case 1:
                                choosePhoto();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                })
                .setOnIconChangeCallBack(new OnIconChangeCallBack<PopMenu>(true) {
                    @Override
                    public int getIcon(PopMenu dialog, int index, String menuText) {
                        switch (index) {
                            case 0:
                                return R.drawable.img_dialogx_pai_zhao;
                            case 1:
                                return R.drawable.img_dialogx_xiang_ce;
                            case 2:
                                return R.drawable.img_dialogx_qu_xiao;
                            default:
                                return 0;
                        }
                    }
                });
    }
    private void choosePhoto(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            openAlbum();
        }
    }
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }
    public void startPhoto(){
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("HH_mm_ss");
        String filename = timeStampFormat.format(new Date());
        //创建File对象
        outputImage = new File(getActivity().getExternalCacheDir(), "takePhoto" + filename + ".jpg");
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(getActivity(),
                    "com.example.chinaplus.cameraalbumtest.fileprovider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        //打开相机
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== RESULT_OK){
            if(requestCode==TAKE_PHOTO){
                if (outputImage.exists()) {
                    String imagePath = outputImage.getAbsolutePath();
                    Thread thread = new Thread(new NetworkTask(imagePath));
                    WaitDialog.show("正在识别中！");
                    thread.start();
                } else {
                    System.out.println("图片不存在");
                }
            }else if(requestCode == CHOOSE_PHOTO){
                //打开相册返回
                String image_Path;
                if (Build.VERSION.SDK_INT>=19){
                    //用这个方法处理图片
                    image_Path = handleImageOnKitKat(data);
                }else{
                    image_Path = handleImageBeforeKitKat(data);
                }
                //识别
                Thread thread = new Thread(new NetworkTask(image_Path));
                WaitDialog.show("正在识别中！ ");
                thread.start();
            }
        }
    }
    private String handleImageOnKitKat(@NonNull Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(getApplicationContext(),uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        return imagePath;
    }
    private String handleImageBeforeKitKat(@NonNull Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        return imagePath;
    }
    private void handleResult(String result) {
        if (result != null) {
            // 处理和显示结果
            System.out.println("这是处理和显示结果：" + result);
        } else {
            System.out.println("请求失败,请根据日志情况查询错误原因");
            WaitDialog.dismiss();
            TipDialog.show("图片过大，请重新选择图片!", WaitDialog.TYPE.ERROR, 800);
        }
    }
    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection){
        String path = null;
        Cursor cursor = getApplicationContext().getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private class NetworkTask implements Runnable {
        private String imagePath;

        public NetworkTask(String imagePath) {
            this.imagePath = imagePath;
        }
        public void run() {
            // 异步处理
            AsyncTask.execute(() -> {
                try {
                    //按字节读取文件
                    byte[] imgData = FileUtil.readFileByBytes(imagePath);
                    bitmapDialog = BitmapFactory.decodeFile(imagePath);
                    //字节转Base64
                    String imageBase64 = compressAndEncodeImage(imgData, 3 * 1024 * 1024);
                    System.out.println("这是imageBase64：" + imageBase64);
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("image", imageBase64);
                    map1.put("top_num", "5");
                    String param = GsonUtils.toJson(map1);
                    Response response = PostToken.getToken();
                    String responseBody = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseBody);
                    // 获取access_token的值 30天更新一次
                    String mmkvToken = kv.decodeString("token");
                    int mmkvToken_day = kv.decodeInt("time");
                    if(!TextUtils.isEmpty(mmkvToken)||mmkvToken_day>=28){
                        accessToken = jsonObject.getString("access_token");
                        kv.encode("token",accessToken);
                        kv.removeValueForKey("time");
                        kv.encode("time",1);
                    }else{
                        accessToken = mmkvToken;
                        kv.encode("time",mmkvToken_day+1);
                    }
                    System.out.println(accessToken);
                    String result = HttpUtil.post(url, accessToken, "application/json", param);
                    handleResult(result);
                    Gson gson = new Gson();
                    Results data = gson.fromJson(result, Results.class);
                    ResultEasydl firstResult = data.results[0];
                    // 在UI线程中更新UI和展示对话框
                    getActivity().runOnUiThread(() -> {
                        WaitDialog.dismiss();
                        System.out.println(firstResult.name + "，识别度: " + String.format("%.2f", firstResult.score));
                        CustomDialog.build()
                                .setCustomView(new OnBindView<CustomDialog>(R.layout.shi_bie_result_dialog) {
                                    @Override
                                    public void onBind(final CustomDialog dialog, View v) {
                                        TextView name = v.findViewById(R.id.shibie_name);
                                        TextView accuracy = v.findViewById(R.id.shibie_accuracy);
                                        ImageView imageView = v.findViewById(R.id.shibie_img);
                                        name.setText(firstResult.name);
                                        String text = map.get(firstResult.name);
                                        accuracy.setText(text);
//                                        accuracy.setText(("准确度："+(int)(firstResult.score*100)+ "%"));
                                        imageView.setImageBitmap(bitmapDialog);
                                    }
                                })
                                .setMaskColor(Color.parseColor("#4D000000"))
                                .setAlign(CustomDialog.ALIGN.TOP)
                                .show();
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    handleResult(null);
                }
            });
        }
    }
    private String compressAndEncodeImage(byte[] imgData, int maxSize) {
//        图片压缩算法
        // 获取图像的原始宽度和高度
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(imgData, 0, imgData.length, options);
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;

        // 根据原始宽度和高度计算缩放比例
        int scale = 1;
        while ((originalWidth / scale) * (originalHeight / scale) > maxSize) {
            scale++;
        }

        // 使用缩放比例加载图像
        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length, options);
        if (bitmap == null) {
            return null;
        }

        // 压缩图像，直到数据小于 maxSize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        while (baos.toByteArray().length > maxSize && quality > 0) {
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            quality -= 10;
            if (quality <= 0) {
                return null; // 压缩失败
            }
        }

        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
                .replaceAll(System.lineSeparator(), "");
    }

}