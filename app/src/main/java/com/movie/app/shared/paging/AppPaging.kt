package com.movie.app.shared.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class AppPaging<K, V>(
        var pageSize: Int = 10,
        var dataSource: AppItemKeyedDataSource<K, V>
) {

    fun build(): LiveData<PagedList<V>> {
        return AppLivePagedListBuilder()
                .pageSize(pageSize)
                .build(AppKeyedDataSourceFactory(dataSource))
    }

}
