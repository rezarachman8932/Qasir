package com.project.qasir.data.local.dao

import androidx.room.*
import com.project.qasir.data.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE product_id IN (:id)")
    suspend fun getProductById(id: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(projects: List<ProductEntity>)

}