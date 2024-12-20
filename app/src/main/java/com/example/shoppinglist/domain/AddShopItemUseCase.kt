package com.example.shoppinglist.domain

class AddShopItemUseCase(private val shopListRepo: ShopListRepo) {

    suspend fun addItem(item: ShopItem) =
        shopListRepo.addItem(item)

}