package com.project.qasir.presentation.page.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.qasir.common.base.BaseViewModel
import com.project.qasir.data.remote.model.ProductItem
import kotlinx.coroutines.launch

class DetailViewModel(private val detailUseCase: DetailUseCase) : BaseViewModel() {

    private val _getProductDetailFromLocal = MutableLiveData<ProductItem>()
    val getProductDetailFromLocal: LiveData<ProductItem> = _getProductDetailFromLocal

    fun setContentDetail(productId: Int) {
        viewModelScope.launch {
            val content = detailUseCase.getProductDetailOffline(productId)
            _getProductDetailFromLocal.postValue(content)
        }
    }

}