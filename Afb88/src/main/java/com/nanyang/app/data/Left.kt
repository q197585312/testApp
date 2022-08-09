package com.nanyang.app.data

data class Left(
    var img: String,
    var imgNoSelect: String,
    var imgSelect: String,
    var type: String,
    var isSelected: Boolean = false,
    var text: String = ""
)