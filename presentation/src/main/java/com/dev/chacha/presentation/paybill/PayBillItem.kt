package com.dev.chacha.presentation.paybill

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PayBillItem(
    payBill: PayBill,
    onPayBillClick: (PayBill) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(onClick = { onPayBillClick(payBill) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (payBill.image != null) {
            AsyncImage(
                model = payBill.image,
                contentDescription = "profile_image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Unspecified)
                ,
                placeholder = null
            )

        } else {
            val names = payBill.name.split(" ")
            val initials = names[0].first().toString() + names[1].first().toString()
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Cyan),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp
                )

            }

        }
        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = payBill.name
            )
            Text(
                text = payBill.businessNumber
            )
        }

    }
}

@Composable
@Preview
fun PayBillItemPreview() {
    PayBillItem(
        payBill = PayBill(
            name = "John Doe",
            businessNumber = "1234567890",
            image = null
        )
    ) {}
}
