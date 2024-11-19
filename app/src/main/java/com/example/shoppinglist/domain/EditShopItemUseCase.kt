package com.example.shoppinglist.domain

class EditShopItemUseCase(private val shopListRepo: ShopListRepo) {

    fun editItem(item: ShopItem) =
        shopListRepo.editItem(item)

}