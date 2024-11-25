package com.example.shoppinglist.presentation.MainActivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter: ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()
        holder.view.setOnLongClickListener{
            onShopItemLongClickListener?.invoke(item)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enable) {
            VIEW_TYPE_ENABLED
        }
        else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 10
    }
}