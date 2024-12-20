package com.example.shoppinglist.domain

class GetShopItemByIdUseCase(private val shopListRepo: ShopListRepo) {

    suspend fun getItemById(id: Long): ShopItem =
        shopListRepo.getItemById(id)
}