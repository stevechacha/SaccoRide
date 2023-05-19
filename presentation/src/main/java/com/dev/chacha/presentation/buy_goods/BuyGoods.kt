package com.dev.chacha.presentation.buy_goods

data class BuyGoods(
    val tillName: String,
    val tillNumber: String,
    val amount: Double,
    val date: String? = null
)

fun getTillNumber():List<BuyGoods> = listOf(
    BuyGoods(
        "Mwambuka Nsoto",
        "123456",
        200.00,
        "12 June, 2022"
    ),
    BuyGoods(
        "Mwambuka Nsoto",
        "123456",
        200.00,
        "12 June, 2022"
    ),
    BuyGoods(
        "Mwambuka",
        "123456",
        200.00,
        "12 June, 2022"
    ),
)


