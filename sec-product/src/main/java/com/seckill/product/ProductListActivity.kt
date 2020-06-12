package com.seckill.product

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.seckill.common.base.BaseActivity
import com.seckill.common.utilities.RoutePath

@Route(path = RoutePath.PATH_PRODUCT_LIST)
class ProductListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
    }

}