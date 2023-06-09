package com.dev.chacha.presentation.send_money

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.ContactUiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SendMoneyBottomSheetContent(
    coroutineScope: CoroutineScope,
    scaffoldState: BottomSheetScaffoldState,
    sendMoneyState: SendMoneyState,
    onPhoneNumberChanged: (String)->Unit,
    onAmountChanged:(String)-> Unit,
    contactListViewModel : ContactListViewModel,
    onRecipientProvider: (RecipientProvider)->Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SendMoneyRecipientDropDown(
            sendMoneyState = sendMoneyState ,
            onRecipientProvider = { newValue->
                onRecipientProvider(newValue)
            }
        )
        RideOutlinedTextField(
            value = sendMoneyState.phoneNumber,
            onValueChange = {phoneNumber->
                onPhoneNumberChanged(phoneNumber)
            },
            keyboardType = KeyboardType.Phone,
            hint = stringResource(id = R.string.phoneNumber),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Call",
                    tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                    modifier = Modifier.padding(8.dp).size(24.dp)
                        .clickable {
                                coroutineScope.launch {
                                    if (scaffoldState.bottomSheetState.isCollapsed) {
                                        contactListViewModel.send(ContactUiEvent.GetContacts, context)
                                        scaffoldState.bottomSheetState.expand()

                                    } else {
                                        scaffoldState.bottomSheetState.collapse()
                                    }
                                }

                        },
                )
            },
        )
        RideOutlinedTextField(
            value = sendMoneyState.amount,
            onValueChange = { amount->
                onAmountChanged(amount)
            },
            keyboardType = KeyboardType.Phone,
            hint = stringResource(id = R.string.amount),
            supportText = stringResource(id = R.string.amount_support_text),

            )

        Spacer(modifier = Modifier.height(20.dp))

        ContinueButton(
            text = stringResource(id = R.string.continuee),
            onClick = {},
            enable = sendMoneyState.phoneNumber.isNotBlank() && sendMoneyState.amount.isNotBlank()
        )
    }

}
