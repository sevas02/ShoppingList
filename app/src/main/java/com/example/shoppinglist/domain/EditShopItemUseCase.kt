package com.example.shoppinglist.domain

class EditShopItemUseCase(private val shopListRepo: ShopListRepo) {

    suspend fun editItem(item: ShopItem) =
        shopListRepo.editItem(item)

}