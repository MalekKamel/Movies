package com.movie.app.ui.search

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.annimon.stream.Stream
import com.movie.app.shared.data.DataManager
import com.movie.app.shared.data.model.Movie
import com.movie.app.shared.data.model.MoviesRequest
import com.movie.app.shared.paging.AppPaging
import com.movie.app.shared.paging.AppPositionalDataSource
import com.movie.app.shared.rx.FlowableUtil
import com.movie.app.shared.rx.disposeBy
import com.movie.app.shared.vm.BaseViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

val searchModule = module {
    viewModel { SearchVm(get()) }
}

class SearchVm(dataManager: DataManager) : BaseViewModel(dataManager) {

    fun isValidSearchString(query: String): Boolean {
        return query.isNotEmpty()
    }

    fun loadMovies(request: MoviesRequest): LiveData<PagedList<Movie>> {
        val dataSource = AppPositionalDataSource<Movie>()
                .loadInitialCallback { params ->
                    loadMovies(request.nextPage(1)) {
                        params.callback.onResult(it, 0)
                    }
                }
                .loadAfterCallback { params ->
                    loadMovies(request.nextPage(params.params.startPosition)) {
                        params.callback.onResult(it)
                    }
                }

        return AppPaging(appPageKeyedDataSource = dataSource)
                .dataSource(dataSource)
                .build()
    }

    private fun loadMovies(request: MoviesRequest, callback: (MutableList<Movie>) -> Unit) {
        loading(true)

        val moviesApi = when(request.type) {
            MoviesRequest.Type.SEARCH -> api.searchMovies(request)
            MoviesRequest.Type.POPULAR -> api.popularMovies(request)
        }

        moviesApi
                .compose(FlowableUtil.handleException(rxError { loadMovies(request, callback) }))
                .subscribe { response ->

                    if (response.results.isNullOrEmpty()) {
                        callback(ArrayList())
                        loading(false)
                        return@subscribe
                    }

                    Stream.of(response.results).forEach { item -> item.latPage = response.page }
                    callback(response.results!!)
                    loading(false)
                }.disposeBy(disposables)

    }

}

