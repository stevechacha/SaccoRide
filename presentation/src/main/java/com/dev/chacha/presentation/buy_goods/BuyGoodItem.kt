package com.dev.chacha.presentation.buy_goods

data class BuyGoodItem(
    val name: String,
    val contact: String,
    val image: Int? = null
)

val buyItems = listOf(
    BuyGoodItem(
        name = "John Doe",
        contact = "0712345678",
        image = null,
        ),
    BuyGoodItem(
        name = "John Doe",
        contact = "0712345678",
        image = null,
    ),
)
