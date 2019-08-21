package com.movie.app.shared.network

import com.movie.app.shared.data.model.MoviesRequest
import com.movie.app.shared.data.model.MoviesResponse
import io.reactivex.Flowable


class ApiRepository(private val api: ApiInterface) {

    fun searchMovies(request: MoviesRequest): Flowable<MoviesResponse> {
        return api.searchDoctors(
                query = request.search,
                page = request.nextPage
        )
    }

    fun popularMovies(request: MoviesRequest): Flowable<MoviesResponse> {
        return api.popularMovies(page =  request.nextPage)
    }

}
