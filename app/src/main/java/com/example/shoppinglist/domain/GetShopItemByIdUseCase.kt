package com.example.shoppinglist.domain

class GetShopItemByIdUseCase(private val shopListRepo: ShopListRepo) {

    fun getItemById(id: Long): ShopItem =
        shopListRepo.getItemById(id)
}