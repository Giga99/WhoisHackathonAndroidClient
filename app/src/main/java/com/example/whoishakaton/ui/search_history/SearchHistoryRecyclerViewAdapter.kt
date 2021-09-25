package com.example.whoishakaton.ui.search_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.whoishakaton.databinding.SearchHistoryItemBinding
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.utils.getFormattedDateForMilliseconds
import com.example.whoishakaton.utils.view_binding.RecyclerViewHolderBinding

class SearchHistoryRecyclerViewAdapter(private val callback: (DomainHistoryUIModel) -> Unit) :
    ListAdapter<DomainHistoryUIModel, RecyclerViewHolderBinding<SearchHistoryItemBinding>>(object :
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
        SearchHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecyclerViewHolderBinding<SearchHistoryItemBinding>,
        position: Int
    ) {
        val item = getItem(position)

        with(holder.viewBinding) {
            tvDomainName.text = item.title
            tvSearchDate.text = getFormattedDateForMilliseconds(item.date)

            vHorizontalLineBottom.isVisible = position == currentList.size - 1

            holder.itemView.setOnClickListener {
                callback.invoke(item)
            }
        }
    }
}
