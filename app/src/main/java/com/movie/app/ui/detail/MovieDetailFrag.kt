package com.movie.app.ui.detail

import com.movie.app.R
import com.movie.app.shared.ext.withTitleRes
import com.movie.app.shared.ui.frag.BaseFrag
import com.movie.app.shared.util.picasso.PicassoUtil
import kotlinx.android.synthetic.main.frag_movie_detail.*
import kotlinx.android.synthetic.main.include__movie_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class MovieDetailFrag : BaseFrag<MovieDetailVm>() {

    override val vm: MovieDetailVm by sharedViewModel()

    override var layoutId: Int = R.layout.frag_movie_detail

    override fun setupUi() {
        tvTitle.text = vm.movie.title
        tvRate.text = vm.movie.voteAverage.toString().withTitleRes(R.string.rate)
        tvReleaseDate.text = vm.movie.releaseDate.withTitleRes(R.string.release_date)

        PicassoUtil.load(
                iv = ivPoster,
                url = vm.movie.poster()
        )
    }

}

