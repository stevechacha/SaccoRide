package com.dev.chacha.presentation.send_money

import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi

@OptIn(ExperimentalMaterialApi::class)
data class SendMoneyState(
    val sheetState: BottomSheetState,
    val expanded: Boolean,
    val phoneNumber: String,
    val amount: String,
    val selectedRecipient: RecipientProvider
)
