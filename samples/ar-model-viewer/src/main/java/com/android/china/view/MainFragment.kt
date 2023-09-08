package com.android.china.view

import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.ArSceneView
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.SceneView
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.samples.gltf.R
import com.gorisse.thomas.sceneform.scene.await
import com.tencent.mmkv.MMKV
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var MODEL_URI: String
    private lateinit var kv: MMKV
    private lateinit var arFragment: ArFragment
    private val arSceneView: ArSceneView get() = arFragment.arSceneView
    private val scene get() = arSceneView.scene
    private var model: Renderable? = null
    private var modelView: ViewRenderable? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getModel()
        arFragment = (childFragmentManager.findFragmentById(R.id.arFragment) as ArFragment).apply {
            setOnSessionConfigurationListener { session, config ->
                // Modify the AR session configuration here
            }
            setOnViewCreatedListener { arSceneView ->
                arSceneView.setFrameRateFactor(SceneView.FrameRate.FULL)
            }
            setOnTapArPlaneListener(::onTapPlane)
        }

        lifecycleScope.launchWhenCreated {
            loadModels()
        }
    }
    private suspend fun loadModels() {
        model = ModelRenderable.builder()//构建一个ModelRenderable对象，设置模型的源文件和解析模式，
            .setSource(context, Uri.parse(MODEL_URI))
            .setIsFilamentGltf(true)
            .await()
        modelView = ViewRenderable.builder()//构建一个ViewRenderable对象,设置视图的源文件，R.layout.view_renderable_infos是视图文件的资源ID
            .setView(context, R.layout.view_renderable_infos)
            .await()
    }
    private fun onTapPlane(hitResult: HitResult, plane: Plane, motionEvent: MotionEvent) {
        if (model == null || modelView == null) {//模型判空
            Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            return
        }
        scene.addChild(//创建一个锚点（Anchor）并添加到场景中
            AnchorNode(hitResult.createAnchor()).apply {
            addChild(
                TransformableNode(arFragment.transformationSystem)
                    .apply {
                renderable = model//禁用可渲染实例的剔除功能
                renderableInstance.setCulling(false)//启动可渲染实例的动画效果
                        renderableInstance.animate(true).start()
                addChild(Node().apply {//添加一个子节点的视图（Node），并设置其相对位置和缩放
                    localPosition =
                        Vector3(0.0f, 1f, 0.0f)
                    localScale =
                        Vector3(0.3f, 0.3f, 0.3f)
                    renderable = modelView
                })
            })
        })
    }
    /**
     * 拿到模型
     */
    private fun getModel(){
        var rootDir: String = MMKV.initialize(view?.context)
        kv = MMKV.defaultMMKV()
        MODEL_URI = kv.decodeString("ArModel")!!
    }
}