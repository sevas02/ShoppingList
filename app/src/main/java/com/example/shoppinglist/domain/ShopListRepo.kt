package com.example.shoppinglist.domain

interface ShopListRepo {

    fun addItem(item: ShopItem)

    fun editItem(item: ShopItem)

    fun getItemById(id: Long): ShopItem

    fun getShopList(): List<ShopItem>

    fun removeItemById(id: Long)
}