package com.illegal.funime.data.horizontalpagerdata

import com.illegal.funime.R

enum class PagerSlideManga(
    val image: Int,
    val id: Int,
    val title: String,
    val description: String,
) {
    PagerSlide1(
        image = R.drawable.berserk,
        id = 813,
        title = "Berserk",
        description = ""
    ),

    PagerSlide2(
        image = R.drawable.vagabond,
        id = 21,
        title = "Vagabond",
        description = ""
    ),

    PagerSlide3(
        image = R.drawable.monster,
        id = 20,
        title = "Monster",
        description = ""
    ),

    PagerSlide4(
        image = R.drawable.oyasumipunpun,
        id = 269,
        title = "Oyasumi pun pun",
        description = ""
    )

}