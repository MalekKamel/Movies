package com.movie.app.shared.paging

import androidx.paging.PositionalDataSource

data class AfterParams<V>(
        var params: PositionalDataSource.LoadRangeParams,
        var callback: PositionalDataSource.LoadRangeCallback<V>
)