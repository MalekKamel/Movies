package com.movie.app.shared.paging

import androidx.paging.PageKeyedDataSource

data class InitialParams<K, V>(
        var initialLoadSize: Int = 0,
        var callback: PageKeyedDataSource.LoadInitialCallback<K, V>
)
