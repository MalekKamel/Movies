package com.movie.app.shared.ui.adapter

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.movie.app.shared.ui.view.BaseView
import com.movie.app.shared.util.CrashlyticsUtil


abstract class BasePagedListAdapter<T> constructor(protected var view: BaseView, diffCallback: DiffUtil.ItemCallback<T>)
    : PagedListAdapter<T, BaseViewHolder<T>>(diffCallback) {


    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        try {
            val item = getItem(position)
            if( item != null) holder.bindView(item)
        } catch (e: Exception) {
            e.printStackTrace()
            CrashlyticsUtil.log(e)
        }

    }


}
