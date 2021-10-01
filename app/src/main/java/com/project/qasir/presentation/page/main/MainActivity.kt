package com.project.qasir.presentation.page.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.qasir.R
import com.project.qasir.common.base.BaseActivity
import com.project.qasir.common.base.BaseResponse
import com.project.qasir.common.constant.ProductConstant
import com.project.qasir.common.shared.ImageHelper
import com.project.qasir.data.remote.model.ProductData
import com.project.qasir.data.remote.model.ProductItem
import com.project.qasir.data.remote.model.ResultState
import com.project.qasir.presentation.adapter.ProductAdapter
import com.project.qasir.presentation.page.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()

        showLoadingDialog()

        viewModel.getProductList.observe(this, { handleResponseFromRemote(it) })
        viewModel.getProductListFromLocal.observe(this, { handleResponseFromLocal(it as MutableList<ProductItem>) })
        viewModel.getProducts()
    }

    override fun setToolbar(): Toolbar? {
        return toolbar
    }

    private fun setup() {
        setHomeEnabled(false)
        toolbar.title = getString(R.string.label_order_item)

        productAdapter = ProductAdapter { handleItemClicked(it as ProductItem) }

        val layoutManager = LinearLayoutManager(this)
        rv_product_item.layoutManager = layoutManager
        rv_product_item.adapter = productAdapter
    }

    private fun handleItemClicked(item: ProductItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(ProductConstant.PRODUCT_ID, item.product_id)
        intent.putExtra(ProductConstant.PRODUCT_NAME, item.product_name)
        startActivity(intent)
    }

    private fun handleResponseFromLocal(items: MutableList<ProductItem>) {
        hideLoadingDialog()
        setContent("", items)
    }

    private fun handleResponseFromRemote(result: ResultState<BaseResponse<ProductData>>) {
        when (result) {
            is ResultState.Success -> result.data?.let {
                hideLoadingDialog()
                setContent(it.data.banner, it.data.products)
            }
            is ResultState.Error -> {
                hideLoadingDialog()
            }
        }
    }

    private fun setContent(bannerUrl: String, items: MutableList<ProductItem>) {
        ImageHelper.setResource(bannerUrl, iv_thumbnail)
        productAdapter.setProducts(items)
    }

}