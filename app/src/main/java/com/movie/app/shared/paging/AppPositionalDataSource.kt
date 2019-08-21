package com.movie.app.shared.paging

import androidx.paging.PositionalDataSource

class AppPositionalDataSource<V>: PositionalDataSource<V>() {

    private var loadInitialCallback: ((InitialParams<V>) -> Unit)? = null
    private var loadAfterCallback: ((AfterParams<V>) -> Unit)? = null

    override fun loadInitial(
            params: LoadInitialParams, 
            callback: LoadInitialCallback<V>
    ) {
        if (!isInvalid && loadInitialCallback != null)
            loadInitialCallback!!(InitialParams(callback = callback))
    }

    override fun loadRange(
            params: LoadRangeParams, 
            callback: LoadRangeCallback<V>
    ) {
        if (!isInvalid && loadAfterCallback != null)
            loadAfterCallback!!(AfterParams(callback = callback, params = params))
    }

    fun loadInitialCallback(loadInitialCallback: (InitialParams<V>) -> Unit): AppPositionalDataSource<V> {
        this.loadInitialCallback = loadInitialCallback
        return this
    }

    fun loadAfterCallback(loadAfterCallback: (AfterParams<V>) -> Unit): AppPositionalDataSource<V> {
        this.loadAfterCallback = loadAfterCallback
        return this
    }


}
