package com.movie.app.shared.paging

import androidx.paging.PageKeyedDataSource

data class AfterParams<K, V>(
        var callback: PageKeyedDataSource.LoadCallback<K, V>,
        var params: PageKeyedDataSource.LoadParams<K>
)