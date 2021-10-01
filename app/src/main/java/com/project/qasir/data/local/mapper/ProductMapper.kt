package com.project.qasir.data.local.mapper

import com.project.qasir.data.local.entity.ProductEntity
import com.project.qasir.data.remote.model.ProductItem

fun ProductItem.toEntity(): ProductEntity {
    return ProductEntity(
            product_id = this.product_id,
            product_name = this.product_name,
            price = this.price,
            stock = this.stock,
            description = this.description,
            images = this.images
    )
}

fun ProductEntity.toDomain(): ProductItem {
    return ProductItem(
            product_id = this.product_id,
            product_name = this.product_name,
            price = this.price,
            stock = this.stock,
            description = this.description,
            images = this.images
    )
}