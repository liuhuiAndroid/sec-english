package com.seckill.english.ui

import android.R.attr.password
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.hyphenate.EMCallBack
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.seckill.english.R
import com.seckill.english.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() {

    private var TAG: String = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 检索当前注册令牌
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    mTvFirebaseToken.text = "检索令牌失败：exception = ${task.exception}"
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token
                Log.d(TAG, "检索当前注册令牌：$token")
                mTvFirebaseToken.text = "检索当前注册令牌：$token"
            })

        EMClient.getInstance().login("im-001", "im-001", object : EMCallBack {
            //回调
            override fun onSuccess() {
                Log.d("main", "登录聊天服务器成功！")
                // 注册环信消息监听
                EMClient.getInstance().chatManager().addMessageListener(msgListener)
                runOnUiThread { mTvEmLoginInfo.text = "${EMClient.getInstance().currentUser}登录成功" }
            }

            override fun onProgress(progress: Int, status: String) {}
            override fun onError(code: Int, message: String) {
                Log.d("main", "登录聊天服务器失败！")
                runOnUiThread { mTvEmLoginInfo.text = "${EMClient.getInstance().currentUser}登录失败" }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(msgListener)
    }

    private val msgListener = object : EMMessageListener {
        override fun onMessageRecalled(messages: MutableList<EMMessage>?) {
            Log.d(TAG, "onMessageRecalled")
        }

        override fun onMessageChanged(message: EMMessage?, change: Any?) {
            Log.d(TAG, "onMessageChanged")
        }

        override fun onCmdMessageReceived(messages: MutableList<EMMessage>?) {
            Log.d(TAG, "onCmdMessageReceived")
        }

        override fun onMessageReceived(messages: MutableList<EMMessage>?) {
            Log.d(TAG, "onMessageReceived")
        }

        override fun onMessageDelivered(messages: MutableList<EMMessage>?) {
            Log.d(TAG, "onMessageDelivered")
        }

        override fun onMessageRead(messages: MutableList<EMMessage>?) {
            Log.d(TAG, "onMessageRead")
        }
    }

}