package com.manorgass.togglefullscreentest

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    private var isEdgeToEdge = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.btnFullScreenOn).setOnClickListener { setFullScreen() }
        findViewById<Button>(R.id.btnFullScreenOff).setOnClickListener { initFullScreen() }
        findViewById<Button>(R.id.btnEdgeToEdgeOn).setOnClickListener { enableEdgeToEdge() }
        findViewById<Button>(R.id.btnEdgeToEdgeOff).setOnClickListener { disableEdgeToEdge() }
    }

    private fun setFullScreen() {
        // 사용자 제스쳐 시 System Bars가 나타났다 사라지는 정책 설정.
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN

        // API 28 이하부터 Edge To Edge 설정한 후 systemUiVisibility 변경 시 기존에 설정한 Edge To Edge 설정이 초기화 되는 현상을 막기위한 처리
        if (Build.VERSION.SDK_INT < 29 && isEdgeToEdge) {
            enableEdgeToEdge()
        }
    }

    private fun initFullScreen() {
        // 0으로 설정 시 Clear 됨.
        window?.decorView?.systemUiVisibility = 0

        // API 28 이하부터 Edge To Edge 설정한 후 systemUiVisibility 변경 시 기존에 설정한 Edge To Edge 설정이 초기화 되는 현상을 막기위한 처리
        if (Build.VERSION.SDK_INT < 29 && isEdgeToEdge) {
            enableEdgeToEdge()
        }
    }

    private fun enableEdgeToEdge() {
        setEdgeToEdge(false)
    }

    private fun disableEdgeToEdge() {
        setEdgeToEdge(true)
    }

    private fun setEdgeToEdge(fitSystemWindow: Boolean) {
        isEdgeToEdge = !fitSystemWindow
        WindowCompat.setDecorFitsSystemWindows(window, fitSystemWindow)
    }
}