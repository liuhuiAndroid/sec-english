package com.seckill.push.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hyphenate.chat.EMClient

/**
 * Firebase 云消息传递服务
 * 作用：接收应用通知、在后台进行更多的消息处理工作
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    /**
     * 监控令牌的生成
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // sendRegistrationToServer(token)
        Log.d("MessagingService", "onNewToken：$token")
        // Important, send the fcm token to the server
        // 检索EMPushHelper查看下fcm token是否上传成功
        EMClient.getInstance().sendFCMTokenToServer(token)
    }

    /**
     * 应用处于前台可以收到onMessageReceived回调
     * 应用处于后台可以收到通知
     */
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
    }
}