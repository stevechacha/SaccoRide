package com.dev.chacha.presentation.base

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheets(
    appViewModel: AppViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()

    BottomSheetWrapper(
        name = CURRENCY_EDITOR,
    ) { state ->
        CurrencyEditor(
            onClose = {
                coroutineScope.launch {
                    state.hide()
                }
            }
        )
    }



    BottomSheetWrapper(
        name = FINISH_DATE_SELECTOR_SHEET,
    ) { state ->
        FinishDateSelector(
            selectDate = state.args["initialDate"] as Date?,
            onBackPressed = {
                coroutineScope.launch {
                    state.hide()
                }
            },
            onApply = {
                coroutineScope.launch {
                    state.hide(mapOf("finishDate" to it))
                }
            },
        )
    }


    /* BottomSheetWrapper(
         name = SETTINGS_CHANGE_THEME_SHEET,
         windowSizeClass = windowSizeClass,
     ) { state ->
         ThemeSwitcherDialog(
             onClose = {
                 coroutineScope.launch { state.hide() }
             }
         )
     }*/


}

@Composable
fun CurrencyEditor( onClose: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Strings")

    }

}

const val WALLET_SHEET = "wallet_sheet"
const val DEFAULT_RECALC_BUDGET_CHOOSER = "default_recalc_budget_chooser"
const val CURRENCY_EDITOR = "currency_editor"
const val FINISH_DATE_SELECTOR_SHEET = "finish_date_selector_sheet"
const val SETTINGS_SHEET = "settings_sheet"
const val RECALCULATE_DAILY_BUDGET_SHEET = "recalculate_daily_budget_sheet"
const val FINISH_PERIOD_SHEET = "finish_period_sheet"
const val ON_BOARDING_SHEET = "on_boarding_sheet"
const val NEW_DAY_BUDGET_DESCRIPTION_SHEET = "new_day_budget_description_sheet"
const val BUDGET_IS_OVER_DESCRIPTION_SHEET = "budget_is_over_description_sheet"
const val DEBUG_MENU_SHEET = "debug_menu_sheet"
const val BUG_REPORTER_SHEET = "bug_reporter_sheet"
const val SETTINGS_CHANGE_THEME_SHEET = "settings_change_theme_sheet"
const val SETTINGS_CHANGE_LOCALE_SHEET = "settings_change_locale_sheet"
