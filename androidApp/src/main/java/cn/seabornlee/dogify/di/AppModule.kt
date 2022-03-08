package cn.seabornlee.dogify.di

import cn.seabornlee.dogify.android.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get(), get(), get()) }
}