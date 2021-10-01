package com.project.qasir.presentation.page.main

import com.project.qasir.data.local.dao.ProductDao
import com.project.qasir.data.local.mapper.toDomain
import com.project.qasir.data.repository.ProductRepository

class MainUseCase(private val productRepository: ProductRepository, private val productDao: ProductDao) {
    suspend fun getProducts() = productRepository.getProducts()
    suspend fun getProductsOffline() = productDao.getAllProducts().map { it.toDomain() }
}