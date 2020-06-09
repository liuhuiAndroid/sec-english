package com.seckill.english.service

import com.google.firebase.messaging.FirebaseMessagingService

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
    }
}