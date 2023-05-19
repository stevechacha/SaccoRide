package com.dev.chacha.domain.model

/*

Loan class:
    loan amount (e.g. decimal or integer)
    interest rate (e.g. decimal)
    repayment terms (e.g. number of payments, payment frequency) (e.g. integer or string)
    loan status (e.g. approved, denied, in progress) (e.g. string or enumerated type)
    loan ID (e.g. string or integer)
*/


data class Loan(
    val loanId: String,
    val loanAmount: Double,
    val interestRate: Double,
    val NumPayments: String,
    val paymentFrequency: PaymentFrequency,
    val loanStatus: LoanStatus,
) {
    fun calculatePayment() {
        // implementation details go here
    }
}

enum class PaymentFrequency {
    Monthly,
    Biweekly,
    Weekly
}

enum class LoanStatus {
    Approved,
    Denied,
    InProgress
}

