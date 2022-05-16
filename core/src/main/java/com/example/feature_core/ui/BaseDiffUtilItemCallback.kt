package com.example.feature_core.ui

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<DisplayableItem>() {

    override fun areItemsTheSame(
        oldItem: DisplayableItem,
        newItem: DisplayableItem
    ) = oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(
        oldItem: DisplayableItem,
        newItem: DisplayableItem
    ) = oldItem.hashCode() == newItem.hashCode()

}