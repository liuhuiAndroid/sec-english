package com.seckill.english.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.seckill.common.base.BaseActivity
import com.seckill.common.utilities.RoutePath
import com.seckill.english.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                delay(2000)
            }

//            ARouter.getInstance().build(RoutePath.PATH_PUSH_EASEMOB).navigation()
//            ARouter.getInstance().build(RoutePath.PATH_PRODUCT_LIST).navigation()
//            finish()
        }
    }

}