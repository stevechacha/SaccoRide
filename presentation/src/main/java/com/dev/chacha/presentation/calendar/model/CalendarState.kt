package com.dev.chacha.presentation.calendar.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import com.chacha.presentation.util.getNumberWeeks
import com.dev.chacha.presentation.util.toLocalDate
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class CalendarState(selectDate: Date? = null) {

    val calendarUiState = mutableStateOf(CalendarUiState())
    val listMonths: List<Month>

    private val calendarStartDate: LocalDate = LocalDate.now().withDayOfMonth(1)
    private val calendarEndDate: LocalDate = LocalDate.now().plusYears(2)
        .withMonth(12).withDayOfMonth(31)

    private val periodBetweenCalendarStartEnd: Period = Period.between(
        calendarStartDate,
        calendarEndDate
    )

    init {
        val tempListMonths = mutableListOf<Month>()
        var startYearMonth = YearMonth.from(calendarStartDate)
        for (numberMonth in 0..periodBetweenCalendarStartEnd.toTotalMonths()) {
            val numberWeeks = startYearMonth.getNumberWeeks()
            val listWeekItems = mutableListOf<Week>()
            for (week in 0 until numberWeeks) {
                listWeekItems.add(
                    Week(
                        number = week,
                        yearMonth = startYearMonth
                    )
                )
            }
            val month = Month(startYearMonth, listWeekItems)
            tempListMonths.add(month)
            startYearMonth = startYearMonth.plusMonths(1)
        }
        listMonths = tempListMonths.toList()

        if (selectDate != null) setSelectedDay(selectDate.toLocalDate())
    }

    fun setSelectedDay(newDate: LocalDate) {
        calendarUiState.value = calendarUiState.value.setDates(LocalDate.now(), newDate)
    }

    companion object {
        const val DAYS_IN_WEEK = 7
    }
}
