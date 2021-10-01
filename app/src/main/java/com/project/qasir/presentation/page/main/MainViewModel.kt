package com.project.qasir.presentation.page.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.qasir.common.base.BaseResponse
import com.project.qasir.common.base.BaseViewModel
import com.project.qasir.common.shared.NetworkVisibility
import com.project.qasir.data.local.dao.ProductDao
import com.project.qasir.data.local.mapper.toEntity
import com.project.qasir.data.remote.model.LoaderState
import com.project.qasir.data.remote.model.ProductData
import com.project.qasir.data.remote.model.ProductItem
import com.project.qasir.data.remote.model.ResultState
import kotlinx.coroutines.launch

class MainViewModel(
        private val mainUseCase: MainUseCase,
        private val networkVisibility: NetworkVisibility,
        private val productDao: ProductDao
) : BaseViewModel() {

    private val _getProductList = MutableLiveData<ResultState<BaseResponse<ProductData>>>()
    val getProductList: LiveData<ResultState<BaseResponse<ProductData>>> = _getProductList

    private val _getProductListFromLocal = MutableLiveData<List<ProductItem>>()
    val getProductListFromLocal: LiveData<List<ProductItem>> = _getProductListFromLocal

    fun getProducts() {
        if (networkVisibility.hasNetworkAvailable()) {
            getRemoteProducts()
        } else {
            getProductsOffline()
        }
    }

    private fun getProductsOffline() {
        viewModelScope.launch {
            val listFromLocal = mainUseCase.getProductsOffline()
            _getProductListFromLocal.postValue(listFromLocal)
        }
    }

    private fun getRemoteProducts() {
        loaderState.value = LoaderState.OnLoading(true)

        viewModelScope.launch {
            try {
                val response = mainUseCase.getProducts()

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.data?.products?.let { list -> productDao.insertAllProducts(list.map { it.toEntity() }) }

                    _getProductList.postValue(ResultState.Success(data = response.body()))
                } else {
                    _getProductList.postValue(ResultState.Error(errorCode = response.code(), data = response.errorBody()))
                }

                loaderState.value = LoaderState.OnLoading(false)
            } catch (throwable: Throwable) {
                _getProductList.postValue(ResultState.Error(exception = throwable))

                loaderState.value = LoaderState.OnLoading(false)
            }
        }
    }

}