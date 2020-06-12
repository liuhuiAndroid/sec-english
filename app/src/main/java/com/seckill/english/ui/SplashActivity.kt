package com.seckill.english.ui

import android.content.Intent
import android.os.Bundle
import com.seckill.common.base.BaseActivity
import com.seckill.english.R
import com.seckill.push.ui.TestEasemobActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(Intent(this, TestEasemobActivity::class.java))
    }
}