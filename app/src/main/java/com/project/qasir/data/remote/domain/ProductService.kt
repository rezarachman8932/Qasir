package com.project.qasir.data.remote.domain

import com.project.qasir.common.base.BaseResponse
import com.project.qasir.data.remote.model.ProductData
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("b/5ee794370e966a7aa369eafd")
    suspend fun getProducts() : Response<BaseResponse<ProductData>>
}