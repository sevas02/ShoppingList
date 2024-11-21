package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepo {

    fun addItem(item: ShopItem)

    fun editItem(item: ShopItem)

    fun getItemById(id: Long): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

    fun removeItemById(id: Long)
}