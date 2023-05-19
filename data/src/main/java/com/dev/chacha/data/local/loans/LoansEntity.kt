package com.dev.chacha.data.local.loans

import androidx.room.Entity
import com.dev.chacha.domain.model.LoanStatus
import java.util.Date

/*
* •	loan amount (e.g. decimal or integer)
•	interest rate (e.g. decimal)
•	repayment terms (e.g. number of payments, payment frequency) (e.g. integer or string)
•	loan status (e.g. approved, denied, in progress) (e.g. string or enumerated type)
•	loan ID (e.g. string or integer)

* */
@Entity(tableName = "loans")
data class LoanEntity(
    val memberId: Int,
    var loanId: Int = 0,
    var loanAmount: Double = 0.0,
    var interestRate: Double = 0.0,
    var repaymentSchedule: List<PaymentsEntity>,
    var startDate: Date,
    var endDate: Date,
    val loanStatus: LoanStatus,
    val repayDuration: Int,
    val outstandingBalance: Double
)

data class PaymentsEntity(
    val amount: Double
)

enum class LoanStatus {
    Approved,
    Denied,
    InProgress
}
