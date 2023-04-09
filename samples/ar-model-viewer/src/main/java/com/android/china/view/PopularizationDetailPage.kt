package com.android.china.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.china.model.ChinaHistory
import com.android.china.room.AppDataBase
import com.android.china.room.dao.ChinaHistoryDao
import com.android.china.room.dao.MyCollectionDao
import com.android.china.utils.MyStatusBarTransparency
import com.google.ar.sceneform.samples.gltf.R
import com.google.ar.sceneform.samples.gltf.databinding.ActivityPopularizationDetailPageBinding
import com.tencent.mmkv.MMKV

class PopularizationDetailPage : AppCompatActivity() {
    private var binding: ActivityPopularizationDetailPageBinding? = null

    private var kv: MMKV? = null

    private var myStatusBarTransparency: MyStatusBarTransparency? = null

    private var db: AppDataBase? = null

    private var popularizationDao: ChinaHistoryDao? = null

    private var myCollectionDao: MyCollectionDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popularization_detail_page)

        initBinding()
        initActivityStatus()

        createDatabase()
        initMmkv()

        initData()
        setCollectionBtnColor()
        onClick()
    }

    private fun initBinding(){
        binding = ActivityPopularizationDetailPageBinding.inflate(layoutInflater)
        var view: View = binding!!.root
        setContentView(view)
    }

    private fun initActivityStatus(){
        myStatusBarTransparency = MyStatusBarTransparency()
        myStatusBarTransparency!!.setFullscreen(true,true,this)
        myStatusBarTransparency!!.setAndroidNativeLightStatusBar(this,true)
    }

    private fun initMmkv(){
        var rootDir: String = MMKV.initialize(this)
        kv = MMKV.defaultMMKV()
    }

    private fun initData(){
        setSupportActionBar(binding?.popularizationItemToolbar)
        var actionBar: ActionBar? = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.popularizationItemToolbar?.setNavigationOnClickListener{
            var intent: Intent = Intent()
            intent.setClass(applicationContext,PocelainKepuActivity::class.java)
            startActivity(intent)
            finish()
        }

        var popularizationId: Int = intent.getIntExtra("popularizationId",0)
        var item: ChinaHistory = popularizationDao!!.queryChinaHistoryById(popularizationId)

        binding?.popularizationItemText?.text = "\t\t\t\t" + item.description
        binding?.popularizationItemCollapsingToolbar?.title = item.name
        binding?.popularizationItemImageview?.setImageUrl(item.imageUrl)
    }

    private fun createDatabase(){
        db = AppDataBase.getInstance(this)
        popularizationDao = db!!.ChinaHistoryDao()
        myCollectionDao = db!!.myCollectionDao()
    }

    /**
     * 设置收藏按钮的颜色
     */
    private fun setCollectionBtnColor(){
        var popularizationId: Int = intent.getIntExtra("popularizationId",0)
        var flag: Int = myCollectionDao!!.queryIfCollectedById(popularizationId,0)

        if (flag == 1) binding?.floatBtn?.setImageTintList(ColorStateList.valueOf(
            ContextCompat.getColor(this,R.color.yellow)))
        else if(flag == 0) binding?.floatBtn?.setImageTintList(ColorStateList.valueOf(
            ContextCompat.getColor(this,R.color.black40)))
    }

    /**
     * 点击事件
     */
    private fun onClick(){
        var popularizationId: Int = intent.getIntExtra("popularizationId",0)
        var flag: Int = myCollectionDao?.queryIfCollectedById(popularizationId,0) ?: 0
        /**
         * 收藏按钮的点击事件
         */
        binding?.floatBtn?.setOnClickListener{
            if (flag == 0){
                binding?.floatBtn?.setImageTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(this,R.color.yellow)))
                flag = 1
                myCollectionDao?.updateIsCollected(flag,popularizationId,0)
            }
            else if (flag == 1){
                binding?.floatBtn?.setImageTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(this,R.color.black40)))
                flag = 0
                myCollectionDao?.updateIsCollected(flag,popularizationId,0)
            }
        }
    }
}