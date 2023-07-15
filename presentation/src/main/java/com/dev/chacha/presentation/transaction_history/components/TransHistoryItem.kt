package com.dev.chacha.presentation.transaction_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.dev.chacha.presentation.extensions.formatContact
import com.dev.chacha.presentation.extensions.formatDate
import com.dev.chacha.presentation.extensions.formatDateTime
import com.dev.chacha.presentation.extensions.getInitials
import com.dev.chacha.presentation.transaction_history.TransactionsItem
import com.dev.chacha.presentation.transaction_history.transactionsItem
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TransHistoryItem(
    transactionItem: TransactionsItem,
    onTransactionClick: (TransactionsItem) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
    ) {
        val (profileImage, nameText, amountText, contactText, dateTimeText) = createRefs()

        if (transactionItem.image != null) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08F))
                    .constrainAs(profileImage) {
                        start.linkTo(parent.start)
                        centerVerticallyTo(parent)
                    },
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = transactionItem.image,
                    contentDescription = "profile_image",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(shape = CircleShape),
                    placeholder = null
                )
            }
        } else {
            val transactionInitials = transactionItem.name
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08F))
                    .constrainAs(profileImage) {
                        start.linkTo(parent.start)
                        centerVerticallyTo(parent)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getInitials(transactionInitials),
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    style = typography.headlineSmall
                )
            }
        }

        Text(
            text = transactionItem.name,
            style = typography.labelSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .constrainAs(nameText) {
                    start.linkTo(profileImage.end, margin = 10.dp)
                    top.linkTo(amountText.top)
                }
        )

        Text(
            text = "KSH${transactionItem.amount}",
            textAlign = TextAlign.End,
            style = typography.labelSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(amountText) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        )

        val formattedContact = formatContact(transactionItem.contact)
        Text(
            text = formattedContact,
            style = typography.labelSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .constrainAs(contactText) {
                    start.linkTo(nameText.start)
                    top.linkTo(nameText.bottom)
                }
        )

        Text(
            text = "${formatDate(transactionItem.date)}, ${transactionItem.time}",
            textAlign = TextAlign.End,
            style = typography.labelSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(dateTimeText) {
                    end.linkTo(amountText.end)
                    top.linkTo(amountText.bottom)
                }
        )
    }
}








@Composable
@Preview
fun TransHistoryItemPreview() {
    LazyColumn {
        items(transactionsItem.size) { index ->
            TransHistoryItem(
                transactionItem = transactionsItem[index],
                onTransactionClick = {}
            )
        }
    }


}


