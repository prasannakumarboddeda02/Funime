package com.illegal.funime.data.horizontalpagerdata

import com.illegal.funime.R

enum class PagerSlideAnime(
    val image: Int,
    val id: Int,
    val title: String,
    val description: String,
) {
    PagerSlide1(
        image = R.drawable.dragonball,
        id = 813,
        title = "Dragon Ball Z",
        description = ""
    ),

    PagerSlide2(
        image = R.drawable.onepiece,
        id = 21,
        title = "One piece",
        description = ""
    ),

    PagerSlide3(
        image = R.drawable.naruto,
        id = 20,
        title = "Naruto",
        description = ""
    ),

    PagerSlide4(
        image = R.drawable.bleach,
        id = 269,
        title = "Bleach",
        description = ""
    )

}