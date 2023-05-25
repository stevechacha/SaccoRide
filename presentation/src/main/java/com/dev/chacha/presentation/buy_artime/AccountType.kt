package com.dev.chacha.presentation.buy_artime

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.vector.ImageVector


enum class AccountType(val icon: ImageVector) {
    AccountA(Icons.Default.Phone),
    AccountB(Icons.Default.AttachMoney),
}