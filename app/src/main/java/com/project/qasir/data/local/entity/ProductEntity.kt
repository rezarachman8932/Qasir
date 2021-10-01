package com.project.qasir.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.project.qasir.data.local.converter.ProductConverter
import com.project.qasir.data.remote.model.ProductImage

@Entity(tableName = "product")
@TypeConverters(ProductConverter::class)
data class ProductEntity(
        @PrimaryKey val product_id: Int,
        val product_name: String,
        val price: Int,
        val stock: Int,
        val description: String,
        val images: ProductImage
)
