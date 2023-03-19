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
        model = ModelRenderable.builder()
            .setSource(context, Uri.parse(MODEL_URI))
            .setIsFilamentGltf(true)
            .await()
        modelView = ViewRenderable.builder()
            .setView(context, R.layout.view_renderable_infos)
            .await()
    }

    private fun onTapPlane(hitResult: HitResult, plane: Plane, motionEvent: MotionEvent) {
        if (model == null || modelView == null) {
            Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            return
        }

        // Create the Anchor.
        scene.addChild(
            AnchorNode(hitResult.createAnchor()).apply {
            // Create the transformable model and add it to the anchor.
            addChild(
                TransformableNode(arFragment.transformationSystem)
                    .apply {
                renderable = model
                renderableInstance.setCulling(false)
                renderableInstance.animate(true).start()
                // Add the View
                addChild(Node().apply {
                    // Define the relative position
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