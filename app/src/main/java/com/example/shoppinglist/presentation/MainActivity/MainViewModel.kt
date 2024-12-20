package com.example.shoppinglist.presentation.MainActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.ShopListRepoImpl
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.RemoveShopItemUseCase
import com.example.shoppinglist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repo = ShopListRepoImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repo)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repo)
    private val editShopItemUseCase = EditShopItemUseCase(repo)

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(item: ShopItem) {
        viewModelScope.launch {
            removeShopItemUseCase.removeItemById(item.id)
        }
    }

    fun changeEnableItemState(item: ShopItem) {
        viewModelScope.launch {
            val editedItem = item.copy(enable = !item.enable)
            editShopItemUseCase.editItem(editedItem)
        }
    }
}