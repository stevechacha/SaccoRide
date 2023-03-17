package com.dev.chacha.presentation.onboarding

import com.dev.chacha.presentation.R

data class SampleOnBoard(
    val image: Int,
    val title: String,
    val desc: String
)

val onBoardItem = listOf(
    SampleOnBoard(
        R.drawable.ic_home,
        "Make it Easy One",
        "Lorem Ipsum is simply dummy text of the printing and typesetting Industry."
    ),
    SampleOnBoard(
        R.drawable.icon_arrow_back,
        "Make it Easy Two",
        "Lorem Ipsum is simply dummy text of the printing and typesetting Industry."
    ),
    SampleOnBoard(
        R.drawable.ic_home,
        "Make it Easy Three",
        "Lorem Ipsum is simply dummy text of the printing and typesetting Industry."
    )
)