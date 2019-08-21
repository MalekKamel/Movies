package com.movie.app.shared.util.app

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes
import androidx.multidex.MultiDexApplication
import com.movie.app.shared.koin.KoinHelper
import com.movie.app.shared.util.CrashlyticsUtil

/**
 * Created by Sha on 13/04/17.
 */

class MyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        try {

            context = applicationContext

            KoinHelper.start(this)


        } catch (e: Exception) {
            CrashlyticsUtil.logAndPrint(e)
        }

    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        public lateinit var context: Context

        @JvmStatic
        fun string(@StringRes res: Int): String {
            return context.getString(res)
        }

    }
}
