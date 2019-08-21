package com.movie.app.ui.detail

import com.movie.app.shared.data.DataManager
import com.movie.app.shared.data.model.Movie
import com.movie.app.shared.vm.BaseViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailModule = module {
    viewModel { MovieDetailVm(get()) }
}

class MovieDetailVm(dataManager: DataManager)
    : BaseViewModel(dataManager) {
    lateinit var movie: Movie
}