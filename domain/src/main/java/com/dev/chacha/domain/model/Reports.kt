package com.dev.chacha.domain.model

import java.util.Date


/*
ReportEntity class:
    report type (e.g. balance sheet, income statement) (e.g. string or enumerated type)
    data included in the report (e.g. list of transactions, account balances) (e.g. list of objects)
    report date range (e.g. start date, end date) (e.g. date or datetime objects)
    Again, these are just a few examples, and the specific properties that you would need for a
    sacco system would depend on the specific requirements and functionality of the system.
*/


data class Report(
    val reportType: ReportType,
    val transactionHistory: List<Transaction>,
    val startDate: Date,
    val endDate: Date,

    ) {
    fun generateReport() {

    }
}


enum class ReportType {
    BalanceSheet,
    IncomeStatement
}
