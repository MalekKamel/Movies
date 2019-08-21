package com.movie.app.shared.paging

import androidx.paging.PositionalDataSource

data class InitialParams<V>(
        var callback: PositionalDataSource.LoadInitialCallback<V>
)
