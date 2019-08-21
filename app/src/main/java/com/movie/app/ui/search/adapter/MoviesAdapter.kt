package com.movie.app.ui.search.adapter

import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import com.movie.app.R
import com.movie.app.shared.data.model.Movie
import com.movie.app.shared.ui.adapter.BasePagedListAdapter
import com.movie.app.shared.ui.adapter.BaseViewHolder
import com.movie.app.shared.ui.view.BaseView
import com.movie.app.shared.util.picasso.PicassoUtil
import com.movie.app.shared.vm.ViewModelProvidersUtil
import com.movie.app.ui.detail.MovieDetailVm
import kotlinx.android.synthetic.main.item_movie.view.*


/**
 * Created by Sha on 4/20/17.
 */

class MoviesAdapter(baseView: BaseView) : BasePagedListAdapter<Movie>(
        baseView,
        object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        return Vh(parent)
    }

    inner class Vh internal constructor(viewGroup: ViewGroup)
        : BaseViewHolder<Movie>(viewGroup, R.layout.item_movie) {

        init {
            itemView.setOnClickListener {
                val vm = ViewModelProvidersUtil.get(MovieDetailVm::class.java, view.activity()!!)
                vm.movie = getItem(adapterPosition)!!
                Navigation.findNavController(view.fragment().view!!)
                        .navigate(R.id.action_searchFrag_to_moviewDetailFrag)
            }
        }

        override fun bindView(item: Movie) {
            itemView.tvTitle.text = item.title
            itemView.tvRate.text = item.voteAverage.toString()
            PicassoUtil.load(
                    iv =itemView.ivPoster,
                    url = item.poster()
            )
        }

    }
}
