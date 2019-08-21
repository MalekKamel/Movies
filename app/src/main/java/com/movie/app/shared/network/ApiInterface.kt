package com.movie.app.shared.network

import com.movie.app.BuildConfig
import com.movie.app.shared.data.model.MoviesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("search/movie")
    fun searchDoctors(
            @Query("query") query: String,
            @Query("page") page: Int,
            @Query("api_key") key: String = BuildConfig.THE_MOVIE_DB_API_TOKEN
    ): Flowable<MoviesResponse>


    @GET("movie/popular")
    fun popularMovies(
            @Query("page") page: Int,
            @Query("api_key") key: String = BuildConfig.THE_MOVIE_DB_API_TOKEN
    ): Flowable<MoviesResponse>
}