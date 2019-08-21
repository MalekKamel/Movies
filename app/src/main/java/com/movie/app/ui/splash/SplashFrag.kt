package com.movie.app.ui.splash

import android.os.Handler
import androidx.navigation.Navigation
import com.movie.app.R
import com.movie.app.shared.ui.frag.BaseFrag
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFrag : BaseFrag<SplashVm>() {

    override var layoutId: Int = R.layout.frag_splash

    override val vm: SplashVm by viewModel()

    override fun setupUi() {

    }

    override fun doOnViewCreated() {
        Handler().postDelayed(
                {
                    Navigation.findNavController(view!!).navigate(R.id.action_splashFrag_to_searchFrag)
                },
                2000
        )
    }

}
