package com.project.qasir.common.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.project.qasir.R
import com.project.qasir.data.remote.model.LoaderState

abstract class BaseActivity :
        AppCompatActivity(),
        BaseToolbarInterface,
        BaseViewInterface
{

    private var progressDialog: ProgressDialog? = null
    private var homeEnabled = true

    open fun setHomeEnabled(enable: Boolean) {
        homeEnabled = enable
    }

    override fun onStart() {
        super.onStart()

        setSupportActionBar(setToolbar())
        supportActionBar?.setDisplayShowTitleEnabled(showToolbarTitle())
        supportActionBar?.setDisplayHomeAsUpEnabled(homeEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(homeEnabled)
        setToolbar()?.setNavigationOnClickListener{ onBackPressed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle(getString(R.string.app_name))
        progressDialog?.setMessage(getString(R.string.label_please_wait))
    }

    override fun setToolbar(): Toolbar? = null

    override fun showToolbarTitle(): Boolean = true

    override fun showLoadingDialog() {
        progressDialog?.show()
    }

    override fun hideLoadingDialog() {
        progressDialog?.hide()
    }

    override fun handleLoader(it: LoaderState?) {
        when (it) {
            is LoaderState.OnLoading -> if (it.isLoading) showLoadingDialog() else hideLoadingDialog()
        }
    }

}