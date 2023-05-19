package com.dev.chacha.presentation.send_money

import com.dev.chacha.domain.model.Contact

sealed class SendMoneyEvent {
    data class ContactSelected(val contact: Contact) : SendMoneyEvent()
    object ExpandSheet : SendMoneyEvent()
    object CollapseSheet : SendMoneyEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : SendMoneyEvent()
    data class AmountChanged(val amount: String) : SendMoneyEvent()
    data class ContinueButtonClicked(val phoneNumber: String, val amount: String) : SendMoneyEvent()
    data class RecipientProviderSelected(val recipient: RecipientProvider) : SendMoneyEvent()
}
