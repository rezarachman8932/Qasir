package com.project.qasir.presentation.page.detail

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.project.qasir.R
import com.project.qasir.common.base.BaseActivity
import com.project.qasir.common.constant.ProductConstant
import com.project.qasir.common.shared.ImageHelper
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setup()
    }

    override fun setToolbar(): Toolbar? {
        return toolbar
    }

    private fun setup() {
        val name = intent.getStringExtra(ProductConstant.PRODUCT_NAME)
        val id = intent.getIntExtra(ProductConstant.PRODUCT_ID, 0)

        setHomeEnabled(true)
        toolbar.title = name

        viewModel.setContentDetail(id)
        viewModel.getProductDetailFromLocal.observe(this, {
            ImageHelper.setResource(it.images.large, iv_thumbnail_detail)
            tv_title_product_detail.text = it.product_name
            tv_price_product_detail.text = it.price.toString()
            tv_description_product_detail.text = it.description
        })
    }

}