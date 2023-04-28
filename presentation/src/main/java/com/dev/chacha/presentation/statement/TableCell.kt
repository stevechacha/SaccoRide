package com.dev.chacha.presentation.statement

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.common.theme.spacing

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    heading: Boolean = false,
    alignment: TextAlign = TextAlign.Left
) {
    val spacing = MaterialTheme.spacing
    Box(
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .height(spacing.invoiceRowHeight)
            .weight(weight)
            .padding(spacing.small),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = if (heading) FontWeight.Bold else FontWeight.Normal,
            textAlign = alignment,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TableCellPreviewLight() {
    Row {
        TableCell(text = "Item 1", weight = 1f)
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TableCellPreviewDark() {
    Row {
        TableCell(text = "Item 1", weight = 1f)
    }

}