package com.seckill.english.ui

import android.os.Bundle
import android.util.Log
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.seckill.english.R
import com.seckill.english.base.BaseActivity

class SplashActivity : BaseActivity() {

    private var TAG: String = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        // 检索当前注册令牌
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    // java.io.IOException: SERVICE_NOT_AVAILABLE
                    // Method threw 'com.google.android.gms.tasks.RuntimeExecutionException' exception.
                    Log.d(TAG, "检索令牌失败：exception = ${task.exception}")
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token
                Log.d(TAG, "检索当前注册令牌：$token")
            })
    }

}