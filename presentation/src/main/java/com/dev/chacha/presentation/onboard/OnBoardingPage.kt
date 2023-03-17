package com.dev.chacha.presentation.onboard

import androidx.annotation.DrawableRes
import com.dev.chacha.presentation.R


sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.home_icon,
        title = "Make it Easy One",
        description = "Lorem Ipsum dolor sit amet, consectetur adipiscing elit, sed do elusemod tempor incididunt labore."
    )
    object Second: OnBoardingPage(
        image = R.drawable.icon_arrow_back,
        title = "Make it Easy Two",
        description = "Lorem Ipsum dolor sit amet, consectetur adipiscing elit, sed do elusemod tempor incididunt labore."
    )
    object Third: OnBoardingPage(
        image = R.drawable.ic_home,
        title = "Make it Easy Three",
        description = "Lorem Ipsum dolor sit amet, consectetur adipiscing elit, sed do elusemod tempor incididunt labore."
    )
}