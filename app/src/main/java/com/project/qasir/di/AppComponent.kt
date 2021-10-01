package com.project.qasir.di

import com.project.qasir.common.base.BaseViewModel
import com.project.qasir.common.shared.NetworkVisibility
import com.project.qasir.common.shared.RetrofitService
import com.project.qasir.data.local.AppRoomDatabase
import com.project.qasir.data.remote.domain.ProductService
import com.project.qasir.data.repository.ProductRepository
import com.project.qasir.presentation.page.detail.DetailUseCase
import com.project.qasir.presentation.page.detail.DetailViewModel
import com.project.qasir.presentation.page.main.MainUseCase
import com.project.qasir.presentation.page.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val singleton = module {
    single { NetworkVisibility(get()) }
}

val networkModule = module {
    single { RetrofitService.callAPI<ProductService>(get()) }
}

val localModule = module {
    single { AppRoomDatabase.getDatabase(get()).productDao() }
}

val dataSourceModule = module {
    single { ProductRepository(get()) }
}

val useCaseModule = module {
    single { MainUseCase(get(), get()) }
    single { DetailUseCase(get()) }
}

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { DetailViewModel(get()) }
}

val appComponent: List<Module> = listOf(
        singleton,
        networkModule,
        localModule,
        dataSourceModule,
        useCaseModule,
        viewModelModule
)

