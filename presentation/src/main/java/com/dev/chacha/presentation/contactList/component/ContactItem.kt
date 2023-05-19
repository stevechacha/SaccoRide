package com.dev.chacha.presentation.contactList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.contactList.Contact
import com.dev.chacha.presentation.paybill.component.randomColor
import java.util.Locale

@Composable
fun ContactItem(contact: Contact, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(top=5.dp , bottom = 5.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            val names = contact.name.split(" ")
            val initials = (if (names.size >= 2) {
                names[0].trim().first().toString().trim() + names[1].trim().first().toString().trim()
            } else {
                names[0].trim().first().toString().trim()
            }).uppercase()

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(randomColor()),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onItemClick)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = contact.name,
                )
                Text(
                    text = contact.phoneNumber,
                )
            }

        }

        Divider(
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp, top = 5.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08F)
        )

    }


}
