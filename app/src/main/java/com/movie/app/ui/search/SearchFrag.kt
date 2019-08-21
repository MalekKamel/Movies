package com.movie.app.ui.search

import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.movie.app.R
import com.movie.app.shared.data.model.MoviesRequest
import com.movie.app.shared.ui.frag.BaseFrag
import com.movie.app.shared.util.ThreadUtil
import com.movie.app.shared.util.linearLayoutManager
import com.movie.app.shared.util.textString
import com.movie.app.ui.search.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.frag_search.*
import kotlinx.android.synthetic.main.include_recycler_view_refreshable.*
import org.koin.android.viewmodel.ext.android.viewModel


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

        btnSearch.setOnClickListener {
            if (!vm.isValidSearchString(etSearch.textString()))
                return@setOnClickListener

            loadMovies(MoviesRequest(search = etSearch.textString()))
        }

        val arrayAdapter = ArrayAdapter(
                context!!,
                android.R.layout.select_dialog_item,
                vm.allMovies
        )
        etSearch.threshold = 1
        etSearch.setAdapter(arrayAdapter)
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
