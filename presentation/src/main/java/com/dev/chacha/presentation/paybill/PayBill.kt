package com.dev.chacha.presentation.paybill

import com.dev.chacha.presentation.R

data class PayBill(
    val name: String,
    val businessNumber: String,
    val accountNumber: String? = null,
    var amount: Double?=null,
    val date: String?=null,
    val image: Int? = null,

    )
val payBillItems = listOf(
    PayBill(
        name = "Equity Paybill Account",
        businessNumber = "247247",
        image = R.drawable.home_icon,
    ),
    PayBill(
        name = "KCB Paybill Account",
        businessNumber = "522522",
        image = null,
    ),
    PayBill(
        name = "Co-operative Bank Collection Account",
        businessNumber = "400222",
        image = null,
    ),
    PayBill(
        name = "KPLC PREPAID",
        businessNumber = "888880",
        image = null,
    ),
    PayBill(
        name = "ENA INVESTMENTS LIMITED",
        businessNumber = "181893",
        image = null,
    ),

)
