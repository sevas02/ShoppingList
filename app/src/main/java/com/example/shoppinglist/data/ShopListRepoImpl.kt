package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepo

object ShopListRepoImpl: ShopListRepo {

    private val _items = mutableListOf<ShopItem>()
    val items
        get() = _items.toList()

    private var lastId = 0L

    override fun addItem(item: ShopItem) {
        with(item){
            if (id == ShopItem.UNDEFINED_ID)
                id = lastId++
            _items.add(this)
        }
    }

    override fun editItem(item: ShopItem) {
        _items.find { it.id == item.id }?.also {
            removeItemById(it.id)
            addItem(item)
        }
    }

    override fun getShopList() = items

    override fun getItemById(id: Long): ShopItem {
        _items.find { it.id == id }?.let { return it } ?:
        throw IllegalArgumentException("Error: $id is not founded")
    }

    override fun removeItemById(id: Long) {
        _items.removeIf { it.id == id }
    }
}