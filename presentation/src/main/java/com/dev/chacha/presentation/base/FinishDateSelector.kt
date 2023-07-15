package com.dev.chacha.presentation.base

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.calendar.model.CalendarState
import com.dev.chacha.presentation.calendar.model.selectedDatesFormatted
import com.dev.chacha.presentation.util.toDate
import com.dev.chacha.presentation.util.toLocalDate
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.calendar.Calendar
import java.time.LocalDate
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FinishDateSelector(
    selectDate: Date? = null,
    onBackPressed: () -> Unit,
    onApply: (finishDate: Date) -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val calendarState = remember { CalendarState(selectDate) }

        LaunchedEffect(Unit) {
            if (selectDate !== null) calendarState.setSelectedDay(selectDate.toLocalDate())
        }

        FinishDateSelectorContent(
            calendarState = calendarState,
            onDayClicked = { calendarState.setSelectedDay(it) },
            onBackPressed = onBackPressed,
            onApply = { onApply(calendarState.calendarUiState.value.selectedEndDate!!.toDate()) }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun FinishDateSelectorContent(
    calendarState: CalendarState,
    onDayClicked: (LocalDate) -> Unit,
    onBackPressed: () -> Unit,
    onApply: () -> Unit,
) {
    Column {
        FinishDateSelectorTopAppBar(calendarState, onBackPressed, onApply)
        Calendar(
            calendarState = calendarState,
            onDayClicked = onDayClicked,
        )
    }
}
@RequiresApi(Build.VERSION_CODES.O)

@Composable
private fun FinishDateSelectorTopAppBar(
    calendarState: CalendarState,
    onBackPressed: () -> Unit,
    onApply: () -> Unit,
) {
    Surface {
        Column() {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                IconButton(
                    onClick = { onBackPressed() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
                Spacer(Modifier.weight(1F))
                Button(
                    onClick = { onApply() },
                    contentPadding = ButtonDefaults.ContentPadding,
                    enabled = calendarState.calendarUiState.value.hasSelectedDates,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)

                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(text = stringResource(R.string.apply))
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 24.dp,
                    )
            ) {
                Text(
                    text = if (!calendarState.calendarUiState.value.hasSelectedDates) {
                        stringResource(R.string.select_finish_date_title)
                    } else {
                        selectedDatesFormatted(calendarState)
                    },
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Default")
@Composable
private fun PreviewDefault(){
        FinishDateSelector(onBackPressed = {}, onApply = {})
}

@Preview(name = "Night mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
@RequiresApi(Build.VERSION_CODES.O)
private fun PreviewNightMode(){
        FinishDateSelector(onBackPressed = {}, onApply = {})

}