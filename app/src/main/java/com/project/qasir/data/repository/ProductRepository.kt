package com.project.qasir.data.repository

import com.project.qasir.common.base.BaseResponse
import com.project.qasir.data.remote.domain.ProductService
import com.project.qasir.data.remote.model.ProductData
import retrofit2.Response

class ProductRepository(private val productService: ProductService) {

    suspend fun getProducts(): Response<BaseResponse<ProductData>> {
        return  productService.getProducts()
    }

}