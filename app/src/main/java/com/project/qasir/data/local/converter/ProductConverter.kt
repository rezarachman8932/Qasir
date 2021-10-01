package com.project.qasir.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.qasir.data.remote.model.ProductImage
import java.lang.reflect.Type

class ProductConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToProductImage(data: String): ProductImage {
        val listType: Type = object : TypeToken<ProductImage>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun productImageToString(someObject: ProductImage): String {
        return gson.toJson(someObject)
    }

}