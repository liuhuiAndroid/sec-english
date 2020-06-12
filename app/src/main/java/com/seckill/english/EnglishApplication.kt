package com.seckill.english

import android.app.Application
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.hyphenate.push.EMPushConfig
import com.seckill.common.utilities.Constants

class EnglishApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initEM()
    }

    /**
     * 初始化环信SDK
     */
    private fun initEM() {
        val options = EMOptions().apply {
            // 默认添加好友时，是不需要验证的，改成需要验证
            acceptInvitationAlways = false
            // 设置自动登录
            autoLogin = true
            // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
            autoTransferMessageAttachments = true
            // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
            setAutoDownloadThumbnail(true)
            // 配置推送
            pushConfig = EMPushConfig.Builder(applicationContext)
                .enableFCM(Constants.FCM_SENDER_ID).build()
            // 设置允许使用FCM推送
            isUseFCM = true
            // 设置FCM number
            fcmNumber = Constants.FCM_SENDER_ID
        }
        // 初始化
        EMClient.getInstance().init(applicationContext, options)
        // 在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
    }

}