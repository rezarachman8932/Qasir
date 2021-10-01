package com.project.qasir.presentation.page.detail

import com.project.qasir.data.local.dao.ProductDao
import com.project.qasir.data.local.mapper.toDomain

class DetailUseCase(private val productDao: ProductDao) {
    suspend fun getProductDetailOffline(productId: Int) = productDao.getProductById(productId).toDomain()
}