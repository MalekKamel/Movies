package com.movie.app.shared.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("page")
    var page: Int = 0,

    @SerializedName("results")
    var results: MutableList<Movie>?,

    @SerializedName("total_results")
    var totalResults: Int = 0,

    @SerializedName("total_pages")
    var totalPages: Int = 0

)
