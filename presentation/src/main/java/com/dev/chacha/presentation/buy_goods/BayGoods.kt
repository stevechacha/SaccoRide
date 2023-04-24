package com.dev.chacha.presentation.buy_goods

data class BayGoods(
    val tillName: String,
    val tillNumber: String,
    val amount: Double,
    val date: String? = null
)
