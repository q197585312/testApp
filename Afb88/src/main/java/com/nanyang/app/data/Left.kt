package com.nanyang.app.data

import com.nanyang.app.R

data class Left(
    var img: String,
    var imgNoSelect: String,
    var imgSelect: String,
    var type: String,
    var isSelected: Boolean = false,
    var text: Int =R.string.Games
)