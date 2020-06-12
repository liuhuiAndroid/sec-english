package com.seckill.common.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun fullscreen() {
        // 使用全屏沉浸模式
        window.decorView.systemUiVisibility =
                // 避免某些用户交互造成系统自动清除全屏状态
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    // 隐藏系统NavigationBar
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    // 全屏状态
                    View.SYSTEM_UI_FLAG_FULLSCREEN
    }

}