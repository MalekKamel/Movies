package com.movie.app.shared.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class AppPaging<V>(
        var pageSize: Int = 10,
        var appPageKeyedDataSource: AppPositionalDataSource<V>
) {

    fun dataSource(appPageKeyedDataSource: AppPositionalDataSource<V>): AppPaging<V> {
        return this
    }

    fun build(): LiveData<PagedList<V>> {
        return AppLivePagedListBuilder()
                .pageSize(pageSize)
                .build(AppKeyedDataSourceFactory(appPageKeyedDataSource))
    }

}
