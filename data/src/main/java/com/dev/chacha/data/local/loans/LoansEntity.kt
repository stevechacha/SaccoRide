package com.dev.chacha.data.local.loans

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/*
* •	loan amount (e.g. decimal or integer)
•	interest rate (e.g. decimal)
•	repayment terms (e.g. number of payments, payment frequency) (e.g. integer or string)
•	loan status (e.g. approved, denied, in progress) (e.g. string or enumerated type)
•	loan ID (e.g. string or integer)

* */
@Entity(tableName = "loans")
data class Loans(
    val memberId: Int,
    var loanId: Int = 0,
    var loanAmount: Double = 0.0,
    var interestRate: Double = 0.0,
    var repaymentPeriod: Int = 0,
    var repaymentSchedule: List<PaymentsEntity>,
    var startDate: Date,
    var endDate: Date
)

class PaymentsEntity {

}


data class LoanEntity(
    @PrimaryKey val id: Int,
    val memberId: Int,
    val loanAmount: Double,
    val interestRate: Double,
    val repayDuration: Int,
    val outstandingBalance: Double

)
