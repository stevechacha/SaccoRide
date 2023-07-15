package com.dev.chacha.presentation.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dev.chacha.presentation.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.ceil

@Suppress("Build.VERSION_CODES.O")
const val DAY = 24 * 60 * 60 * 1000

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toDate(): Date = Date(this.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000)

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toDate(): Date = Date(this.toEpochSecond(
    ZoneId.systemDefault().rules.getOffset(this)
) * 1000)

@RequiresApi(Build.VERSION_CODES.O)
fun Date.toLocalDate(): LocalDate = this.toInstant()
    .atZone(ZoneId.systemDefault())
    .toLocalDate()

@RequiresApi(Build.VERSION_CODES.O)
fun Date.toLocalDateTime(): LocalDateTime = this.toInstant()
    .atZone(ZoneId.systemDefault())
    .toLocalDateTime()

@RequiresApi(Build.VERSION_CODES.O)
fun countDays(toDate: Date, fromDate: Date = Date()): Int {
    val fromDateRound = roundToDay(fromDate)
    val toDateRound = roundToDay(toDate)

    val daysFrom = ceil(fromDateRound.time / DAY.toDouble()).toInt()
    val daysTo = ceil(toDateRound.time / DAY.toDouble()).toInt()

    return daysTo - daysFrom + 1
}

@RequiresApi(Build.VERSION_CODES.O)
fun isSameDay(timestampA: Long, timestampB: Long): Boolean {
    return roundToDay(Date(timestampA)) == roundToDay(Date(timestampB))
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun prettyDate(
    date: Date,
    showTime: Boolean = true,
    forceShowDate: Boolean = false,
    forceHideDate: Boolean = false,
    forceShowYear: Boolean = false,
    human: Boolean = false,
    shortMonth: Boolean = false,
): String {
    val yearOnlyFormatter = DateTimeFormatter.ofPattern("yyyy")
    val dateWithMonthAndYearFormatter = if (shortMonth) {
        DateTimeFormatter.ofPattern("dd MMM yyyy")
    } else {
        DateTimeFormatter.ofPattern("dd MMMM yyyy")
    }
    val dateWithMonthFormatter = if (shortMonth) {
        DateTimeFormatter.ofPattern("dd MMM")
    } else {
        DateTimeFormatter.ofPattern("dd MMMM")
    }
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val currentFullDate = dateWithMonthAndYearFormatter.format(LocalDate.now())
    val currentYear = yearOnlyFormatter.format(LocalDate.now())

    val convertedFullDate = dateWithMonthAndYearFormatter.format(date.toLocalDate())
    val convertedYear = yearOnlyFormatter.format(date.toLocalDate())
    val convertedDate = dateWithMonthFormatter.format(date.toLocalDate())
    val convertedTime = timeFormatter.format(date.toLocalDateTime())

    var final = ""

    if (human && convertedFullDate == currentFullDate) {
        final += stringResource(R.string.today)
    }

    if (!human || convertedFullDate != currentFullDate) {
        if ((convertedFullDate != currentFullDate || !showTime || forceShowDate) && !forceHideDate) {
            final += convertedDate
        }

        if (convertedYear != currentYear || forceShowYear) {
            final += " $convertedYear"
        }
    }

    if (showTime) {
        final += " $convertedTime"
    }

    return final.trim()
}

@RequiresApi(Build.VERSION_CODES.O)
fun prettyYearMonth(yearMonth: YearMonth): String {
    val yearOnlyFormatter = DateTimeFormatter.ofPattern("yyyy")
    val monthFormatter = DateTimeFormatter.ofPattern("LLLL")
    val monthWithYearFormatter = DateTimeFormatter.ofPattern("LLLL yyyy")

    return if (yearMonth.year.toString() == yearOnlyFormatter.format(LocalDate.now())) {
        yearMonth.format(monthFormatter)
    } else {
        yearMonth.format(monthWithYearFormatter)
    }.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun roundToDay(date: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = date

    return Calendar
        .Builder()
        .setTimeZone(calendar.timeZone)
        .setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        .build()
        .time
}