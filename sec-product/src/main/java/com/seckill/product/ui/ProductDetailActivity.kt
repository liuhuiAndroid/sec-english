package com.seckill.product.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.seckill.common.base.BaseActivity
import com.seckill.common.utilities.RoutePath
import com.seckill.product.R

/**
 * 商品详情页面
 */
@Route(path = RoutePath.PATH_PRODUCT_DETAIL)
class ProductDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
    }

}