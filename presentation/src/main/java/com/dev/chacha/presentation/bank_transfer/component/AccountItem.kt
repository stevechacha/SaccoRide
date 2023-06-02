package com.dev.chacha.presentation.bank_transfer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dev.chacha.presentation.bank_transfer.AccountList
import com.dev.chacha.presentation.extensions.getInitials
import com.dev.chacha.presentation.paybill.component.randomColor

@Composable
fun AccountItem(accountList: AccountList,onAccountItemClicked:()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = { onAccountItemClicked() }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (accountList.accountLogo != null) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(randomColor()),
                contentAlignment = Alignment.Center
            ){
                AsyncImage(
                    model = accountList.accountLogo,
                    contentDescription = "profile_image",
                    modifier = Modifier
                        .size(24.dp),
                    placeholder = null
                )
            }


        } else {
            val accountInitials = accountList.accountName
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(randomColor()),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getInitials(accountInitials),
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

            }

        }
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = accountList.accountName,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Text(
                text = accountList.accountDescription,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)

            )
        }


    }

}