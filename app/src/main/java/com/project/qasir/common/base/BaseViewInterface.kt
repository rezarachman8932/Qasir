package com.project.qasir.common.base

import com.project.qasir.data.remote.model.LoaderState

interface BaseViewInterface {
    fun showLoadingDialog()
    fun hideLoadingDialog()
    fun handleLoader(it: LoaderState?)
}