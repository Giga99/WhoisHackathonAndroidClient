package com.example.whoishakaton.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.whoishakaton.R
import com.example.whoishakaton.databinding.FavoriteDomainItemBinding
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.utils.getFormattedDateForMilliseconds
import com.example.whoishakaton.utils.view_binding.RecyclerViewHolderBinding

class FavoritesRecyclerViewAdapter(private val callback: (DomainInformationUIModel) -> Unit) :
    ListAdapter<DomainInformationUIModel, RecyclerViewHolderBinding<FavoriteDomainItemBinding>>(
        object :
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
        FavoriteDomainItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecyclerViewHolderBinding<FavoriteDomainItemBinding>,
        position: Int
    ) {
        val item = getItem(position)

        with(holder.viewBinding) {
            tvDomainName.text = item.name
            item.expirationDateInMiliseconds?.let {
                tvExpirationDate.text = holder.itemView.rootView.resources.getString(
                    R.string.domain_expires,
                    it.getFormattedDateForMilliseconds()
                )

                tvExpirationDate.visibility = View.VISIBLE
                tvAvailable.visibility = View.GONE
            } ?: kotlin.run {
                tvExpirationDate.visibility = View.GONE
                tvAvailable.visibility = View.VISIBLE
            }

            vHorizontalLineBottom.isVisible = position == currentList.size - 1

            holder.itemView.setOnClickListener {
                callback.invoke(item)
            }
        }
    }
}
