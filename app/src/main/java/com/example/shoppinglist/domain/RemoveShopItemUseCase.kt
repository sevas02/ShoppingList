package com.example.shoppinglist.domain

class RemoveShopItemUseCase(private val shopListRepo: ShopListRepo) {

    suspend fun removeItemById(id: Long) =
        shopListRepo.removeItemById(id)
}