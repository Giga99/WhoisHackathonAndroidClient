package com.example.whoishakaton.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.whoishakaton.databinding.MostSearchedItemBinding
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.utils.view_binding.RecyclerViewHolderBinding

class PopularDomainsRecyclerViewAdapter(private val callback: (DomainInformationUIModel) -> Unit) :
    ListAdapter<DomainInformationUIModel, RecyclerViewHolderBinding<MostSearchedItemBinding>>(object :
        DiffUtil.ItemCallback<DomainInformationUIModel>() {

        override fun areItemsTheSame(
            oldItem: DomainInformationUIModel,
            newItem: DomainInformationUIModel
        ) =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: DomainInformationUIModel,
            newItem: DomainInformationUIModel
        ) = oldItem == newItem
    }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecyclerViewHolderBinding(
        MostSearchedItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecyclerViewHolderBinding<MostSearchedItemBinding>,
        position: Int
    ) {
        val item = getItem(position)

        with(holder.viewBinding) {
            tvDomainName.text = item.name

            holder.itemView.setOnClickListener {
                callback.invoke(item)
            }
        }
    }
}
