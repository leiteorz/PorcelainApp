package com.android.china.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.android.china.adpter.MyWorkAdapter;
import com.android.china.model.MyWork;
import com.android.china.utils.MyStatusBarTransparency;
import com.android.china.viewModel.NavigationStatusModel;
import com.google.ar.sceneform.samples.gltf.R;
import com.google.ar.sceneform.samples.gltf.databinding.ActivityMyPageBinding;

import com.kongzue.dialogx.DialogX;
import com.kongzue.dialogx.dialogs.BottomMenu;
import com.kongzue.dialogx.dialogs.InputDialog;
import com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;
import com.kongzue.dialogx.style.IOSStyle;
import com.kongzue.dialogx.style.MIUIStyle;
import com.tencent.mmkv.MMKV;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MyPageActivity extends AppCompatActivity implements View.OnClickListener {
    //作品List
    private List<MyWork> myWorkList = new ArrayList<>();
    private MyWorkAdapter myWorkAdapter;
    private MyStatusBarTransparency myStatusBarTransparency;
    private ActivityMyPageBinding binding;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;
    private MMKV kv;
    private String name_default ;
    private String signature_default ;
    private String take_photo ;
    private String choose_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMyPageActivityStatus();
        initBinding();
        initNavigationStatus(); //设置导航栏状态为2
        initRecyclerView();
        initWorks();
        initMmkv();
        initData();
    }
    public void initData(){
        name_default = kv.decodeString("name");
        signature_default = kv.decodeString("signature");
        take_photo = kv.decodeString("take_photo");
        choose_photo = kv.decodeString("choose_photo");
        if(!TextUtils.isEmpty(name_default)){
            binding.myName.setText(name_default);
        }else{
            binding.myName.setText("你的名字");
            name_default = "你的名字";
        }
        if(!TextUtils.isEmpty(signature_default)){
            binding.mySignature.setText(signature_default);
        }else{
            binding.mySignature.setText("寒来暑往，秋收冬藏");
            signature_default = "寒来暑往，秋收冬藏";
        }
        if(!TextUtils.isEmpty(take_photo)){
            //将String数据转为Bitmap取出
            byte[] byteArray= Base64.decode(take_photo, Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
            Bitmap bitmap1 = BitmapFactory.decodeStream(byteArrayInputStream);
            binding.myPhoto.setImageBitmap(bitmap1);
        }
        if(!TextUtils.isEmpty(choose_photo)){
            displayImage(choose_photo);
        }
    }
    public void initMmkv(){
        String rootDir = MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
    }
    public void initBinding(){
        binding = ActivityMyPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.myPhoto.setOnClickListener(this);
        binding.myName.setOnClickListener(this);
        binding.mySignature.setOnClickListener(this);
        binding.jumpCollectionBtn.setOnClickListener(this);
    }
    public void initMyPageActivityStatus(){
        myStatusBarTransparency = new MyStatusBarTransparency();
        myStatusBarTransparency.setFullscreen(true,true,this);
        myStatusBarTransparency.setAndroidNativeLightStatusBar(this,true);
    }
    /**
     * 设置导航栏状态
     */
    private void initNavigationStatus(){
        final NavigationStatusModel model = ViewModelProviders.of(this).get(NavigationStatusModel.class);
        model.setStatus(2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_Photo:
                DialogX.globalStyle = new IOSStyle();
                BottomMenu.show(new String[]{"从相册选择", "拍照", "取消"})
                        .setMessage("编辑头像")
                        .setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() {
                            @Override
                            public boolean onClick(BottomMenu dialog, CharSequence text, int index) {
                                switch (index){
                                    case 0:
                                        choosePhoto();
                                        break;
                                    case 1:
                                        startPhoto();
                                        break;
                                    default:
                                        break;
                                }
                                return false;
                            }
                        });
                break;
            case R.id.my_Name:
                DialogX.globalStyle = new MIUIStyle();
                new InputDialog("YourName", "请输入修改后的名字", "确定", "取消", name_default)
                        .setCancelable(true)
                        .setOkButton(new OnInputDialogButtonClickListener<InputDialog>() {
                            @Override
                            public boolean onClick(InputDialog baseDialog, View v, String inputStr) {
                                if(!TextUtils.isEmpty(inputStr)){
                                    binding.myName.setText(inputStr);
                                    kv.encode("name",inputStr);
                                    name_default = inputStr;
                                }else{
                                    Toast.makeText(getApplicationContext(),"名字不可为空，设置失败",Toast.LENGTH_SHORT).show();
                                }
                                return false;
                            }
                        })
                        .show();
                break;
            case  R.id.my_Signature:
                DialogX.globalStyle = new IOSStyle();
                new InputDialog("个性签名", "编辑个签，展示我的独特态度", "确定", "取消", signature_default)
                        .setCancelable(false)
                        .setOkButton(new OnInputDialogButtonClickListener<InputDialog>() {
                            @Override
                            public boolean onClick(InputDialog baseDialog, View v, String inputStr) {
                                if(!TextUtils.isEmpty(inputStr)){
                                    binding.mySignature.setText(inputStr);
                                    kv.encode("signature",inputStr);
                                    signature_default = inputStr;
                                }else{
                                    Toast.makeText(getApplicationContext(),"签名不可为空，设置失败",Toast.LENGTH_SHORT).show();
                                }
                                return false;
                            }
                        })
                        .show();
                break;
            case R.id.jump_collection_btn:
                Intent intent = new Intent();
                intent.setClass(MyPageActivity.this,MyCollectionActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }
    private void choosePhoto(){
        if(ContextCompat.checkSelfPermission(MyPageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) MyPageActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            openAlbum();
        }
    }
    public void startPhoto(){
        //创建File对象 用于存储拍照后的图片
        File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
        try{
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(getApplicationContext(),
                    "com.example.chinaplus.cameraalbumtest.fileprovider",outputImage);
        }else{
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        Bitmap bitmap= BitmapFactory.decodeStream(getApplicationContext().getContentResolver().openInputStream(imageUri));
                        binding.myPhoto.setImageBitmap(bitmap);
                        //将Bitmap数据转成String数据存入
                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray=byteArrayOutputStream.toByteArray();
                        String imageString=new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
                        if(!TextUtils.isEmpty(imageString)){
                            kv.encode("take_photo",imageString);
                            kv.removeValueForKey("choose_photo");
                        }
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    if (Build.VERSION.SDK_INT>=19){
                        //用这个方法处理图片
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    private void handleImageOnKitKat(@NonNull Intent data){
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
        if(!TextUtils.isEmpty(imagePath))
        {
            kv.encode("choose_photo",imagePath);
        }
        displayImage(imagePath);
    }
    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            binding.myPhoto.setImageBitmap(bitmap);
        }else{
            Toast.makeText(getApplicationContext(),"failed to get image",Toast.LENGTH_SHORT).show();
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

    /**
     * 初始化作品RecyclerView
     */
    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.workRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myWorkAdapter = new MyWorkAdapter(myWorkList);
        binding.workRecyclerView.setAdapter(myWorkAdapter);
    }

    /**
     * @Test
     * 加载作品RecyclerView(仅作为测试)
     */
    private void initWorks(){
        for(int i=0;i<10;i++){
            MyWork work = new MyWork("作品表述："+i+"号",R.drawable.china_collect_img);
            myWorkList.add(work);
        }
    }
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getApplicationContext(), "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void handleImageBeforeKitKat(@NonNull Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
        if(!TextUtils.isEmpty(imagePath))
        {
            kv.encode("choose_photo",imagePath);
            kv.removeValueForKey("take_photo");
        }
    }
}
