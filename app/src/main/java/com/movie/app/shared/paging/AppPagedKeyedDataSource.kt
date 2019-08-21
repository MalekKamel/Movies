package com.movie.app.shared.paging

import androidx.paging.PageKeyedDataSource

class AppPagedKeyedDataSource<K, V> : PageKeyedDataSource<K, V>() {
    private var loadInitialCallback: ((InitialParams<K, V>) -> Unit)? = null
    private var loadAfterCallback: ((AfterParams<K, V>) -> Unit)? = null
    private var initialLoadSize = 20

    override fun loadInitial(params: LoadInitialParams<K>, callback: LoadInitialCallback<K, V>) {
        if (!isInvalid && loadInitialCallback != null)
            loadInitialCallback!!(
                    InitialParams(initialLoadSize = initialLoadSize, callback = callback)
            )
    }

    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<K, V>) {
        if (!isInvalid && loadAfterCallback != null && params.key != null && params.key.toString() != "")
            loadAfterCallback!!(
                    AfterParams(callback = callback, params = params)
            )
    }

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<K, V>) {}

    fun loadInitialCallback(loadInitialCallback: (InitialParams<K, V>) -> Unit): AppPagedKeyedDataSource<K, V> {
        this.loadInitialCallback = loadInitialCallback
        return this
    }

    fun loadAfterCallback(loadAfterCallback: (AfterParams<K, V>) -> Unit): AppPagedKeyedDataSource<K, V> {
        this.loadAfterCallback = loadAfterCallback
        return this
    }


}
