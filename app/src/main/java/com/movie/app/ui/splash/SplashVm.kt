package com.movie.app.ui.splash

import com.movie.app.shared.data.DataManager
import com.movie.app.shared.vm.BaseViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Sha on 9/15/17.
 */

val splashModule = module {
    viewModel { SplashVm(get()) }
}

class SplashVm(dataManager: DataManager)
    : BaseViewModel(dataManager)