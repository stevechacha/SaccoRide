package com.dev.chacha.presentation.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.util.prettyYearMonth
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun MonthHeader(modifier: Modifier = Modifier, yearMonth: YearMonth) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 24.dp).weight(1f),
            text = prettyYearMonth(yearMonth),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PreviewMonthHeader() {
        MonthHeader(
            yearMonth = YearMonth.from(LocalDate.now().withDayOfMonth(1)),
        )

}