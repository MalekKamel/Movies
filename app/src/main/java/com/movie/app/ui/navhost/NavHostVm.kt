package com.movie.app.ui.navhost

import com.movie.app.shared.data.DataManager
import com.movie.app.shared.vm.BaseViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navHostModule = module {
    viewModel { NavHostVm(get()) }
}

class NavHostVm(dataManager: DataManager)
    : BaseViewModel(dataManager) {

}