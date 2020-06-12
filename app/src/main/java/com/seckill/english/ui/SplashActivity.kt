package com.seckill.english.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.hyphenate.EMCallBack
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMMessage.ChatType
import com.hyphenate.chat.EMTextMessageBody
import com.seckill.english.R
import com.seckill.english.base.BaseActivity
import com.seckill.english.ext.toast
import kotlinx.android.synthetic.main.activity_splash.*
import org.json.JSONException
import org.json.JSONObject
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.ktx.withPermissionsCheck

class SplashActivity : BaseActivity() {

    private var TAG: String = "SplashActivity"

    /**
     * 检查权限
     */
    private fun checkPermissions() = withPermissionsCheck(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        onShowRationale = ::onPermissionsShowRationale,
        onPermissionDenied = ::onPermissionsDenied,
        onNeverAskAgain = ::onPermissionsNeverAskAgain
    ) {

    }

    private fun onPermissionsDenied() {
        toast("无法正常获取权限")
        checkPermissions()
    }

    private fun onPermissionsShowRationale(request: PermissionRequest) {
        request.proceed()
    }

    private fun onPermissionsNeverAskAgain() {
        toast("无法正常获取权限")
        checkPermissions()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        checkPermissions()

        mBtnLogin01.setOnClickListener {
            login("im-001", "im-001")
        }
        mBtnLogin02.setOnClickListener {
            login("im-002", "im-002")
        }
        mBtnSendMsg001.setOnClickListener {
            // 这里只是一 TXT 消息为例，IMAGE FILE 等类型的消息设置方法相同
            val message = EMMessage.createTxtSendMessage("来自IM-002", "im-001")
            // 设置自定义推送提示
            val extObject = JSONObject()
            try {
                extObject.put("em_push_name", "离线推送标题")
                extObject.put("em_push_content", "离线推送内容部分")
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            // 将推送扩展设置到消息中
            message.setAttribute("em_apns_ext", extObject)
            // 设置自定义扩展字段
            message.setAttribute("em_force_notification", true)
            message.setMessageStatusCallback(object : EMCallBack {
                override fun onSuccess() {
                    runOnUiThread {
                        Toast.makeText(this@SplashActivity, "发送成功", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onProgress(progress: Int, status: String?) {
                    TODO("Not yet implemented")
                }

                override fun onError(code: Int, error: String?) {
                    runOnUiThread {
                        Toast.makeText(this@SplashActivity, "发送失败", Toast.LENGTH_SHORT).show()
                    }
                }

            })
            // 发送消息
            EMClient.getInstance().chatManager().sendMessage(message)
        }
    }

    private fun login(username: String, password: String) {
        EMClient.getInstance().login(username, password, object : EMCallBack {
            //回调
            override fun onSuccess() {
                Log.d("main", "登录聊天服务器成功！")
                // 注册环信消息监听
                EMClient.getInstance().chatManager().addMessageListener(msgListener)
                runOnUiThread { mTvEmLoginInfo.text = "${EMClient.getInstance().currentUser}登录成功" }

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
                        // Important, send the fcm token to the server
                        // 检索EMPushHelper查看下fcm token是否上传成功
                        EMClient.getInstance().sendFCMTokenToServer(token)
                    })
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
            messages?.firstOrNull()?.let {
                runOnUiThread {
                    Toast.makeText(
                        this@SplashActivity,
                        "onMessageReceived：${it.body}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        override fun onMessageDelivered(messages: MutableList<EMMessage>?) {
            Log.d(TAG, "onMessageDelivered")
        }

        override fun onMessageRead(messages: MutableList<EMMessage>?) {
            Log.d(TAG, "onMessageRead")
        }
    }

}