package com.dev.chacha.presentation.paybill.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dev.chacha.presentation.paybill.PayBill
import kotlin.random.Random

@Composable
fun PayBillItem(
    payBill: PayBill,
    onPayBillClick: (PayBill) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = { onPayBillClick(payBill) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (payBill.image != null) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(randomColor()),
                contentAlignment = Alignment.Center
            ){
                AsyncImage(
                    model = payBill.image,
                    contentDescription = "profile_image",
                    modifier = Modifier
                        .size(24.dp),
                    placeholder = null
                )
            }


        } else {
            val names = payBill.name.split(" ")
            val initials = names[0].first().toString() + names[1].first().toString()
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(randomColor()),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
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
                text = payBill.name,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Text(
                text = payBill.businessNumber,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)

            )
        }

    }
}

fun randomColor(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
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
