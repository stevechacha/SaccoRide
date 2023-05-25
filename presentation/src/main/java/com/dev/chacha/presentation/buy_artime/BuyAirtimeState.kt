package com.dev.chacha.presentation.buy_artime

data class BuyAirtimeState(
    var targetRadio: String = "myself",
    val phoneNumber: String = "",
    val amount: String = "",
    var isExpanded: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = ""
) {
    private val isAmountValid: Boolean
        get() = amount.replace("-", "").trim().isNotEmpty() && amount.trim().toIntOrNull() != null && amount.trim().toInt() in 6..9999
    private val areDetailsFilled: Boolean
        get() = when (targetRadio) {
            "myself" -> true
            "someone_else" -> phoneNumber.replace("-", "").trim().isNotEmpty()
            else -> false
        }
    val isBuyAirtimeEnabled = isAmountValid && areDetailsFilled
}

