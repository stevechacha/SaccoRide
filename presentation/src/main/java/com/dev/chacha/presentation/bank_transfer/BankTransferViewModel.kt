package com.dev.chacha.presentation.bank_transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BankTransferViewModel : ViewModel() {
    private val _bankTransferState = MutableStateFlow(BankTransferState())
    val bankTransferState: StateFlow<BankTransferState> = _bankTransferState

    private var searchJob: Job? = null

    private val initialBankList = listOf<BankList>()

    fun onBankTransferEvent(event: BankTransferEvent){
        when(event){
            BankTransferEvent.GetAccountList -> {
                // Simulated logic to retrieve the account list
                val accountList = retrieveAccountList()
                _bankTransferState.update { it.copy(accountList = accountList) }
            }
            BankTransferEvent.GetBankList -> {
                // Simulated logic to retrieve the bank list
                val bankList = retrieveBankList()
                _bankTransferState.update { it.copy(bankList = bankList) }
            }
            is BankTransferEvent.AccountNameChanged -> {
                _bankTransferState.update { it.copy(accountName =  event.accountName) }
            }
            is BankTransferEvent.BankNameChanged -> {
                _bankTransferState.update { it.copy(bankName = event.bankName) }
            }
            is BankTransferEvent.SearchBank -> {
                if (event.searchParams.isNotEmpty()){
                    _bankTransferState.update { it.copy(searchParams =  event.searchParams.trim()) }
                    searchJob?.cancel()
                    searchJob = viewModelScope.launch {
                        try {
                            _bankTransferState.update { it.copy(isLoading = true) }
                            // Simulated delay to mimic a long-running search operation
                            delay(1000)
                            // Perform the actual bank search logic heres
                            _bankTransferState.update { it.copy(searchParams = event.searchParams) }
                            // Update the state with the search results
                            val filteredBankList = initialBankList.filter {
                                it.bankName.contains(bankTransferState.value.searchParams, ignoreCase = true)
                            }
                            _bankTransferState.update { it.copy(bankList = filteredBankList, isLoading = false) }
                        } catch (e: CancellationException) {
                            // Handle cancellation if needed
                        } catch (e: Exception) {
                            _bankTransferState.update { it.copy(error = e.message ?: "An error occurred", isLoading = false) }
                        }
                    }
                } else{
                    _bankTransferState.update { it.copy(bankList = retrieveBankList()) }
                    _bankTransferState.update { it.copy(bankList =  initialBankList.take(1000))}

                }

            }

            is BankTransferEvent.AmountChanged -> {
                _bankTransferState.update { it.copy(amount = event.amount) }
            }

            is BankTransferEvent.TransferDescriptionChanged -> {
                _bankTransferState.update { it.copy(transferDescription = event.transferDescription) }
            }

            BankTransferEvent.BankTransfer -> {

            }
        }
    }
    // Simulated logic to retrieve the account list
    private fun retrieveAccountList(): List<AccountList> {
        // Replace this with your actual implementation to retrieve the account list
        // For demonstration purposes, returning a hardcoded list here
        return listOf(
            AccountList("Account 1","",""),
            AccountList("Account 2","",""),
            AccountList("Account 3","",""),

        )
    }

    // Simulated logic to retrieve the bank list
    private fun retrieveBankList(): List<BankList> {
        // Replace this with your actual implementation to retrieve the bank list
        // For demonstration purposes, returning a hardcoded list here
        return listOf(
            BankList("Bank 1"),
            BankList("Bank 2"),
            BankList("Bank 3")
        )
    }




    private suspend fun searchInDatabase(searchParams: String): List<BankList> {
        // Replace this with your actual implementation to perform the bank search
        // For demonstration purposes, returning a hardcoded list of search results here
        return listOf(
            BankList("Bank 1"),
            BankList("Bank 2")
        )
    }


}