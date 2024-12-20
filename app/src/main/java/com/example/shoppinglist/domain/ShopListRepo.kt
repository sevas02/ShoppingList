package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepo {

    suspend fun addItem(item: ShopItem)

    suspend fun editItem(item: ShopItem)

    suspend fun getItemById(id: Long): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

    suspend fun removeItemById(id: Long)
}