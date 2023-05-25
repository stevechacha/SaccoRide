package com.dev.chacha.presentation.buy_artime

sealed class BuyAirtimeEvent{
    data class PhoneNumberChanged(val phoneNumber: String): BuyAirtimeEvent()
    data class AmountChanged(val amount: String): BuyAirtimeEvent()
    object BuyAirtimeClicked: BuyAirtimeEvent()
    data class TargetRadionButton(val targetRadio: String): BuyAirtimeEvent()
//    data class AccountType(val accountType: AccountType?): BuyAirtimeEvent()
}
