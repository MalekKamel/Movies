package com.movie.app.shared.data

import com.movie.app.shared.network.ApiRepository
import com.movie.app.shared.rx.SchedulerProvider
import com.movie.app.shared.util.SharedPref

open class DataManager(
        val pref: SharedPref,
        val api: ApiRepository,
        val schedulerProvider: SchedulerProvider
)
