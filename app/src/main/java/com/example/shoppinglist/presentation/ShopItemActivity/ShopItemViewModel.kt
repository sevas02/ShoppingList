package com.example.shoppinglist.presentation.ShopItemActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.ShopListRepoImpl
import com.example.shoppinglist.domain.AddShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopItemByIdUseCase
import com.example.shoppinglist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ShopListRepoImpl(application)

    private val editShopItemUseCase = EditShopItemUseCase(repo)
    private val addShopItemUseCase = AddShopItemUseCase(repo)
    private val getShopItemUseCase = GetShopItemByIdUseCase(repo)

    //LiveData errors to show error messages in Activity
    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName
    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    //LiveData to get shop item
    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    //LiveData to close Activity, when edit or add be finished
    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (isValidateInput(name, count)) {
            _shopItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name, count = count)
                    editShopItemUseCase.editItem(item)
                    _shouldCloseScreen.postValue(Unit)
                }
            }
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (isValidateInput(name, count)) {
            viewModelScope.launch {
                addShopItemUseCase.addItem(ShopItem(name = name, count = count))
                _shouldCloseScreen.postValue(Unit)
            }
        }
    }

    fun getShopItemById(id: Long) {
        viewModelScope.launch {
            _shopItem.postValue(getShopItemUseCase.getItemById(id))
        }
    }

    private fun parseName(input: String?): String {
        return input?.trim() ?: ""
    }

    private fun parseCount(input: String?): Int {
        return input?.trim()?.toIntOrNull() ?: 0
    }

    private fun isValidateInput(name: String, count: Int): Boolean {
        var res = true
        if (name.isBlank()) {
            _errorInputName.postValue(true)
            res = false
        }
        if (count <= 0) {
            _errorInputCount.postValue(true)
            res = false
        }
        return res
    }

    fun resetErrorInputName() {
        _errorInputName.postValue(false)
    }

    fun resetErrorInputCount() {
        _errorInputCount.postValue(false)
    }

}