package com.example.shoppinglist.domain

data class ShopItem(
    val id: Long,
    val name: String,
    val count: Int,
    val enable: Boolean
)
