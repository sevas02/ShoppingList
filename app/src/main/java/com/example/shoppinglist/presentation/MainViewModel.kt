package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepoImpl
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.RemoveShopItemUseCase
import com.example.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repo = ShopListRepoImpl

    private val getShopListUseCase = GetShopListUseCase(repo)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repo)
    private val editShopItemUseCase = EditShopItemUseCase(repo)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        shopList.postValue(getShopListUseCase.getShopList())
    }

    fun RemoveShopItem(item: ShopItem) {
        removeShopItemUseCase.removeItemById(item.id)
        getShopList()
    }

    fun changeEnableItemState(item: ShopItem) {
        val editedItem = item.copy(enable = !item.enable)
        editShopItemUseCase.editItem(editedItem)
        getShopList()
    }

}