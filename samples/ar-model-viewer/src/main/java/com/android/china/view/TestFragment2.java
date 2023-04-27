package com.android.china.view;

import static android.app.Activity.RESULT_OK;
import static com.kongzue.dialogx.interfaces.BaseDialog.getApplicationContext;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.android.china.model.ResultEasydl;
import com.android.china.model.Results;
import com.android.china.taocishibie.Base64Util;
import com.android.china.taocishibie.FileUtil;
import com.android.china.taocishibie.GsonUtils;
import com.android.china.taocishibie.HttpUtil;
import com.android.china.utils.MyApplication;
import com.google.ar.sceneform.samples.gltf.databinding.FragmentTest2Binding;
import com.google.gson.Gson;

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


public class TestFragment2 extends Fragment {
    private static final int TAKE_PHOTO = 1;
    private static FragmentTest2Binding binding;
    private static final int CHOOSE_PHOTO = 2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private File outputImage;
    private String mParam1;
    private String mParam2;
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
        binding.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPhoto();
            }
        });
        binding.xj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhoto();
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
                    thread.start();
                } else {
                    System.out.println("图片不存在");
                }
            }else if(requestCode == CHOOSE_PHOTO){
                //打开相册返回
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                final Uri imageUri = Objects.requireNonNull(data).getData();
                Cursor cursor = getActivity().getContentResolver().query(imageUri, filePathColumns, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumns[0]);
                //获取图片路径
                String imagePath = cursor.getString(columnIndex);
                //识别
                Thread thread = new Thread(new NetworkTask(imagePath));
                thread.start();
                cursor.close();
            }
        }
    }
    private void handleResult(String result) {
        if (result != null) {
            // 处理和显示结果
            System.out.println("这是处理和显示结果：" + result);
        } else {
            System.out.println("请求失败");
        }
    }
    private class NetworkTask implements Runnable {
        private String imagePath;

        public NetworkTask(String imagePath) {
            this.imagePath = imagePath;
        }

        @Override
        public void run() {
            String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/ciyuliling";
            try {
                //按字节读取文件
                byte[] imgData = FileUtil.readFileByBytes(imagePath);
                //字节转Base64
                String imageBase64 = compressAndEncodeImage(imgData, 4 * 1024 * 1024);
                System.out.println("这是imageBase64：" + imageBase64);
                Map<String, Object> map = new HashMap<>();
                map.put("image", imageBase64);
                map.put("top_num", "5");
                String param = GsonUtils.toJson(map);
                // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
                String accessToken = "24.13d1f190b326120e566dead4b1fb2b96.2592000.1691554464.282335-35874216";
                String result = HttpUtil.post(url, accessToken, "application/json", param);
                handleResult(result);
                Gson gson = new Gson();
                Results data = gson.fromJson(result, Results.class);
                ResultEasydl firstResult = data.results[0];
                System.out.println(firstResult.name + "，识别度: " + String.format("%.2f", firstResult.score));
            } catch (Exception e) {
                e.printStackTrace();
                handleResult(null);
            }
        }
    }
    private String compressAndEncodeImage(byte[] imgData, int maxSize) {
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

    private Bitmap resizeBitmap(Bitmap bitmap, int minDimension, int maxDimension) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scale = Math.max((float) minDimension / Math.min(width, height), (float) maxDimension / Math.max(width, height));

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    private Bitmap compressBitmap(Bitmap bitmap, int maxSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // 最初压缩质量为100%
        int quality = 100;

        while (baos.toByteArray().length > maxSize && quality > 0) {
            baos.reset(); // 重置输出流

            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            quality -= 10; // 每次降低质量10%

            if (quality <= 0) {
                return null; // 压缩失败
            }
        }

        byte[] bitmapBytes = baos.toByteArray();
        return BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }
}