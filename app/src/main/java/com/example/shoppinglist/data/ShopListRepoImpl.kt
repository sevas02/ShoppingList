package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepo
import kotlin.random.Random

object ShopListRepoImpl: ShopListRepo {

    private val _items = sortedSetOf<ShopItem>(
        {o1, o2-> o1.id.compareTo(o2.id)})
    val items
        get() = _items.toList()

    private var lastId = 0L
    private val itemsLD = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0..1000){
            addItem((ShopItem("$i", Random.nextInt(0,5), enable = Random.nextBoolean())))
        }
    }

    override fun getShopList() = itemsLD

    override fun getItemById(id: Long): ShopItem {
        _items.find { it.id == id }?.let { return it } ?:
        throw IllegalArgumentException("Error: $id is not founded")
    }

    override fun addItem(item: ShopItem) {
        with(item){
            if (id == ShopItem.UNDEFINED_ID)
                id = lastId++
            _items.add(this)
        }
        updateLD()
    }

    override fun editItem(item: ShopItem) {
        _items.find { it.id == item.id }?.also {
            removeItemById(it.id)
            addItem(item)
        }
    }

    override fun removeItemById(id: Long) {
        _items.removeIf { it.id == id }
        updateLD()
    }

    private fun updateLD() {
        itemsLD.value = items
    }
}