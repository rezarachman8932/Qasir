package com.project.qasir.data.remote.model

import com.squareup.moshi.Json

data class ProductData(
    @Json(name = "banner") val banner: String,
    @Json(name = "products") val products: MutableList<ProductItem>
)

data class ProductItem(
    @Json(name = "product_id") val product_id: Int,
    @Json(name = "product_name") val product_name: String,
    @Json(name = "price") val price: Int,
    @Json(name = "stock") val stock: Int,
    @Json(name = "description") val description: String,
    @Json(name = "images") val images: ProductImage
)

data class ProductImage(
    @Json(name = "thumbnail") val thumbnail: String,
    @Json(name = "large") val large: String
)