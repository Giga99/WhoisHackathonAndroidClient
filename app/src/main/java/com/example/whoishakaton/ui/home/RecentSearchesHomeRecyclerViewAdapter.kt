package com.example.whoishakaton.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.whoishakaton.databinding.RecentSearchHomeItemBinding
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.utils.getFormattedDateForMilliseconds
import com.example.whoishakaton.utils.view_binding.RecyclerViewHolderBinding

class RecentSearchesHomeRecyclerViewAdapter(private val callback: (DomainHistoryUIModel) -> Unit) :
    ListAdapter<DomainHistoryUIModel, RecyclerViewHolderBinding<RecentSearchHomeItemBinding>>(object :
        DiffUtil.ItemCallback<DomainHistoryUIModel>() {

        override fun areItemsTheSame(oldItem: DomainHistoryUIModel, newItem: DomainHistoryUIModel) =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: DomainHistoryUIModel,
            newItem: DomainHistoryUIModel
        ) = oldItem == newItem
    }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecyclerViewHolderBinding(
        RecentSearchHomeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecyclerViewHolderBinding<RecentSearchHomeItemBinding>,
        position: Int
    ) {
        val item = getItem(position)

        with(holder.viewBinding) {
            tvDomainName.text = item.title
            tvSearchDate.text = item.date.getFormattedDateForMilliseconds()

            vHorizontalLine.isVisible = position != currentList.size - 1

            holder.itemView.setOnClickListener {
                callback.invoke(item)
            }
        }
    }
}
