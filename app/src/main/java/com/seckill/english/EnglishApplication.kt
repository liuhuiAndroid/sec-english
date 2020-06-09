package com.seckill.english

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class EnglishApplication : Application() {

    private var TAG: String = "EnglishApplication"

    override fun onCreate() {
        super.onCreate()

        // 检索当前注册令牌
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token
                Log.d(TAG, "检索当前注册令牌：$token")
            })
    }
}