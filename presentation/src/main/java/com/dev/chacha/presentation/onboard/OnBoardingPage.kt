package com.dev.chacha.presentation.onboard

import androidx.annotation.DrawableRes
import com.dev.chacha.presentation.R


sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.sako_icon,
        title = "Lend,Save,Spend,Send and Pay Bills with Ease!",
        description = "Our app is designed to help you manage your money with ease, so sign up now and start taking control of your financial future!"
    )
    object Second: OnBoardingPage(
        image = R.drawable.sako_ride_icons,
        title = "Manage Your Money Better with Our Finance App!",
        description = "Welcome to our finance app! Our powerful yet easy-to-use platform is designed to help you take control of your finances and make informed decisions."
    )
    object Third: OnBoardingPage(
        image = R.drawable.sako_outlined_icons,
        title = "Msaidizi Wako Binafsi wa Kikundi cha Sacco ",
        description = "App yetu ni kama kuwa na msaidizi binafsi wa kikundi cha Sacco, kwenye kifaa chako cha mkononi. Kwa huduma zetu zenye nguvu, utaweza kudhibiti pesa zako kwa urahisi."
    )
}