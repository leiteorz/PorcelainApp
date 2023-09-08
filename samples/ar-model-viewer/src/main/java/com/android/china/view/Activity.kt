package com.android.china.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.commit
import com.google.ar.sceneform.samples.gltf.R
class Activity : AppCompatActivity(R.layout.activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar).apply {
            title = ""
        })
        supportFragmentManager.commit {
            add(R.id.containerFragment, MainFragment::class.java, Bundle())
        }
    }
}