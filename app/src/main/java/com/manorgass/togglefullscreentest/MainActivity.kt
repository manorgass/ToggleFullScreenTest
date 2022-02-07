package com.manorgass.togglefullscreentest

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.btnFullScreenOn).setOnClickListener { setFullScreen() }
        findViewById<Button>(R.id.btnFullScreenOff).setOnClickListener { initFullScreen() }
        findViewById<Button>(R.id.btnEdgeToEdgeOn).setOnClickListener { setEdgeToEdge(false) }
        findViewById<Button>(R.id.btnEdgeToEdgeOff).setOnClickListener { setEdgeToEdge(true) }
    }

    private fun setFullScreen() {
        // 사용자 제스쳐 시 System Bars가 나타났다 사라지는 정책 설정.
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    private fun initFullScreen() {
        // 0으로 설정 시 Clear 됨.
        window?.decorView?.systemUiVisibility = 0
    }

    private fun setEdgeToEdge(fitSystemWindow: Boolean) {
        WindowCompat.setDecorFitsSystemWindows(window, fitSystemWindow)
    }
}