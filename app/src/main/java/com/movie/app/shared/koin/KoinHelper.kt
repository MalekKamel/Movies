package com.movie.app.shared.koin

import android.content.Context
import com.movie.app.ui.detail.movieDetailModule
import com.movie.app.ui.navhost.navHostModule
import com.movie.app.ui.search.searchModule
import com.movie.app.ui.splash.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinHelper {
    companion object {
        fun start(context: Context){
            startKoin {
                androidContext(context)
                modules(listOf(
                        appModule,
                        splashModule,
                        searchModule,
                        movieDetailModule,
                        navHostModule
                ))
            }
        }
    }
}