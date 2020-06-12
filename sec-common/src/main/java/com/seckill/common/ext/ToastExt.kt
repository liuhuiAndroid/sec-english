package com.seckill.common.ext

import android.content.Context
import android.widget.Toast

inline fun Context.toast(message: CharSequence?) =
    Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }