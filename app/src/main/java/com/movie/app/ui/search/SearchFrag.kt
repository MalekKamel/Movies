package com.movie.app.ui.search

import androidx.lifecycle.Observer
import com.jakewharton.rxbinding2.widget.RxTextView
import com.movie.app.R
import com.movie.app.shared.data.model.MoviesRequest
import com.movie.app.shared.rx.disposeBy
import com.movie.app.shared.ui.frag.BaseFrag
import com.movie.app.shared.util.ThreadUtil
import com.movie.app.shared.util.linearLayoutManager
import com.movie.app.ui.search.adapter.MoviesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.frag_search.*
import kotlinx.android.synthetic.main.include_recycler_view_refreshable.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class SearchFrag : BaseFrag<SearchVm>() {

    override val vm: SearchVm by viewModel()
    override var layoutId: Int = R.layout.frag_search

    private lateinit var adapter: MoviesAdapter

    override fun setupUi() {
        super.setupUi()
        rv.linearLayoutManager(context)
        setupAdapter()
        setupSearch()
        loadMovies(MoviesRequest(type = MoviesRequest.Type.POPULAR))
    }

    private fun setupAdapter() {
        adapter = MoviesAdapter(this)
    }

    private fun setupSearch() {
        RxTextView.textChanges(etSearch)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map { it.toString() }
                .filter { vm.isValidSearchString(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { loadMovies(MoviesRequest(search = it)) }
                .disposeBy(vm.disposables)

    }

    private fun loadMovies(request: MoviesRequest) {
        vm.loadMovies(request)
                .observe(
                        this,
                        Observer { list ->
                            rv.adapter = adapter
                            adapter.submitList(list)
                        })
    }

    override fun showLoadingDialog() {
        ThreadUtil.runOnUiThread { swipeRefresh.isRefreshing = true }
    }

    override fun hideLoading() {
        ThreadUtil.runOnUiThread { swipeRefresh.isRefreshing = false }
    }


}
